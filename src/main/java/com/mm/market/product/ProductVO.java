package com.mm.market.product;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.mm.market.category.CategoryVO;
import com.mm.market.location.LocationVO;

import lombok.Data;

@Data
public class ProductVO {
	
	private Long productNum;
	private String productName;
	private String username;
	private Long categoryCode;
	private Timestamp productDate;
	private Long productHit;
	private Long productHeart;
	private String productContent;
	private String productStatus;
	private Long productPrice;
	private Boolean productNego; //1(true) : 제안 가능, 0(false) : 제안 불가 
	private Long locationCode;
	
	private List<ProductFileVO> files;

	private CategoryVO category;
	private LocationVO location;
	private ProductFileVO thumbnail;
	private HeartVO heart;
	
}
