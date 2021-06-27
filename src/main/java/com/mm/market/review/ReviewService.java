package com.mm.market.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.market.util.ReviewPager;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	public List<ReviewVO> getListByReviewer(ReviewVO reviewVO) throws Exception {
		return reviewMapper.getListByReviewer(reviewVO);
	}
	
	public List<ReviewVO> getListByReviewee(ReviewPager reviewPager, Long perPage) throws Exception {
		reviewPager.setPerPage(perPage);
		reviewPager.makeRow(perPage);
		Long totalCount = reviewMapper.getTotalCount(reviewPager);
		reviewPager.makeNum(totalCount, perPage, 5L);

		return reviewMapper.getListByReviewee(reviewPager);
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
	
	public int setDelete(ReviewVO reviewVO) throws Exception {
		return reviewMapper.setDelete(reviewVO);
	}
}
