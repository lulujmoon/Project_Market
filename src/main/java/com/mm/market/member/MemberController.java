package com.mm.market.member;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
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

	@GetMapping("memberLogin")
	public String getLogin()throws Exception{
		return "member/memberLogin";
	}
	
	@GetMapping("memberLoginFail")
	public String loginFail()throws Exception{
		return "redirect:/member/memberLogin";
	}
	
	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session, Authentication auth2)throws Exception{
		
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			System.out.println("attribute Name:+"+en.nextElement());
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		SecurityContextImpl sc = (SecurityContextImpl)obj;
		
		Authentication auth= sc.getAuthentication();
		
		System.out.println("obj : "+obj);
		
		System.out.println("login 성공");
		
		return "redirect:/";
		
	}
	
	
	
	
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
