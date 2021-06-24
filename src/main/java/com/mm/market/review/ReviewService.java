package com.mm.market.review;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	public List<ReviewVO> getListByReviewer(ReviewVO reviewVO) throws Exception {
		return reviewMapper.getListByReviewer(reviewVO);
	}
	
	public List<ReviewVO> getListByReviewee(ReviewVO reviewVO) throws Exception {
		return reviewMapper.getListByReviewee(reviewVO);
	}
	
	public ReviewVO getSelect(ReviewVO reviewVO) throws Exception {
		return reviewMapper.getSelect(reviewVO);
	}
	
	public int setInsert(ReviewVO reviewVO) throws Exception {
		return reviewMapper.setInsert(reviewVO);
	}
	
	public ReviewVO getAvgsAndCounts(ReviewVO reviewVO) throws Exception {
		return reviewMapper.getAvgsAndCounts(reviewVO);
	}
}
