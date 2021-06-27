package com.mm.market.review;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mm.market.util.ReviewPager;

@Mapper
public interface ReviewMapper {

	public List<ReviewVO> getListByReviewer(ReviewVO reviewVO) throws Exception;
	
	public List<ReviewVO> getListByReviewee(ReviewPager reviewPager) throws Exception;
	
	public Long getTotalCount(ReviewPager reviewPager) throws Exception;
	
	public ReviewVO getSelect(ReviewVO reviewVO) throws Exception;
		
	public int setInsert(ReviewVO reviewVO) throws Exception;
	
	public ReviewVO getAvgsAndCounts(ReviewVO reviewVO) throws Exception;
	
	public int setDelete(ReviewVO reviewVO) throws Exception;
}
