package com.mm.market.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;
import com.mm.market.social.SocialService;
import com.mm.market.social.SocialVO;
import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;
import com.mm.market.util.SocialPager;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SocialService socialService;
	
	@GetMapping("/")
	public ModelAndView home(ProductPager productPager, ModelAndView mv)throws Exception {

		List<ProductVO> productList = productService.getList(productPager, 11L, 5L);
		List<SocialVO> socialList = socialService.getList(new SocialPager());
		
		mv.addObject("products", productList);
		mv.addObject("socials", socialList);
		mv.setViewName("home");
		
		return mv;
	}
	
}
