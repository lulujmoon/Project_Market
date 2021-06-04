package com.mm.market.review;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewTest {

	@Autowired
	private ReviewMapper reviewMapper;
	
//	@Test
//	void getListByReviewer() throws Exception {
//		ReviewVO reviewVO = new ReviewVO();
//		reviewVO.setReviewer("user2");
//		
//		List<ReviewVO> list = reviewMapper.getListByReviewer(reviewVO);
//		for(ReviewVO review : list) {
//			System.out.println(review.getReviewNum());
//			System.out.println(review.getCondition());
//			System.out.println("-----------------------");
//		}
//		
//		assertNotEquals(0, list.size());
//	}
	
//	@Test
//	void getListByReviewee() throws Exception {
//		ReviewVO reviewVO = new ReviewVO();
//		reviewVO.setReviewee("user");
//		
//		List<ReviewVO> list = reviewMapper.getListByReviewee(reviewVO);
//		for(ReviewVO review : list) {
//			System.out.println(review.getReviewNum());
//			System.out.println(review.getCondition());
//			System.out.println("-----------------------");
//		}
//		
//		assertNotEquals(0, list.size());
//	}
	
//	@Test
//	void getSelect () throws Exception {
//		ReviewVO reviewVO = new ReviewVO();
//		reviewVO.setProductNum(1);
//		
//		reviewVO= reviewMapper.getSelect(reviewVO);
//		
//			System.out.println(reviewVO.getReviewNum());
//			System.out.println(reviewVO.getCondition());
//		
//		assertNotNull(reviewVO);
//	}
	
	@Test
	void setInsert() throws Exception {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setProductNum(1L);
		reviewVO.setReviewer("user");
		reviewVO.setReviewee("user2");
		reviewVO.setType(false);
		reviewVO.setLocationCode(1111010300L);
		reviewVO.setManner(10L);
		reviewVO.setResponse(7L);
		
		int result = reviewMapper.setInsert(reviewVO);
		assertNotEquals(0, result);
	}

}
