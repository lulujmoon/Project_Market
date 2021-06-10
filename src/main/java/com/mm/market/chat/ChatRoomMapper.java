package com.mm.market.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRoomMapper {

	public void addChatRoom(ChatRoom chatRoom) throws Exception;
	
	public void appendMessage(ChatRoom chatRoom)throws Exception;
	
	public ChatRoom findByChatNum(Long productNum, String buyerId);
	
	public List<ChatList> findByUsername(String username);
	
	public Long countByChatNum(Long chatNum, String buyerId);
	
	public Long getChatNum(Long productNum, String buyerId);
	
	public void updateFileName(Long chatNum, String fileName);
	
	public void updateChatReadBuy(Long chatNum, int chatReadBuy);
	
	public void updateChatReadSell(Long chatNum, int chatReadSell);
	
	public int getUnreadMessage(String username);
	
	public List<Integer> getUnreadChatRoom(String username);
	
	
}
