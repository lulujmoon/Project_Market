package com.mm.market.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	
	@Autowired
	private ProductMapper productMapper;
	
	//insert
	public int setInsert(ProductVO productVO)throws Exception{
		int result = productMapper.setInsert(productVO);
		
		return result;
	}
}
