package com.mm.market.review;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	
	private long reviewNum;
	private long productNum;
	private String reviewer;
	private String reviewee;
	private boolean type; //구매자는 true, 판매자는 false
	private Date reviewDate;
	private long locationCode;
	private long state;
	private long manner;
	private long response;
	private String reviewContent;

}
