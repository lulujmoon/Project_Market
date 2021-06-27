package com.mm.market.review;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

	public List<ReviewVO> getListByReviewer(ReviewVO reviewVO) throws Exception;
	
	public List<ReviewVO> getListByReviewee(ReviewVO reviewVO) throws Exception;
	
	public ReviewVO getSelect(ReviewVO reviewVO) throws Exception;
	
	public int setInsert(ReviewVO reviewVO) throws Exception;
}
