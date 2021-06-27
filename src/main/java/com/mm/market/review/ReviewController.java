package com.mm.market.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/review/")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("reviews/{reviewee}")
	public ModelAndView getLists(@PathVariable("reviewee") String reviewee, ModelAndView mv) throws Exception {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setReviewee(reviewee);
		reviewVO.setType(true);
		List<ReviewVO> buyerReviews = reviewService.getListByReviewee(reviewVO);
		reviewVO.setType(false);
		List<ReviewVO> sellerReviews = reviewService.getListByReviewee(reviewVO);
		
		mv.addObject("buyerReviews", buyerReviews);
		mv.addObject("sellerReviews", sellerReviews);
		mv.setViewName("review/reviewList");

		return mv;
		
	}
	
}
