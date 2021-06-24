package com.mm.market.review;

import java.sql.Timestamp;
import java.util.Date;

import com.mm.market.member.MemberFileVO;
import com.mm.market.member.MemberVO;
import com.mm.market.product.ProductVO;

import lombok.Data;

@Data
public class ReviewVO {
	
	private Long reviewNum;
	private Long productNum;
	private String reviewer;
	private String reviewee;
	private boolean type; //구매자는 true, 판매자는 false
	private Timestamp reviewDate;
	private Long locationCode;
	private Long state;
	private Long manner;
	private Long speed;
	private String reviewContent;
	
	private Long avgState;
	private Long avgManner;
	private Long avgSpeed;
	private Long countState;
	private Long countManner;
	private Long countSpeed;
	
	private ProductVO product;
	private MemberVO reviewerVO;
	private MemberFileVO reviewerFile;

}
