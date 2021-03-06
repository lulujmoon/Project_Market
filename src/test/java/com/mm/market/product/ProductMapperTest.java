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
	/*
	 * void getListTest() throws Exception { List<ProductVO> ar =
	 * productMapper.getList(); assertNotEquals(0, ar.size()); }
	 */

	
	//@Test
	void getSelectTest()throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(3L);
		
		productVO = productMapper.getSelect(productVO);
		assertNotNull(productVO);
	}
	
//	@Test
	/*
	 * void setInsertTest() throws Exception{
	 * 
	 * for(int i=5;i<10;i++) { ProductVO productVO = new ProductVO();
	 * productVO.setProductName("title"+i); productVO.setUsername("user1");
	 * productVO.setProductCategory("c"+i); productVO.setProductContent("cont"+i);
	 * productVO.setProductPrice(1000L); productMapper.setInsert(productVO);
	 * 
	 * }
	 * 
	 * System.out.println("finish"); }
	 */
	
	
	@Test
	void setInsertTest() throws Exception{
		for(int i=0;i<10;i++) {
			ProductVO productVO = new ProductVO();
			productVO.setProductName("티켓"+i);
			productVO.setUsername("id1");
			productVO.setCategoryCode(11L);
			productVO.setProductContent("판매합니다");
			productVO.setProductPrice(10000L);
			productVO.setLocationCode(1111011900L);

			int result = productMapper.setInsert(productVO);
			
			assertNotEquals(result, 0);
		}
		
	}
	
	

}
