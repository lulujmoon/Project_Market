package com.mm.market.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store/**")
public class StoreController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("{code}/products")
	public ModelAndView products(@PathVariable("code") Long code, Authentication authentication, ModelAndView mv) throws Exception {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setCode(code);
		memberVO = memberService.getSelectByCode(memberVO);
		MemberFileVO memberFileVO = memberService.selectFile(memberVO);
		
		mv.addObject("member", memberFileVO);
		mv.addObject("file", memberFileVO);
		mv.setViewName("/store/products");
		return mv;
	}
	
	@GetMapping("{code}/reviews")
	public String reviews(@PathVariable("code") Long code) throws Exception {
		return "/store/reviews";
	}
	
	@GetMapping("{code}/socials")
	public String socials(@PathVariable("code") Long code) throws Exception {
		return "/store/socials";
	}
	
	@GetMapping("profileUpdate")
	public ModelAndView setUpdateFile(MemberFileVO memberFileVO, Authentication authentication) throws Exception{
		MemberVO memberVO =(MemberVO)authentication.getPrincipal();
		memberFileVO = memberService.selectFile(memberVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("file",memberFileVO);
		mv.setViewName("member/profileUpdate");
		
		return mv;
	}
	
	@PostMapping("profileUpdate")
	public String setUpdateFile(MemberFileVO memberFileVO, MultipartFile avatar, Authentication authentication) throws Exception{
		MemberVO memberVO =(MemberVO)authentication.getPrincipal();
		int result = memberService.setUpdateFile(avatar, memberVO, authentication);
		return "redirect:/store/"+memberVO.getCode()+"/products";
	}
	
}
