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
	private Long productNum; //
	private int notiFrom; //1: 채팅메시지 알림 0: 가격제안
	
	private int unread;
	private String username;
	
	private ProductVO product;
}
