package com.mm.market.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;

@Mapper
public interface ProductMapper {
	
	//List
	public List<ProductVO> getList(ProductPager productPager) throws Exception;
	
	public List<ProductVO> getListByUsername(ProductPager productPager) throws Exception;
	
	public Long getTotalCount(ProductPager productPager) throws Exception;
	
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
	
	//file Select 
	public ProductFileVO getFileSelect(ProductFileVO productFileVO)throws Exception;
	
	//update
	public int setUpdate(ProductVO productVO) throws Exception;
	
	//rewrite
	public int setRewrite(ProductVO productVO) throws Exception;
	
	//setStatus
	public int setStatus(ProductVO productVO) throws Exception;

	
	//heart
	public Long getHeart(HeartVO heartVO)throws Exception;
	
	public int setHeart(HeartVO heartVO)throws Exception;
	
	public int deleteHeart(HeartVO heartVO)throws Exception;
	
	public void updateHeart(Long productNum)throws Exception;
	
	public List<ProductVO> getHeartList(ProductPager productPager)throws Exception;
	
}
