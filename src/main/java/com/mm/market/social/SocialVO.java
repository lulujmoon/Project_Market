package com.mm.market.social;

import java.sql.Date;

import lombok.Data;

@Data
public class SocialVO {

	private Long socialNum;
	private String socialTitle;
	private String socialCategory;
	private String username;
	private String socialContent;
	private Date socialDate;
	
}
