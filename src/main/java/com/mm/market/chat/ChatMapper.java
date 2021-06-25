package com.mm.market.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {

	public List<ChatVO> getVO(ChatVO chatVO) throws Exception;
	
	public List<ChatVO> chatList(ChatVO chatVO) throws Exception;
	
	public String getOtherProfile(ChatVO chatVO) throws Exception;
	
	public int countUnread(ChatVO chatVO) throws Exception;
	
	public List<ChatVO> roomContentList(ChatVO chatVO) throws Exception;
	
	public int chatReadChk(ChatVO chatVO) throws Exception;
	
	public int chatSendInList(ChatVO chatVO) throws Exception;
	
	public Integer maxRoom(ChatVO chatVO) throws Exception;
	
	public int existChat(ChatVO chatVO) throws Exception;
	
	public String selectRoom(ChatVO chatVO) throws Exception;
	
}
