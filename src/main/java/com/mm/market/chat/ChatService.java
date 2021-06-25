package com.mm.market.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	@Autowired
	private ChatMapper chatMapper;
	
	
	public List<ChatVO> getVO(ChatVO chatVO) throws Exception {
		return chatMapper.getVO(chatVO);
	}
	
	
	
	public List<ChatVO> chatList(ChatVO chatVO) throws Exception{
		
		String username = chatVO.getUsername();
		
		List<ChatVO> list = chatMapper.chatList(chatVO);		
		
		for(ChatVO chat : list) {
			chat.setUsername(username);
			int unread = chatMapper.countUnread(chat);
			String profile = chatMapper.getOtherProfile(chat);
			chat.setUnread(unread);
			chat.setProfile(profile);
			if(username.equals(chat.getSendUser())) {
				chat.setOtherUser(chat.getRecvUser());
				System.out.println("otherUser : "+chat.getOtherUser());
			} else {
				chat.setOtherUser(chat.getSendUser());
				System.out.println("otherUser : "+chat.getOtherUser());
			}
		}
		return list;
	}


	public List<ChatVO> roomContentList(ChatVO chatVO) throws Exception {
		
		System.out.println("room : " + chatVO.getRoom());
		System.out.println("recv user : " + chatVO.getRecvUser());
		System.out.println("username : " + chatVO.getUsername());
		
		
		List<ChatVO> clist = (List<ChatVO>)chatMapper.roomContentList(chatVO);
		
		chatMapper.chatReadChk(chatVO);
		
		return clist;
	}
	
	public int chatSendInList(ChatVO chatVO) throws Exception{
		
		if(chatVO.getRoom()==0) {
			int existChat = chatMapper.existChat(chatVO);
			if(existChat==0) {
				int maxRoom = chatMapper.maxRoom(chatVO);
				chatVO.setRoom(maxRoom+1);
			}else {
				int room = Integer.parseInt(chatMapper.selectRoom(chatVO));
				chatVO.setRoom(room);
			}
		}
		int flag = chatMapper.chatSendInList(chatVO);
		return flag;
	}
	
	
	
}
