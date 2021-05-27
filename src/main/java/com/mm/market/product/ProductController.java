package com.mm.market.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/product/**")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("productInsert")
	public void setInsert()throws Exception{}
	
	@PostMapping("productInsert")
	public ModelAndView setInsert(ProductVO productVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = productService.setInsert(productVO);
		mv.addObject("product", productVO);
		mv.setViewName("redirect:./productlist");
		
		return mv;
	}
}
