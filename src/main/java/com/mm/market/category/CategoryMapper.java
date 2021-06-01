package com.mm.market.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

	public List<CategoryVO> getList() throws Exception;
	

}

