package com.mm.market.chat;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private final ChatService chatService;
	private final ProductService productService;

	@GetMapping("/product/{username}/{productName}/chatting")
	public String index(Model model, @PathVariable("username") String username, 
										@PathVariable("productName") String productName, Authentication auth) throws Exception{
		
		ProductVO productVO = new ProductVO();
		productVO.setUsername(username);
		productVO.setProductName(productName);
		productVO = productService.getNum(productVO);
		Long productNum = productVO.getProductNum();
		
		System.out.println("ProductNum : "+productNum);
			
		ChatVO chatVO = new ChatVO();
		chatVO.setProductNum(productNum);
		
		List<ChatVO> chatList = chatService.getChatList(chatVO);
		
		MemberVO memberVO = (MemberVO)auth.getPrincipal();
		username = memberVO.getUsername();
		
		
		model.addAttribute("chatList", chatList);
		model.addAttribute("account", username);
		model.addAttribute("productNum", productNum);
		
		return "product/chatting";
	}
	
	@MessageMapping("/chat/{productNum}") // 메세지가 목적지(/chat)로 전송되면 chat()메서드를 호출 //해당 url로 메세지가 전송되면 메서드를 호출
	@SendTo("/receive/chat/{productNum}") // 결과를 리턴시키는 목적지
	public ChatVO chat(@DestinationVariable Long productNum, ChatVO chatVO) throws Exception{
		chatVO.setSendDateTime(LocalDateTime.now()); // DB에는 자동으로 저장되지만 javaScript에서 출력해주기 위해 값을 한번 넣어줌
		chatVO.setProductNum(productNum);
		chatService.saveChat(chatVO); // 전송전 DB저장
		
		return chatVO;
	}
	
	
	
}
