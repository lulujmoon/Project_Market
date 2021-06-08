package com.mm.market.memberLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberLocationController {

	@Autowired
	MemberLocationService memberLocationService;
	
	@PostMapping("/memberLocation/insert")
	public String setInsert(MemberLocationVO memberLocationVO) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)principal;
		
		memberLocationVO.setUsername(userDetails.getUsername());
		memberLocationService.setInsert(memberLocationVO);
		return "redirect:../member/info";
	}
	
	@PostMapping("/memberLocation/delete")
	public String setDelete(MemberLocationVO memberLocationVO) throws Exception {
		memberLocationService.setDelete(memberLocationVO);
		
		return "redirect:../member/info";
	}
	
}
