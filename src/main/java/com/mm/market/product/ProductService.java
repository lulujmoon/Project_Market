package com.mm.market.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
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
	public int setInsert(ProductVO productVO) throws Exception {
		return productMapper.setInsert(productVO);
	}
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception {
		return productMapper.setUpdate(productVO);
	}
	
}
