package com.mm.market.member;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/*
	 * @GetMapping("error") public String error() { return "error/error"; }
	 */

	@GetMapping("memberLogin")
	public String getLogin()throws Exception{
		return "member/memberLogin";
	}
	
	@PostMapping("memberLogin")
	public String getLogin(HttpServletRequest request)throws Exception{
		//포워딩된 어트리뷰트를 포스트형식으로 받아줌
		System.out.println(request.getAttribute("message"));
		
		return "member/memberLogin";
	}

	@GetMapping("memberLoginFail")
	public String loginFail()throws Exception{
		return "redirect:/member/memberLogin";
	}

	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session, Authentication auth2)throws Exception{

		Enumeration<String> en = session.getAttributeNames();
		MemberVO memberVO = new MemberVO();
		System.out.println(memberVO.getUsername());

		while(en.hasMoreElements()) {
			System.out.println("attribute Name:+"+en.nextElement());
		}

		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");

		SecurityContextImpl sc = (SecurityContextImpl)obj;
									//저장되는 session의 타입
		Authentication auth = sc.getAuthentication();

		System.out.println("===================================");
		System.out.println("Name : "+auth.getName());
		System.out.println("Details : "+auth.getDetails());
		System.out.println("Principal : "+auth.getPrincipal());
		System.out.println("Authorities : "+auth.getAuthorities());
		System.out.println("===================================");

		System.out.println("===================================");
		System.out.println("Name : "+auth2.getName());
		System.out.println("Details : "+auth2.getDetails());
		System.out.println("Principal : "+auth2.getPrincipal());
		System.out.println("Authorities : "+auth2.getAuthorities());
		System.out.println("===================================");

		System.out.println("obj : "+obj);

		System.out.println("login 성공");

		return "redirect:/";

	}

	@GetMapping("memberLogout")
	public String logout(HttpSession session)throws Exception{
		System.out.println("로그아웃");
		session.invalidate();

		return "redirect:../";
	}


	@GetMapping("memberJoin")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/memberJoin";
	}


	@PostMapping("memberJoin")
	public String setJoin(@Valid MemberVO memberVO,Errors errors,ModelAndView mv)throws Exception{
		System.out.println("Join process"+ memberVO.getName().length());

		  if(memberService.memberError(memberVO, errors)) { 
			  System.out.println("에러났어");
		  return"member/memberJoin"; 
		  
		  }

		int result = memberService.setJoin(memberVO); 

		return "redirect:../";

	}
	


}
