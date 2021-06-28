package com.mm.market.notification;

import com.mm.market.product.ProductVO;

import lombok.Data;

@Data
public class NotificationVO {

	private long notiNum;
	private String notiSendUser;
	private String notiRecvUser;
	private String notiSendTime;
	private String notiContent;
	private String notiReadChk; // 1: 읽지않음, 0: 읽음	
	private Long productNum;
	
	private int unread;
	private String username;
	
	private ProductVO product;
	
}
