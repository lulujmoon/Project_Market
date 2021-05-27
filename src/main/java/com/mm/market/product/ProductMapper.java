package com.mm.market.product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

	//insert
	public int setInsert(ProductVO productVO) throws Exception;
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception;
	
}
