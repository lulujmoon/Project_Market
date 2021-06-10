package com.mm.market.chat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mm.market.member.MemberVO;

@Controller
public class ChatRoomController {
	
	@Autowired
	private SimpMessagingTemplate simpMessage;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	//채팅으로 거래하기
	public String getWebSocketWithSockJs(Model model, HttpSession session, @ModelAttribute("chatRoom") ChatRoom chatRoom)throws Exception{
		
		//chat 화면에 전달해줄 parameter
		MemberVO memberVO=(MemberVO)session.getAttribute("member");
		String buyerId = memberVO.getUsername();
		String buyerName = memberVO.getName();
		chatRoom.setBuyerId(buyerId);
		chatRoom.setBuyerName(buyerName);
		
		//이미 chatRoom이 만들어져 있는지 확인
		if(chatRoomService.countByChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId()) > 0) {
			//get ChatRoomInfo
			ChatRoom chatRoomTemp = chatRoomService.findByChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId());
			//load existing chat history
			List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomTemp);
			//transfer chatHistory Model to View
			model.addAttribute("chatHistory", chatHistory);
		} else {
			//chatRoom 생성
			chatRoomService.addChatRoom(chatRoom);
			//textFile 생성
			chatRoomService.createFile(chatRoom.getProductNum(), chatRoomService.getChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId()));
		}
		
		//chatRoom Add시 생성될 chatNum
		chatRoom.setChatNum(chatRoomService.getChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId()));
		
		//chatRoom 객체 Model에 저장해서 view로 전달
		model.addAttribute("chatRoomInfo", chatRoom);
		
		
		return "chatBroadcastProduct";
	}
	
	
	@MessageMapping("/broadcast")
	public void send(ChatRoom chatRoom) throws Exception{
		
		chatRoom.setSendTime(TimeUtils.getCurrentTimeStamp());
		//append message to txtFile
		chatRoomService.appendMessage(chatRoom);
		
		Long chatNum = chatRoom.getChatNum();
		String url = "/user/" + chatNum + "/queue/messages";
		simpMessage.convertAndSend(url, new ChatRoom(chatRoom.getContent(), chatRoom.getSenderName(), chatRoom.getSendTime(), chatRoom.getSenderId()));
	}

}
