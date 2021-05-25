package com.mm.market.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public void setJoin()throws Exception{
		
	}
	
	@PostMapping("join")
	public int setJoin(MemberVO memberVO)throws Exception{
		
		int result = memberService.setJoin(memberVO);
		
		return result;
	}
}
