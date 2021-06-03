package com.mm.market.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.Pager;

@Mapper
public interface ProductMapper {
	
	//List
	public List<ProductVO> getList(Pager pager)throws Exception;
	
	public List<ProductVO> getCateList(Pager pager)throws Exception;
	
	public int setCategory(ProductVO productVO)throws Exception;
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	//Select
	public ProductVO getSelect(ProductVO productVO)throws Exception;
	
	//productNum
	public long getProductNum()throws Exception;
	
	
	public int setHitUpdate(ProductVO productVO)throws Exception;
	
	//Delete
	public int setDelete(ProductVO productVO)throws Exception;

	//insert
	public int setInsert(ProductVO productVO) throws Exception;
	
	//file Insert
	public int setFileInsert(ProductFileVO productFileVO)throws Exception;
	
	//file Delete
	public int setFileDelete(ProductFileVO productFileVO)throws Exception;
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception;
	
}
