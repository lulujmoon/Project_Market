package com.mm.market.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("memberJoin")
	public String setJoin() throws Exception {
		return "member/memberJoin";
	}

	
	 @PostMapping("memberJoin")
	 public String setJoin(MemberVO memberVO,ModelAndView mv)throws Exception{
	 int result = memberService.setJoin(memberVO); 
	
	 return "redirect:../";
	  }
	
	 
}
