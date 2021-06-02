package com.mm.market.product;

import java.util.ArrayList;
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
	
//	@Value("${product.filePath}")
//	private String filePath;
	
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
	public int setInsert(ProductVO productVO, MultipartFile [] files) throws Exception {
			int result = productMapper.setInsert(productVO);
			
			String filePath = "product";
			
			for(MultipartFile multipartFile:files) {
				if(multipartFile.getSize()==0) {
					continue;
				}
				String fileName = fileManager.save(filePath, multipartFile, session);
				System.out.println(fileName);
				ProductFileVO productFileVO = new ProductFileVO();
				productFileVO.setFileName(fileName);
				productFileVO.setOriginName(multipartFile.getOriginalFilename());
				productFileVO.setFileNum(productVO.getProductNum());
				
				productMapper.setFileInsert(productFileVO);
			}	
			
		return result; 
	}
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception {
		return productMapper.setUpdate(productVO);
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
