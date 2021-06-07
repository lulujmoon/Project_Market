package com.mm.market.comment;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
	
	private Long commentNum;
	private Long socialNum;
	private String commentContent;
	private String username;
	private Date commentDate;

}
