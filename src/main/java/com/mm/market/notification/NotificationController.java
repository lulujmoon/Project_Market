package com.mm.market.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private ProductService productService;
	
	//알림 인서트 하기	
	@PostMapping("notiInsert")
	public String notiInsert(@RequestParam String notiContent, @RequestParam Long productNum, @RequestParam String notiRecvUser, Authentication auth)throws Exception{
		NotificationVO notificationVO = new NotificationVO();
		ProductVO productVO = new ProductVO();
		
		//현재 로그인한 유저
		MemberVO memberVO = (MemberVO)auth.getPrincipal();	
		String username = memberVO.getUsername();
		
		//상품 번호
		productVO.setProductNum(productNum);
		productVO =	productService.getSelect(productVO);
		
		//판매자
		notiRecvUser = productVO.getUsername();
		
		notificationVO.setNotiSendUser(username);
		notificationVO.setNotiRecvUser(notiRecvUser);
		notificationVO.setNotiContent(notiContent);
		notificationVO.setProductNum(productNum);
		
		System.out.println("notiSendUser: "+username);
		System.out.println("notiRecvUser: "+notiRecvUser);
		System.out.println("notiContent: "+notiContent);
		System.out.println("productNum: "+productNum);
		
		return "redirect:/product/list";
		
		
	}
	
	@RequestMapping("notiDelete")
	public String notiDelete(NotificationVO notificationVO)throws Exception{
		int result = notificationService.notiDelete(notificationVO);
		
		return "redirect:/product/list";
	}
}
