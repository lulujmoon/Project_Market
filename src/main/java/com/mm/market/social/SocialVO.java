package com.mm.market.social;

import java.sql.Timestamp;
import java.util.List;

import com.mm.market.location.LocationVO;
import com.mm.market.member.MemberVO;
import com.mm.market.socialCategory.SocialCategoryVO;

import lombok.Data;

@Data
public class SocialVO {

	private Long socialNum;
	private String socialTitle;
	private Long categoryCode;
	private String username;
	private String socialContent;
	private Timestamp socialDate;
	private Long socialGood;
	private Long locationCode;

	private List<SocialFileVO> files;

	private MemberVO writer;
	private LocationVO location;
	private SocialCategoryVO socialCategory;
	private GoodVO goods;

}