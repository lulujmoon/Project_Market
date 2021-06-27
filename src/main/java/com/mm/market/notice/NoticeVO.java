package com.mm.market.notice;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {

	private Long noticeNum;
	private String username;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
}
