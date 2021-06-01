package com.mm.market.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mm.market.util.FileManager;
import com.mm.market.util.Pager;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	public List<ProductVO> getList(Pager pager)throws Exception{
		Long perPage = 20L;
		Long perBlock = 5L;
		
		pager.makeRow(perPage);
		Long totalCount = productMapper.getTotalCount(pager);
		pager.makeNum(totalCount, perPage, perBlock);
		return productMapper.getList(pager);
	}
	
	
	public ProductVO getSelect(ProductVO productVO) throws Exception {
		productMapper.setHitUpdate(productVO);
		return productMapper.getSelect(productVO);
	}
	
	public int setDelete(ProductVO productVO)throws Exception{
		return productMapper.setDelete(productVO);
	}

	//insert
	public int setInsert(ProductVO productVO, MultipartFile [] files) throws Exception {
		int result = productMapper.setInsert(productVO);
		String filePath = "upload/product/";
		
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()==0) {
				continue;
			}
			String fileName = fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			ProductFileVO productFileVO = new ProductFileVO();
			productFileVO.setFileName(fileName);
			productFileVO.setOriginName(multipartFile.getOriginalFilename());
			productFileVO.setProductNum(productVO.getProductNum());
			
			productMapper.setFileInsert(productFileVO);
		}
		return result; 
	}
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception {
		return productMapper.setUpdate(productVO);
	}
	
}
