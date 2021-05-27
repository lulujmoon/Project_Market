package com.mm.market.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public List<ProductVO> getList()throws Exception{

		return productMapper.getList();
	}
	

}
