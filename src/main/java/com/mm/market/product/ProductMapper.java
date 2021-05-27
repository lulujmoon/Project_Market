package com.mm.market.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sw.s1.util.Pager;

@Mapper
public interface ProductMapper {
	
	//List
	public List<ProductVO> getList()throws Exception;
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	//Select
	public ProductVO getSelect(ProductVO productVO)throws Exception;
	
	public int setHitUpdate(ProductVO productVO)throws Exception;
	
	//Delete
	public int setDelete(ProductVO productVO)throws Exception;

}
