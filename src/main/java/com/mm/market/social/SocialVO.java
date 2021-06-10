package com.mm.market.social;

import java.sql.Date;
import java.util.List;

import com.mm.market.socialCategory.SocialCategoryVO;

import lombok.Data;

@Data
public class SocialVO {

	private Long socialNum;
	private String socialTitle;
	private Long categoryCode;
	private String username;
	private String socialContent;
	private Date socialDate;
	
	private List<SocialFileVO> file;
	private SocialCategoryVO socialCategory;

}
