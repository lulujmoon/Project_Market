package com.mm.market.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;

@Controller
@RequestMapping("/notification/**")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ProductService productservice;
	
	//알림 인서트 하기	
	@PostMapping("notiInsert")
	public String notiInsert(@RequestParam String notiContent, @RequestParam String notiRecvUser, @RequestParam Long productNum, Authentication auth)throws Exception{
		
		System.out.println("notiRecvUser: "+notiRecvUser);
		System.out.println("notiContent: "+notiContent);
		System.out.println("productNum: "+productNum);
		
		NotificationVO notificationVO = new NotificationVO();
		ProductVO productVO = new ProductVO();
		
		//현재 로그인한 유저
		MemberVO memberVO = (MemberVO)auth.getPrincipal();	
		String username = memberVO.getUsername();
		
		//판매자		
		notificationVO.setNotiSendUser(username);
		notificationVO.setNotiRecvUser(notiRecvUser);
		notificationVO.setNotiContent(notiContent);
		notificationVO.setProductNum(productNum);
		
		System.out.println("notiSendUser: "+username);
		
		return "redirect:/product/list";
		
		
	}
	
	@RequestMapping("notiDelete")
	public String notiDelete(NotificationVO notificationVO)throws Exception{
		int result = notificationService.notiDelete(notificationVO);
		
		return "redirect:/product/list";
	}
}
