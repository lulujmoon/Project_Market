package com.mm.market.product;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class ProductVO {
	
	private Long productNum;
	private String productName;
	private String username;
	private String productCategory;
	private Timestamp productDate;
	private Long productHit;
	private Long productHeart;
	private String productContent;
	private String productStatus;
	private Long productPrice;
	private boolean productNego;
	private Long locationCode;

	private List<ProductFileVO> files;

	
}
