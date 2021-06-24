package com.mm.market.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mm.market.member.MemberVO;

@Controller
@RequestMapping("/chat/**")
public class ChatController {
	
	@Autowired
	private ChatService chatService;

	//메세지 목록
	@RequestMapping("chatList")
	public String chatList(Authentication auth, HttpServletRequest request) throws Exception {
		
		MemberVO memberVO= (MemberVO)auth.getPrincipal();
//		System.out.println("auth : "+memberVO.getUsername());
		System.out.println(memberVO);
		String username = memberVO.getUsername();
		
		ChatVO chatVO = new ChatVO();
		chatVO.setUsername(username);
		//System.out.println(chatVO);
		
		List<ChatVO> list = chatService.chatList(chatVO);
		System.out.println("chatList : "+list);
		
		request.setAttribute("list", list);
		
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
		
		System.out.println(request);
		int room = Integer.parseInt(request.getParameter("room"));
		//System.out.println(room);
		
		ChatVO chatVO = new ChatVO();
		chatVO.setRoom(room);
		MemberVO memberVO= (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		chatVO.setUsername(username);
		System.out.println(chatVO);
		List<ChatVO> clist = chatService.getVO(chatVO);
		
		for(int i=0;i<clist.size();i++) {
			clist.get(i).setOtherUser(clist.get(i).getRecvUser());
		}
		
		System.out.println(clist);
		
		//메세지 내용 가져오기
		clist = chatService.roomContentList(chatVO);
		
		request.setAttribute("clist", clist);
		
		return "chat/chatContentList";
	}
	
	
	//메세지 리스트에서 메세지 보내기
	@ResponseBody
	@RequestMapping("chatSendInList")
	public int chatSendInList(@RequestParam int room, @RequestParam String otherUser, @RequestParam String content, Authentication auth) throws Exception {
		ChatVO chatVO = new ChatVO();
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		String username = memberVO.getUsername();
		
		chatVO.setRoom(room);
		chatVO.setSendUser(username);
		chatVO.setRecvUser(otherUser);
		chatVO.setContent(content);
		
		int flag = chatService.chatSendInList(chatVO);
		
		return flag;
	}
	
	
}
