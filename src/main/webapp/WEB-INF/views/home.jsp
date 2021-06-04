<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="./template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/home.css">
<title>Home</title>
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
				<img src="../resources/images/635788502.jpg" class="carousel-images" id="lastClone">
				<img src="../resources/images/641634667.jpg" class="carousel-images">
				<img src="../resources/images/639509788.jpg" class="carousel-images">
				<img src="../resources/images/641827905.jpg" class="carousel-images">
				<img src="../resources/images/617974702.jpg" class="carousel-images">
				<img src="../resources/images/635788502.jpg" class="carousel-images">
				<img src="../resources/images/641634667.jpg" class="carousel-images" id="firstClone">			
			</div>
		</div>
		<section class="home__products">
			<div class="section-title">
				<h2>새로 올라온 상품</h2>
				<a href="${pageContext.request.contextPath}/product/list">더 보기</a>
			</div>
			<div class="section-contents">
				<c:forEach var="i" begin="0" end="7">
					<div class="prd__card" onclick="goSelect(${products[i].productNum})">
						<img class="card__img" src="../resources/images/test01.jpg">
						<div class="card__info">
							<div class="info__name">${products[i].productName}</div>
							<div class="info__price">${products[i].productPrice}</div>
							<div class="info__date">9분 전</div>
							<div class="info__location"><i class="fas fa-map-marker-alt"></i> ${products[i].location.locationName}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<section class="home__socials">
			<div class="section-title">
				<h2>새로 올라온 글</h2>
				<a href="#">더 보기</a>
			</div>
			<div class="section-contents">
				<div class="soc__article"></div>
				<div class="soc__article"></div>
				<div class="soc__article"></div>
			</div>
		</section>
	</div>

<c:import url="./template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/home.js"></script>
</body>
</html>
