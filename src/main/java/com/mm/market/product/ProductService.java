package com.mm.market.product;


import java.nio.file.Files;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private HttpSession session;
	
	public List<ProductVO> getList(Pager pager)throws Exception{
		Long perPage = 20L;
		Long perBlock = 5L;
		
		pager.makeRow(perPage);
		Long totalCount = productMapper.getTotalCount(pager);
		pager.makeNum(totalCount, perPage, perBlock);
		
		return productMapper.getList(pager);
	}
	
	public List<ProductVO> getCateList(Pager pager)throws Exception {

		return productMapper.getCateList(pager);
	}
	
	
	public ProductVO getSelect(ProductVO productVO) throws Exception {
		productMapper.setHitUpdate(productVO);
		return productMapper.getSelect(productVO);
	}
	
	public int setDelete(ProductVO productVO)throws Exception{
		return productMapper.setDelete(productVO);
	}


	//insert
	@Transactional(rollbackFor = Exception.class)
	public int setInsert(ProductVO productVO, MultipartFile [] file) throws Exception {
		
		int result = productMapper.setInsert(productVO);
		
		long productNum = productMapper.getProductNum();
		productVO.setProductNum(productNum);
		
		
		for(MultipartFile f:file) {
			ProductFileVO productFileVO = new ProductFileVO();
			String fileName = fileManager.save("product", f, session);
			System.out.println(fileName);
			productFileVO.setProductNum(productNum);
			productFileVO.setFileName(fileName);
			productFileVO.setOriginName(f.getOriginalFilename());
			
			productMapper.setFileInsert(productFileVO);
		}
		
		return result;
	}
	
	//update
	public int setUpdate(ProductVO productVO, MultipartFile file) throws Exception {
		int result = 0;
		
		if(file.getOriginalFilename().length()!=0) {
			ProductVO productVO2 = productMapper.getSelect(productVO);
			
			if(productVO2.getThumbnail()!=null) {
				String delFileName = productVO2.getThumbnail().getFileName();
				boolean check = fileManager.delete("product", delFileName, session);
				
				ProductFileVO productFileVO = new ProductFileVO();
				productFileVO.setFileNum(productVO2.getThumbnail().getFileNum());
				productMapper.setFileDelete(productFileVO);
			}
			
			String fileName = fileManager.save("product", file, session);
			
			ProductFileVO productFileVO = new ProductFileVO();
			productFileVO.setProductNum(productVO.getProductNum());
			productFileVO.setFileName(fileName);
			productFileVO.setOriginName(file.getOriginalFilename());
			
			result = productMapper.setUpdate(productVO);
			result = productMapper.setFileInsert(productFileVO);
		} else {
			result = productMapper.setUpdate(productVO);
		}
		return result;
		
	}
	
	
	
	//heart
	public void setHeart(HeartVO heartVO)throws Exception{
		productMapper.setHeart(heartVO);
		productMapper.updateHeart(heartVO.getProductNum());
	}
	
	
	public void deleteHeart(HeartVO heartVO)throws Exception{
		productMapper.deleteHeart(heartVO);
		productMapper.updateHeart(heartVO.getProductNum());
	}
	
	public Long getHeart(HeartVO heartVO)throws Exception{
		return productMapper.getHeart(heartVO);
	}
	
	
	
	
}
