package com.mm.market.chat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.mm.market.member.MemberVO;

import net.minidev.json.JSONObject;

@Controller
public class ChatRoomController {
	
	@Autowired
	private SimpMessagingTemplate simpMessage;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	//채팅으로 거래하기
	@GetMapping("/chatMessage")
	public String getWebSocketWithSockJs(Model model, Authentication auth, @ModelAttribute("chatRoom") ChatRoom chatRoom)throws Exception{
		
		//chat 화면에 전달해줄 parameter
		MemberVO memberVO=(MemberVO)auth.getPrincipal();
		System.out.println(memberVO);
		String buyerId = memberVO.getUsername();
		String buyerName = memberVO.getName();
		chatRoom.setBuyerId(buyerId);
		chatRoom.setBuyerName(buyerName);
		
		System.out.println(chatRoom);
		
		System.out.println("productNum : "+chatRoom.getProductNum());
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
			//chatRoom Add시 생성될 chatNum
			chatRoom.setChatNum(chatRoomService.getChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId()));
			
			//textFile 생성
			chatRoomService.createFile(chatRoom.getProductNum(), chatRoomService.getChatNum(chatRoom.getProductNum(), chatRoom.getBuyerId()));
		}
		
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
	
	@GetMapping("/chatread/chatroom/ajax")
	public void ajaxChatRoomRead(@RequestBody Map<String, ?> json) throws Exception{
		JSONObject jsn = new JSONObject(json);
		String chatNumStr = (String)jsn.get("chatNum");
		Long chatNum = Long.parseLong(chatNumStr);
		String flag = (String) jsn.get("flag");
		if(flag.equals("sell")) {
			chatRoomService.updateChatReadSell(chatNum, 1);
		} else {
			chatRoomService.updateChatReadBuy(chatNum, 1);
		}
		
	}
	
	
	public void ajaxChatProductRead(@RequestBody Map<String, ?> json)throws Exception {
		JSONObject jsn = new JSONObject(json);
		String chatNumStr = (String)jsn.get("chatNum");
		Long chatNum = Long.parseLong(chatNumStr);
		chatRoomService.updateChatReadBuy(chatNum, 1);
	}
	
	
	public String getChatList(Model model, HttpSession session) throws Exception {
		return "chatList";
	}
	
	
	@GetMapping("/chatRoom/{productNum}/{buyerId}")
	public String getChatRoom(@PathVariable Map<String, String> requestVar, Model model) throws Exception{
		
		String buyerId = requestVar.get("buyerId");
		Long productNum = Long.parseLong(requestVar.get("productNum"));
		
		//read chatHistory
		ChatRoom chatRoomRead = chatRoomService.findByChatNum(productNum, buyerId);
		List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomRead);
		model.addAttribute("chatHistory", chatHistory);
		
		Long chatNum = chatRoomService.getChatNum(productNum, buyerId);
		String productName = chatRoomRead.getProductName();
		String sellerId = chatRoomRead.getSellerId();
		
		model.addAttribute("chatNum", chatNum);
		model.addAttribute("productNum", productNum);
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("sellerId", sellerId);
		model.addAttribute("productName", productName);
		
		return "chatBroadcastChatRoom";
	}

}
