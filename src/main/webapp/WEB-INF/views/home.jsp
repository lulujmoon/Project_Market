<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="./template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/home.css">
<link rel="stylesheet" href="/resources/css/productCard.css">
<link rel="stylesheet" href="/resources/css/socialCard.css" />
<title>레몬 마켓</title>
</head>
<body>
	
<c:import url="./template/header.jsp"></c:import>
	<div class="container">
		<div class="carousel-container">
			<div class="carousel__btn">
				<i class="fas fa-chevron-left" id="prev-btn"></i>
				<i class="fas fa-chevron-right" id="next-btn"></i>
			</div>
			<div class="carousel-slide">
				<img src="../resources/images/635788502.jpg" class="carousel-images" id="last-clone">
				<img src="../resources/images/641634667.jpg" class="carousel-images">
				<img src="../resources/images/639509788.jpg" class="carousel-images">
				<img src="../resources/images/641827905.jpg" class="carousel-images">
				<img src="../resources/images/617974702.jpg" class="carousel-images">
				<img src="../resources/images/635788502.jpg" class="carousel-images">
				<img src="../resources/images/641634667.jpg" class="carousel-images" id="first-clone">			
			</div>
		</div>
		<section class="product-container">
			<div class="section-title">
				<h2>새로 올라온 상품</h2>
				<a href="${pageContext.request.contextPath}/product/list">더 보기</a>
			</div>
			<div class="section-contents">
				<c:forEach var="i" begin="0" end="7">
					<div class="prd__card" onclick="goProductSelect(${products[i].productNum})">
						<c:if test="${products[i].files[0].fileName != null}">
							<img class="card__img" src="/resources/upload/product/${products[i].files[0].fileName}">
							<input type="hidden" id="category" value="${product[i].categoryCode}">
						</c:if>
						<c:if test="${products[i].files[0].fileName == null}">
							<img class="card__img" src="/resources/images/productDefault.jpg">
						</c:if>
						<div class="card__info">
							<div class="info__name">${products[i].productName}</div>
							<div class="hidden info__category">${products[i].categoryCode}</div>
							<div class="info__price">${products[i].productPrice}</div>
							<div class="info__date">${products[i].productDate}</div>
							<div class="info__location"><i class="fas fa-map-marker-alt"></i> ${products[i].location.locationName}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<section class="social-container">
			<div class="section-title">
				<h2>새로 올라온 글</h2>
				<a href="${pageContext.request.contextPath}/social/list">더 보기</a>
			</div>
			<div class="section-contents">
				<c:forEach var="i" begin="0" end="2">
			<div class="scl__card" onclick="goSocialSelect(${socials[i].socialNum})">
				<div class="scl__top-wrapper">
					<div class="scl__title">${socials[i].socialTitle}</div>
					<div class="scl__date">${socials[i].socialDate}</div>
				</div>
				<div class="scl__mid-wrapper">
					<div class="scl__writer">${socials[i].writer.name}</div>
					<span>&middot;</span>
					<div class="scl__location">${socials[i].location.locationName}</div>
					<span>&middot;</span>
					<div class="scl__category">${socials[i].socialCategory.categoryName}</div>
				</div>
				<div class="scl__content">${socials[i].socialContent}</div>
				<div class="scl__response">
					<div class="scl__good"><i class="far fa-heart"></i> ${socials[i].socialGood}</div>
					<div class="scl__comment"><i class="far fa-comment-dots"></i> ${socials[i].commentCount}</div>
				</div>
			</div>					
				</c:forEach>
			</div>
		</section>
	</div>

<c:import url="./template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/home.js"></script>
</body>
</html>
