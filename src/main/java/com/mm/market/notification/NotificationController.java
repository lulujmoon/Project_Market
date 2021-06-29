package com.mm.market.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("select")
	public String notiSelect(NotificationVO notificationVO) throws Exception{
		
		return "redirect:./list";
	}
	
	@GetMapping("list")
	public String notiList(Authentication auth, HttpServletRequest request) throws Exception{
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		
		NotificationVO notificationVO = new NotificationVO();
		notificationVO.setNotiRecvUser(username);
		
		List<NotificationVO> list = notificationService.notiList(notificationVO);
		System.out.println("Noti List : " + list);
		
		request.setAttribute("list", list);
		
		return "notification/list";
	}
	
	@PostMapping("readChk")
	public String notiReadChk(NotificationVO notificationVO)throws Exception {
		notificationService.notiReadChk(notificationVO);
		return "redirect:./list";
	}
	
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
		
		//출력 테스트
//		System.out.println("seller: "+notificationVO.getNotiRecvUser());
//		System.out.println("notiContent: "+notificationVO.getNotiContent());
//		System.out.println("productNum: "+notificationVO.getProductNum());
		
		//현재 로그인한 유저 = notiSendUser
		MemberVO memberVO = (MemberVO)auth.getPrincipal();	
		String username = memberVO.getUsername();
		notificationVO.setNotiSendUser(username);
		
		notificationService.notiInsert(notificationVO);
		
		return "redirect:/product/list";
		
		
	}
	
	@PostMapping("notiDelete")
	public String notiDelete(NotificationVO notificationVO)throws Exception{
		notificationService.notiDelete(notificationVO);
		
		return "redirect:./list";
	
	}
}
