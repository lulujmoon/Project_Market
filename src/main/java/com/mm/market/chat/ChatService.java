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
	
	
	//메세지 리스트
	public List<ChatVO> chatList(ChatVO chatVO) throws Exception{
		
		String username = chatVO.getUsername();
		//메세지 리스트에 나타낼 것들 가져오기 - 최근메세지, 보낸사람 profile사진, 보낸사람 username
		List<ChatVO> list = chatMapper.chatList(chatVO);		
		
		for(ChatVO chat : list) {
			chat.setUsername(username);
			//현재 사용자가 해당 room에서 안읽은 메세지의 갯수를 가져온다
			int unread = chatMapper.countUnread(chat);
			//현재 사용자가 메세지를 주고받는 상대의 profile을 가져온다 
			String profile = chatMapper.getOtherProfile(chat);
			//안읽은 메세지 갯수를 chat에 set
			chat.setUnread(unread);
			//메세지 상대의 프로핖 사진을 chat에 set
			chat.setProfile(profile);
			//메세지 상대 username을 세팅 otherUser
			if(username.equals(chat.getSendUser())) {
				chat.setOtherUser(chat.getRecvUser());
				System.out.println("otherUser : "+chat.getOtherUser());
			} else {
				chat.setOtherUser(chat.getSendUser());
			}
		}
		return list;
	}

	//room별 메세지 내용을 가져온다
	public List<ChatVO> roomContentList(ChatVO chatVO) throws Exception {
		
		
		//메세지 내역을 가져옴
		List<ChatVO> clist = (List<ChatVO>)chatMapper.roomContentList(chatVO);
		System.out.println("메세지 내역 가져오기 서비스 : "+clist);		
		
//		for(int i=0;i<clist.size();i++) {
//			System.out.println("room : " + clist.get(i).getRoom());
//		System.out.println("recv user : " + clist.get(i).getRecvUser());
//		System.out.println("username : " + clist.get(i).getUsername());
//		clist.get(i).setOtherUser(clist.get(i).getRecvUser());
//		}
		
		//해당 방의 메세지들 중 받는 사람이 현재 사용자의 username인 메세지를 모두 읽음 처리
		chatMapper.chatReadChk(chatVO);
		
		return clist;
	}
	
	//메세지 List에서 메세지를 보낸다
	public int chatSendInList(ChatVO chatVO) throws Exception{
		
		//메세지 리스트에서 보낸건지 프로필에서 보낸건지 구분하기 위함
		if(chatVO.getRoom()==0) { //room이 0이라며 프로필에서 보낸것
			int existChat = chatMapper.existChat(chatVO);
			//프로필에서 보낸것중 메세지 내역이 없어서 첫 메세지가 될 경우를 구분하기 위함
			if(existChat==0) { //메세지 내역이 없어서 0이면 chat테이블의 room 최댓값을 구해서 chatVO에 set
				int maxRoom = chatMapper.maxRoom(chatVO);
				chatVO.setRoom(maxRoom+1);
			}else { //메세지 내역이 있다면 해당 room번호를 가져옴
				int room = Integer.parseInt(chatMapper.selectRoom(chatVO));
				chatVO.setRoom(room);
			}
		}
		int flag = chatMapper.chatSendInList(chatVO);
		return flag;
	}
	
	
	
}
