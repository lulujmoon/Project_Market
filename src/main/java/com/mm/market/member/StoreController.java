package com.mm.market.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store/**")
public class StoreController {

	@GetMapping("products")
	public void products(MemberVO memberVO) throws Exception {
		
	}
	
	@GetMapping("reviews")
	public void reviews() throws Exception { }
	
	@GetMapping("socials")
	public void socials() throws Exception { }
	
}
