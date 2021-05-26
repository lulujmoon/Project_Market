package com.mm.market.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/**")
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@GetMapping("productInsert")
	public String setInsert() throws Exception{
		return "product/productInsert";
	}
	
	@PostMapping("productInsert")
	public String setInsert(ProductVO productVO) throws Exception{
		int result = productservice.setInsert(productVO);
		
		return "redirect:../index";
	}
}
