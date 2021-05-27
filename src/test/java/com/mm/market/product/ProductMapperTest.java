package com.mm.market.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductMapperTest {

	@Autowired
	private ProductMapper productMapper;
	
	@Test
	void setInsertTest() throws Exception{
		
		for(int i=5;i<10;i++) {
			ProductVO productVO = new ProductVO();
			productVO.setProductName("title"+i);
			productVO.setUsername("user1");
			productVO.setProductCategory("c"+i);
			productVO.setProductContent("cont"+i);
			productVO.setProductPrice(1000L);
			productMapper.setInsert(productVO);
			
		}
		
		System.out.println("finish");
	}



}
