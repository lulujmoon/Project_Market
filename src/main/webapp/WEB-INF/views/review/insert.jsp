<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/reviewInsert.css"/>
<title>후기 등록</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="hidden alert">${alert}</div>
	<form action="./insert" method="post" class="insert-form">
		<input type="hidden" name="productNum" value="${product.productNum}">
		<input type="hidden" name="locationCode" value="${product.locationCode}" />
		<input type="hidden" name="reviewee" value="${counterpart.username}">
		<input type="hidden" name="type" value="${dealType}">
		<div class="title">
			이번에 거래한 ${counterpart.name} 님의 거래 후기를 써주세요.
		</div>
		<div class="info-container">
			<div class="info__product" onclick="goProductSelect(${product.productNum})">
				<img src="/resources/upload/product/${product.files[0].fileName}" class="product__img" />
				<div class="product__nameNprice">
					<div class="product__name">${product.productName}</div>
					<div class="product__price"> ${product.productPrice}원</div>
				</div>
			</div>
		</div>
		<div class="title">
			항목을 평가해주세요. (필수)
		</div>
		<div class="rate-container">
			<c:if test="${dealType != 1}">
				<div class="rate-title">상품 상태가 설명과 같았나요?</div>
				<div class="rate-content">
					<input type="hidden" name="state" class="input state"/>
					<div class="rate-back">
						<c:forEach begin="1" end="5" var="i" >
							<span class="star star-back star_${i}"><i class="fas fa-star"></i></span>
						</c:forEach>
					</div>
					<div class="rate-front">
						<c:forEach begin="1" end="5" var="i" >
							<span class="star star-front star_${i}"><i class="fas fa-star"></i></span>
						</c:forEach>
					</div>
				</div>
			</c:if>
			<div class="rate-title">거래 매너가 좋았나요?</div>
			<div class="rate-content">
				<input type="hidden" name="manner" class="input manner"/>
				<div class="rate-back">
					<c:forEach begin="1" end="5" var="i" >
						<span class="star star-back star_${i}"><i class="fas fa-star"></i></span>
					</c:forEach>
				</div>
				<div class="rate-front">
					<c:forEach begin="1" end="5" var="i" >
						<span class="star star-front star_${i}"><i class="fas fa-star"></i></span>
					</c:forEach>
				</div>
			</div>
			<div class="rate-title">응답 속도가 빨랐나요?</div>
			<div class="rate-content">
				<input type="hidden" name="speed" class="input speed"/>
				<div class="rate-back">
					<c:forEach begin="1" end="5" var="i" >
						<span class="star star-back star_${i}"><i class="fas fa-star"></i></span>
					</c:forEach>
				</div>
				<div class="rate-front">
					<c:forEach begin="1" end="5" var="i" >
						<span class="star star-front star_${i}"><i class="fas fa-star"></i></span>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="title">
			자세한 후기를 써주세요. (선택)
		</div>
		<textarea class="review-content" name="reviewContent"></textarea>
		<div class="btn-wrapper">
			<input type="button" class="btn-submit" value="등록">
		</div>
	</form>
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/reviewInsert.js"></script>
</body>
</html>