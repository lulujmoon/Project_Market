package com.mm.market.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store/**")
public class StoreController {

	@GetMapping("{code}/products")
	public String products(@PathVariable("code") Long code) throws Exception {
		return "/store/products";
	}
	
	@GetMapping("{code}/reviews")
	public String reviews(@PathVariable("code") Long code) throws Exception {
		return "/store/reviews";
	}
	
	@GetMapping("{code}/socials")
	public String socials(@PathVariable("code") Long code) throws Exception {
		return "/store/socials";
	}
	
}
