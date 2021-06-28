package com.mm.market.socialReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mm.market.member.MemberVO;
import com.mm.market.social.SocialService;
import com.mm.market.social.SocialVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SocialReportController {
	
	@Autowired
    private final SocialReportService reportService;
	
	@Autowired
	private SocialService socialService;

    @GetMapping("/report/socialReport")
    public String dispMail(SocialVO socialVO, Model model) {
			try {
				socialVO = socialService.getSelect(socialVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("social", socialVO);
		
        return "/report/socialReport";
    }

    @PostMapping("/report/socialReport")
    public String execMail(SocialReportVO reportVO, Authentication auth) {
        MemberVO memberVO = (MemberVO)auth.getPrincipal();
        reportVO.setUsername(memberVO.getUsername());
    	
    	reportService.mailSend(reportVO);
        
        return "redirect:/";
    }
}
