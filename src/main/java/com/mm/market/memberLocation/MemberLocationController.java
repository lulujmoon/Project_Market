package com.mm.market.memberLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberLocationController {

	@Autowired
	MemberLocationService memberLocationService;
	
	@GetMapping("/memberLocation/insert")
	public String setInsert(MemberLocationVO memberLocationVO) throws Exception {
		memberLocationService.setInsert(memberLocationVO);
		return "redirect:../member/info";
	}
	
}
