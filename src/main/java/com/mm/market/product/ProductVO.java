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
	@NotEmpty(message = "상품 제목을 입력해주세요")
	private String productName;
	private String username;
	private Long categoryCode;
	private Timestamp productDate;
	private Long productHit;
	private Long productHeart;
	@NotEmpty(message = "상품설명을 입력해주세요")
	private String productContent;
	private String productStatus;
	@NotEmpty(message = "가격을 입력해주세요")
	private Long productPrice;
	private Boolean productNego;
	private Long locationCode;
	
	@NotEmpty(message = "파일을 첨부해주세요")
	private List<ProductFileVO> files;

	private CategoryVO category;
	private LocationVO location;
	private ProductFileVO thumbnail;
	
}
