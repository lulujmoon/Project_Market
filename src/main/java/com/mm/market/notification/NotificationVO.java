package com.mm.market.notification;

public class NotificationVO {

	private String notiNum;
	private String notiSendUser;
	private String notiRecvUser;
	private String notiSendTime;
	private String notiContent;
	private String notiReadChk; // 1: 읽지않음, 0: 읽음
	
	//안읽은 메세지 갯수
	private int unread;
	
}
