package com.mm.market.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductVO;

@Controller
@RequestMapping("/chat/**")
public class ChatController {
	
	@Autowired
	private ChatService chatService;

	//메세지 목록
	@RequestMapping("chatList")
	public String chatList(Authentication auth, HttpServletRequest request, Long productNum, Long locationCode) throws Exception {
		
		MemberVO memberVO= (MemberVO)auth.getPrincipal();
//		System.out.println("auth : "+memberVO.getUsername());
		
		String username = memberVO.getUsername();
		
		ChatVO chatVO = new ChatVO();
		chatVO.setUsername(username);
		//System.out.println(chatVO);
		
		List<ChatVO> list = chatService.chatList(chatVO);
		System.out.println("chatList : "+list);
		
		request.setAttribute("list", list);
		request.setAttribute("productNum", productNum);
		request.setAttribute("locationCode", locationCode);
		
		return "chat/chatList";
	}
	
	//메세지 목록
	@RequestMapping("chatAjaxList")
	public String chatAjaxList(HttpServletRequest request, Authentication auth) throws Exception{
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		ChatVO chatVO = new ChatVO();
		chatVO.setUsername(username);
//		System.out.println(chatVO);
		
		//메세지 리스트
		List<ChatVO> list = chatService.chatList(chatVO);
		
		request.setAttribute("list", list);
		
		return "chat/chatAjaxList";
	}
	
	
	@RequestMapping("chatContentList")
	public String chatContentList(HttpServletRequest request, Authentication auth) throws Exception {
			
		int room = Integer.parseInt(request.getParameter("room"));
		
		//System.out.println(room);
		
		ChatVO chatVO = new ChatVO();
		chatVO.setRoom(room);
		MemberVO memberVO= (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		chatVO.setUsername(username);
		System.out.println("content list chatVO : "+chatVO);
		List<ChatVO> clist = chatService.getVO(chatVO);
		
		System.out.println(clist);
		
		//메세지 내용 가져오기
		clist = chatService.roomContentList(chatVO);
		
		for(int i=0;i<clist.size();i++) {
			clist.get(i).setOtherUser(clist.get(i).getRecvUser());
		}
		System.out.println("메세지내용 가져오기 후 : "+clist);
		
		request.setAttribute("clist", clist);
		
		
		return "chat/chatContentList";
	}
	
	
	//메세지 리스트에서 메세지 보내기
	
	@RequestMapping("chatSendInList")
	public String chatSendInList(@RequestParam int room, @RequestParam String otherUser, @RequestParam String content, Authentication auth) throws Exception {
		System.out.println("otherUser : " + otherUser);
		ModelAndView mv= new ModelAndView();
		ChatVO chatVO = new ChatVO();
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		
		chatVO.setRoom(room);
		chatVO.setSendUser(username);
		System.out.println("user : "+username);
		chatVO.setRecvUser(otherUser);
		chatVO.setContent(content);
		
		ProductVO productVO = new ProductVO();
		productVO.setUsername(username);
		
		
		
		int flag = chatService.chatSendInList(chatVO);
		
		mv.addObject("flag", flag);
		return "redirect:/chat/chatList";
	}
	
	
}
