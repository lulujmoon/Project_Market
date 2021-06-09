package com.mm.market.chat;

import java.sql.Timestamp;

import lombok.Data;


public @Data class ChatVO {
	
	private Long chatNum;
	private Long productNum;
	private String sellerId;
	private String buyerId;
	private String fileName;
	private Timestamp createdDate;
	private String sellerName;
	private String buyerName;
	
	//not in DB
	private String content;
	private String sendTime;
	private String senderName;
	private String productName;
	
	public ChatVO (Long chatNum, Long productNum, String sellerId, String buyerId, String fileName, Timestamp createdDate,String sellerName, String buyerName) {
		super();
		this.chatNum=chatNum;
		this.productNum=productNum;
		this.sellerId=sellerId;
		this.buyerId=buyerId;
		this.fileName=fileName;
		this.createdDate=createdDate;
		this.sellerName=sellerName;
		this.buyerName=buyerName;
	}
	
	
	public ChatVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ChatVO(String content, String senderName, String sendTime) {
		this.content=content;
		this.senderName=senderName;
		this.sendTime=sendTime;
	}
	

}
