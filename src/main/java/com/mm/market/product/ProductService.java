package com.mm.market.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.s1.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public List<ProductVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		Long totalCount = productMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		
		return productMapper.getList(pager);
	}
	

}
