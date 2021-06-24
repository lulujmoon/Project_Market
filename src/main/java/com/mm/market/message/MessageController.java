package com.mm.market.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MessageController {

	@Autowired
	private MessageMapper messageMapper;

	// 메세지 목록
	@RequestMapping(value = "/messageList.do")
	public String messageList(HttpServletRequest request, HttpSession session) {

		String username = (String) session.getAttribute("username");

		MessageVO to = new MessageVO();
		to.setUsername(username);

		// 메세지 리스트
		ArrayList<MessageVO> list = messageMapper.messageList(to);

		request.setAttribute("list", list);

		return "message/messageList";
	}

	// 메세지 목록
	@RequestMapping(value = "/messageAjaxList.do")
	public String messageAjaxList(HttpServletRequest request, HttpSession session) {

		String username = (String) session.getAttribute("username");

		MessageVO to = new MessageVO();
		to.setUsername(username);

		// 메세지 리스트
		ArrayList<MessageVO> list = messageMapper.messageList(to);

		request.setAttribute("list", list);

		return "message/messageAjaxList";
	}

	@RequestMapping(value = "/messageContentList.do")
	public String messageContentList(HttpServletRequest request, HttpSession session) {

		int room = Integer.parseInt(request.getParameter("room"));

		MessageVO to = new MessageVO();
		to.setRoom(room);
		to.setUsername((String) session.getAttribute("username"));

		// 메세지 내용을 가져온다.
		ArrayList<MessageVO> clist = messageMapper.roomContentList(to);

		request.setAttribute("clist", clist);

		return "message/messageContentList";
	}

	// 메세지 리스트에서 메세지 보내기
	@ResponseBody
	@RequestMapping(value = "/message_send_inlist.do")
	public int message_send_inlist(@RequestParam int room, @RequestParam String othername,
			@RequestParam String content, HttpSession session) {

		MessageVO to = new MessageVO();
		to.setRoom(room);
		to.setSendname((String) session.getAttribute("username"));
		to.setRecvname(othername);
		to.setMsgcontent(content);

		int flag = messageMapper.messageSendInlist(to);

		return flag;
	}

}