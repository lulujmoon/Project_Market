package com.mm.market.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.member.MemberService;
import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;
import com.mm.market.reservation.ReservationService;
import com.mm.market.reservation.ReservationVO;

@Controller
@RequestMapping("/review/")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ProductService productService;
	

	@GetMapping("insert")
	public String setInsert(ReservationVO reservationVO, Authentication authentication, Model model) throws Exception {
		//ReservationVO에는 productNum만 담기면 된다.
		reservationVO = reservationService.getSelect(reservationVO);
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		MemberVO counterpart = new MemberVO();

		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setProductNum(reservationVO.getProductNum());
		reviewVO.setReviewer(memberVO.getUsername());
		reviewVO = reviewService.getSelect(reviewVO);
		
		if(reviewVO != null) {
			model.addAttribute("alert", "이미 후기를 작성한 거래입니다.");
			return "review/insert";
		}else {
			reviewVO = new ReviewVO();
			reviewVO.setProductNum(reservationVO.getProductNum());
			if(memberVO.getUsername().equals(reservationVO.getSeller())) {
				reviewVO.setType(1L);
				counterpart.setUsername(reservationVO.getBuyer());
			}else if(memberVO.getUsername().equals(reservationVO.getBuyer())) {
				reviewVO.setType(2L);
				counterpart.setUsername(reservationVO.getSeller());
			}else {
				model.addAttribute("alert", "거래 당사자가 아닙니다.");
			}
			counterpart = memberService.getSeletByUsername(counterpart);
			
			ProductVO productVO = new ProductVO();
			productVO.setProductNum(reservationVO.getProductNum());
			productVO = productService.getSelect(productVO);
			
			model.addAttribute("counterpart", counterpart);
			model.addAttribute("product", productVO);
			model.addAttribute("dealType", reviewVO.getType());		
		}
		
		return "review/insert";
	}
	
	@PostMapping("insert")
	public String setInsert(ReviewVO reviewVO, Authentication authentication) throws Exception {
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		reviewVO.setReviewer(memberVO.getUsername());		
		reviewService.setInsert(reviewVO);
			
		List<ReviewVO> reviewList =reviewService.getListByReview(reviewVO);
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setProductNum(reviewVO.getProductNum());
		
		if(reviewList.size()>1) {
			reservationService.setDelete(reservationVO);
		}
		
		return "redirect:/store/"+memberVO.getCode()+"/myReviews";
	}
	
	@PostMapping("delete")
	public ModelAndView setDelete(ReviewVO reviewVO, ModelAndView mv) throws Exception {
		int result = reviewService.setDelete(reviewVO);
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
}
