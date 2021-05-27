package com.mm.market.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home()throws Exception {
		//System.out.println("index start");
		return "index";
	}
}
