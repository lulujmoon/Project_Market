<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productSelect.css">
<title>${product.productName}</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="top-container">
		<div class="top__images">
			<c:forEach items="${product.files}" var="file">
				<img class="image" src="/resources/upload/product/${file.fileName}">
			</c:forEach>		
		</div>
		<div class="top__info">
			<div class="top__productName">${product.productName}</div>
			<div class="priceNstatus">
				<div class="top__price">${product.productPrice}</div>
				<div class="top__status">${product.productStatus}</div>
			</div>
			<div class="top__hitNheart">
				<div class="top__hit"><i class="far fa-eye"></i> ${product.productHit}</div>
				<div class="top__heart"><i class="fas fa-heart"></i> ${product.productHeart}</div>
			</div>
			<div class="top__productDate"><i class="fas fa-clock"></i> ${product.productDate}</div>
			<div class="top__location"><i class="fas fa-map-marker-alt"></i> ${product.location.locationName}</div>
			<div class="top__btns">
				<div class="top-btn btn-contact">연락하기</div>
				<div class="top-btn btn-nego">가격 제안하기</div>
				<div class="heart" onclick="confirm()"><img id="heart" src="" width="50px" height="50px"></div>
			</div>
			<div class="hidden">
				<input id="heartNumber" type="hidden" title="${heart}">
				<input id="productNum" type="hidden" title="${product.productNum}">
			</div>
		</div>
	</div>
		<div class="seller-container">
			<div class="seller__photo"><img src="/resources/images/productDefault.jpg"></div>
			<div class="seller_name">${product.username}</div>
			<div class="seller_rating">
				<div class="rating__content"><div class="rating__title">상품 상태</div> <div class="rate">6</div> (15)</div>
				<div class="rating__content"><div class="rating__title">거래 매너</div> <div class="rate">7</div> (23)</div>
				<div class="rating__content"><div class="rating__title">응답 속도</div> <div class="rate">8</div> (23)</div>
			</div>
		</div>
		<div class="product-content">
			${product.productContent}
		</div>
</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/productSelect.js"></script>
</body>
</html>