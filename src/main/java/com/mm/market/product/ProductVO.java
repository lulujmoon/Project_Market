package com.mm.market.product;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductVO {
	
	private Long productNum;
	private String productName;
	private String username;
	private String productCategory;
	private Date productDate;
	private Long productHit;
	private Long productHeart;
	private String productContent;
	private String productStatus;
	private Long productPrice;
	private boolean productNego;
	private String locationCode;
	
}
