package com.mm.market.chat;

import java.io.Serializable;

import lombok.Data;

@Data
public class Message implements Serializable{

	private String author;
	private String content;
	private String timestamp;
	
	public Message() {
		
	}
	
	public Message(String author, String content) {
		this.author = author;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message{" +
				"author='" + author + '\'' +
				", content='" + content + '\'' + 
				", timestamp='" + timestamp + '\'' +
				'}';
	}
}
