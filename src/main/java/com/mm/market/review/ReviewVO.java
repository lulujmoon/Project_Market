package com.mm.market.review;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	
	private Long reviewNum;
	private Long productNum;
	private String reviewer;
	private String reviewee;
	private boolean type; //구매자는 true, 판매자는 false
	private Date reviewDate;
	private Long locationCode;
	private Long state;
	private Long manner;
	private Long speed;
	private String reviewContent;

}
