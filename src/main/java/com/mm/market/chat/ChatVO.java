package com.mm.market.chat;

import lombok.Data;

@Data
public class ChatVO {

	private String num;
	private int room;
	private String sendUser;
	private String recvUser;
	private String sendTime;
	private String readTime;
	private String content;
	private String readChk; // 1: 읽지않음, 0: 읽음
	private Long productNum; //null: 가격제안 수락, null이 아니면 채팅메시지
	
	//현재 사용자의 메세지 상대 Username을 담는다
	private String otherUser;
	
	//현재 사용자의 메세지 상대 profile을 담는다
	private String profile;
	
	//현재 사용자 Username
	private String username;
	
	//안읽은 메세지 갯수
	private int unread;
	
	
	
}
