package com.mm.market.notice;

import lombok.Data;

@Data
public class NoticeVO {

	private Long noticeNum;
	private String username;
	private String noticeTitle;
	private String noticeContent;
	private Data noticeDate;
}
