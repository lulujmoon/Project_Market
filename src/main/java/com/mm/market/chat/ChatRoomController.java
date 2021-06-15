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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mm.market.member.MemberVO;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
public class ChatRoomController {
	
	@Autowired
	private SimpMessagingTemplate simpMessage;
	
	@Autowired
	private ChatRoomService chatRoomService;
	
	//채팅으로 거래하기
	@RequestMapping(value = "/chatMessage", method = RequestMethod.GET)
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
	
	@RequestMapping("/chatread/chatroom/ajax")
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
	
	@RequestMapping("/chatread/product/ajax")
	public void ajaxChatProductRead(@RequestBody Map<String, ?> json)throws Exception {
		JSONObject jsn = new JSONObject(json);
		String chatNumStr = (String)jsn.get("chatNum");
		Long chatNum = Long.parseLong(chatNumStr);
		chatRoomService.updateChatReadBuy(chatNum, 1);
	}
	
	@GetMapping("/chatList")
	public String getChatList(Model model, HttpSession session) throws Exception {
		return "chatList";
	}
	
	
	@RequestMapping(value = "/chatRoom/{productNum}/{buyerId}", method = RequestMethod.GET)
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
	
	@RequestMapping(value="/chatUnreadAlert/ajax", method=RequestMethod.POST)
	@ResponseBody
	public int chatUnread(@RequestBody Map<String, ?> json) {
		
		JSONObject jsn = new JSONObject(json);
		String username = (String) jsn.get("username");
    	int messages = chatRoomService.getUnreadMessage(username);

		return messages;
	}
	
	@RequestMapping(value = "/chatUnreadMessageInfo/ajax", method = RequestMethod.POST)
	@ResponseBody
	public String chatListUnread(@RequestBody Map<String, ?> json) {
		//ajax가 전송한 String을 key, value로 구분하기 위해 Json Object convert
		JSONObject jsn = new JSONObject(json);
		//JSON.get([mapped name])으로 value 추출하기
		String username = (String) jsn.get("username");
		//username에 해당되는 모든 chatRoom select 받기
		List<ChatList> chatRoomList = chatRoomService.findByUsername(username);
		//chatRoom 정보는 JSON Array에 저장됨
		JSONArray ja = new JSONArray();
		//username에 해당되는 읽지 않은 chatRoom select 받기
		List<Integer> unreadChatNum = chatRoomService.getUnreadChatRoom(username);
		
		for(ChatList chatList : chatRoomList) {
			//chatRoom 정보를 JSON Object에 Put해줌, chatRoom이 반복문에서 넘어갈 때마다 객체 초기화
			JSONObject jo = new JSONObject();
			jo.put("productNum", chatList.getProductNum());
			jo.put("buyerId", chatList.getBuyerId());
			jo.put("productImg", chatList.getProductImg());
			//리스트에 출력할 상대방 username 확인
			if(chatList.getBuyerId().equals(username)) {				
				jo.put("senderName", chatList.getSenderName());
			} else {
				jo.put("senderName", chatList.getBuyerName());
			}
			
			jo.put("productName", chatList.getProductName());
			//읽지 않은 chatRoom이 0개일때
			if(unreadChatNum.size()==0) {
				jo.put("messageUnread", "");
			}else {
				//읽지 않은 chatRoomNum들과 현재 chatRoomNum 대조 후 처리
				for(int ele : unreadChatNum) {
					if(chatList.getChatNum() == ele) {
						jo.put("messageUnread", "새 메세지");
						break;
					} else {
						jo.put("messageUnread","");
					}
				}
			}
			ja.add(jo);
		}
		//JavaScript에 parsing할 수 있도록 JSONObject에 Array를 담아줌
		JSONObject jsnResult = new JSONObject();
		jsnResult.put("chatList", ja);
		//String으로 변환해주면 끝, 프런트<->백엔드 전달은 String으로 이루어지며 형식은 JSON을 선택했음
		String result = jsnResult.toString();
		return result;
	}
	
	
	@RequestMapping(value = "/chatList/ajax", method=RequestMethod.POST)
	@ResponseBody
	public String chatList(@RequestBody Map<String, ?> json) {
		JSONObject jsn = new JSONObject(json);
		String username = (String)jsn.get("username");
		List<ChatList> chatRoomList = chatRoomService.findByUsername(username);
		JSONArray ja = new JSONArray();
		
		for(ChatList chatList :  chatRoomList) {
			JSONObject jo = new JSONObject();
			jo.put("productImg", chatList.getProductImg());
			ja.add(jo);
		}
		JSONObject jsnReult = new JSONObject();
		jsnReult.put("chatList", ja);
		String result = jsnReult.toString();
		System.out.println("chatResult toString : "+result);
		
		return result;
	}
	
	
}
