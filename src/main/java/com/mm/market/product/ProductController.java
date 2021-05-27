package com.mm.market.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.s1.util.Pager;

@Controller
@RequestMapping("/product/**")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public String getList(Model model) throws Exception {
		
//		List<ProductVO> ar = productService.getList();
//		model.addAttribute("list", ar);
//		model.addAttribute("pager", pager);
		
		return "product/list";
	}
	
}
