package com.mm.market.reservation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.member.MemberVO;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.product.ProductController;
import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;

@Controller
@RequestMapping("/reservation/**")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("insert")
	public String insert(String buyer,Long productNum, ReservationVO reservationVO, Long locationCode, Authentication authentication)throws Exception{	
		
		MemberVO memberVO =(MemberVO)authentication.getPrincipal();
				
		reservationVO.setProductNum(productNum);
		reservationVO.setBuyer(buyer);
		reservationVO.setSeller(memberVO.getUsername());
		reservationVO.setLocationCode(locationCode);
				
		int result = reservationService.setInsert(reservationVO);
		System.out.println(result);
		
		return "redirect:/product/select/"+reservationVO.getProductNum(); 
	}
	
	@GetMapping("select")
	public void getSelect(ReservationVO reservationVO)throws Exception{
		
		reservationVO = reservationService.getSelect(reservationVO);
		
	}
	
}
