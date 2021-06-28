package com.mm.market.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductVO;

@Controller
@RequestMapping("/notification/**")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	//알림 인서트 하기	
	@PostMapping("notiInsert")
	public String notiInsert(@RequestParam String notiContent, @RequestParam String notiRecvUser, Authentication auth)throws Exception{
		NotificationVO notificationVO = new NotificationVO();
		ProductVO productVO = new ProductVO();
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		
		String username = memberVO.getUsername();
		
		System.out.println("user: "+username);
		notificationVO.setNotiSendUser(username);
		notificationVO.setProductNum(productVO.getProductNum());
		notificationVO.setNotiRecvUser(notiRecvUser);
		notificationVO.setNotiContent(notiContent);
		
		System.out.println("recvUser: "+notiRecvUser);
		
		return "redirect:/product/list";
		
		
	}
	
	@RequestMapping("notiDelete")
	public String notiDelete(NotificationVO notificationVO)throws Exception{
		int result = notificationService.notiDelete(notificationVO);
		
		return "redirect:/product/list";
	}
}
