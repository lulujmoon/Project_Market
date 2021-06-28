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
	private Long type; //판매자 1, 구매자 2
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

}
