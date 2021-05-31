<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="./template/setting.jsp"></c:import>
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
				<div class="prd__card">
					<img class="card__img" src="../resources/images/test01.jpg">
					<div class="card__info">
						<div class="info__name">모여봐요 동물의 숲</div>
						<div class="info__price">45000원</div>
						<div class="info__date">9분 전</div>
						<div class="info__location"><i class="fas fa-map-marker-alt"></i> 세종시 아름동</div>
					</div>
				</div>
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>	
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>	
				<div class="prd__card">
					<div class="card__img"></div>
					<div class="card__info"></div>
				</div>												
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
<script type="text/javascript" src="../resources/js/index.js"></script>
</body>
</html>
