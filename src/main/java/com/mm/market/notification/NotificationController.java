package com.mm.market.notification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mm.market.member.MemberVO;
import com.mm.market.util.NotificationPager;

@Controller
@RequestMapping("/notification/**")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("list")
	public String notiList(Authentication auth, HttpServletRequest request, NotificationPager pager) throws Exception{
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		pager.setNotiRecvUser(username);
		
		NotificationVO notificationVO = new NotificationVO();
		notificationVO.setNotiRecvUser(username);
		
		int unread = notificationService.countUnread(notificationVO);
		System.out.println("unread : " + unread);
		
		List<NotificationVO> list = notificationService.notiList(pager);
		System.out.println("Noti List : " + list);
		
		
		request.setAttribute("notificationPager", pager);
		request.setAttribute("unread", unread);
		request.setAttribute("list", list);
		
		return "notification/list";
	}
	
	@PostMapping("readChk")
	public String notiReadChk(NotificationVO notificationVO)throws Exception {
		notificationService.notiReadChk(notificationVO);
		return "redirect:./list";
	}
	
	@GetMapping("select")
	public String notiSelect(NotificationVO notificationVO) throws Exception{
		
		return "redirect:./list";
	}
	
}
