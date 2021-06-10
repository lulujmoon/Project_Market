package com.mm.market.chat;

import java.sql.Timestamp;

import lombok.Data;


public @Data class ChatRoom {
	
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
	
	//not in DB
	private String content;
	private String sendTime;
	private String senderName;
	private String senderId;
	private String productName;
	
	public ChatRoom (Long chatNum, Long productNum, String sellerId, String buyerId, String fileName, Timestamp createdDate,String sellerName, String buyerName, int chatReadSell, int chatReadBuy) {
		super();
		this.chatNum=chatNum;
		this.productNum=productNum;
		this.sellerId=sellerId;
		this.buyerId=buyerId;
		this.fileName=fileName;
		this.createdDate=createdDate;
		this.sellerName=sellerName;
		this.buyerName=buyerName;
		this.chatReadSell=chatReadSell;
	}
	
	
	public ChatRoom() {
		// TODO Auto-generated constructor stub
	}
	
	public ChatRoom(String content, String senderName, String senderId, String sendTime) {
		this.content=content;
		this.senderName=senderName;
		this.senderId=senderId;
		this.sendTime=sendTime;
	}
	

}
