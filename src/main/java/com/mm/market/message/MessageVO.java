package com.mm.market.message;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class MessageVO {
		private int no;
		private int room;
		private String sendname;
		private String recvname;
		private Timestamp sendtime;
		private Timestamp readtime;
		private String msgcontent;
		private int readcheck;
		
		
		private String othername;
		
		private String name;
		
		private String username;
		
		private int unread;


	

	
}
