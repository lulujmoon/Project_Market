package com.mm.market.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {

	public List<ChatVO> getChatList(ChatVO chatVO) throws Exception;
	
	public int saveChat(ChatVO chatVO) throws Exception;
	
	public int saveRoom(RoomVO roomVO) throws Exception;
}
