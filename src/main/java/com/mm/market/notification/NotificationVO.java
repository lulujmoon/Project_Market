package com.mm.market.notification;

import com.mm.market.product.ProductVO;

import lombok.Data;

@Data
public class NotificationVO {
	
	private String notiNum;
	private String notiSendUser;
	private String notiRecvUser;
	private String notiSendTime;
	private String notiContent;
	private String notiReadChk; // 1: 읽지않음, 0: 읽음
	private String productNum;
	private String notiFrom; //1: 가격제안 0: 채팅
	
	private int unread;
	private String username;
	
	private ProductVO product;
}
