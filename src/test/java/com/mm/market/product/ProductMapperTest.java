package com.mm.market.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;

	//@Test
	void getListTest() throws  Exception {
		List<ProductVO> ar = productMapper.getList();
		assertNotEquals(0, ar.size());
	}

	
	@Test
	void getSelectTest()throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(3L);
		
		productVO = productMapper.getSelect(productVO);
		assertNotNull(productVO);
	}
}
