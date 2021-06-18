package com.mm.market.comment;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVO {
	
	private Long commentNum;
	private Long socialNum;
	private String commentContent;
	private String username;
	private Timestamp commentDate;
	private Long ref;
	private Long step;
	private Long depth;

}
