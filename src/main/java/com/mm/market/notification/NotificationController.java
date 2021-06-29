package com.mm.market.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//가격 제안하기 페이지
	@GetMapping("nego")
	public void setNego(NotificationVO notificationVO, Authentication auth, Model model)throws Exception{
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		notificationVO.setNotiSendUser(username);

		model.addAttribute("noti", notificationVO);
	}
	
	//알림 인서트 하기	
	@PostMapping("nego")
	public String notiInsert(NotificationVO notificationVO, Authentication auth)throws Exception{
		
		System.out.println("seller: "+notificationVO.getNotiRecvUser());
		System.out.println("notiContent: "+notificationVO.getNotiContent());
		System.out.println("productNum: "+notificationVO.getProductNum());
		
		ProductVO productVO = new ProductVO();
		
		//현재 로그인한 유저
		MemberVO memberVO = (MemberVO)auth.getPrincipal();	
		String username = memberVO.getUsername();
		notificationVO.setNotiSendUser(username);
		
		notificationService.notiInsert(notificationVO);
		
		return "redirect:/product/list";
		
		
	}
	
	@RequestMapping("notiDelete")
	public String notiDelete(NotificationVO notificationVO)throws Exception{
		int result = notificationService.notiDelete(notificationVO);
		
		return "redirect:/product/list";
	}
}
