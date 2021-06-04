package com.mm.market.comment;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentVO {
	
	private Long commentNum;
	private Long socialNum;
	private String username;
	private Date commentDate;
	private String commentContent;

}
