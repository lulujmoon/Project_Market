package com.mm.market.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	@Autowired
	private ChatMapper chatMapper;
	
	public List<ChatVO> getChatList(ChatVO chatVO)throws Exception {
		return chatMapper.getChatList(chatVO);
	}
	
	public int saveChat(ChatVO chatVO) throws Exception {
		return chatMapper.saveChat(chatVO);
	}
	
	public int saveRoom(RoomVO roomVO) throws Exception {
		return chatMapper.saveRoom(roomVO);
	}
	
	

}
