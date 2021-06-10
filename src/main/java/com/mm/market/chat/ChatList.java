package com.mm.market.chat;

import java.sql.Timestamp;

import lombok.Data;


public @Data class ChatList {
	
	private Long chatNum;
	private Long productNum;
	private String sellerId;
	private String buyerId;
	private String fileName;
	private Timestamp createdDate;
	private String sellerName;
	private String buyerName;
	private int chatReadSell;
	private int chatReadBuy;
	private int chatRoomRead;
	//not in DB
	private String content;
	private String sendTime;
	private String senderName;
	private String productName;
	private String productImg;
	
	public ChatList() {
		
	}


}
