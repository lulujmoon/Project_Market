<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/store.css">
<title>내 상점</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<div class="profile-container">
		<img class="profile__photo" src = "/resources/images/test01.jpg">
		<div class="profile__info">
			<div class="profile__name">문정현</div>
			<div class="profile__location">세종특별자치시 아름동</div>
			<div class="profile__joinDate">2021년 6월 4일 가입</div>
		</div>
		<div class="profile__rating">
			<div class="rating__state">상품 상태 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
			<div class="rating__manner">거래 매너 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
			<div class="rating__response">응답 속도 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
		</div>
	</div>
	<div class="board-container">
		<div class="board__nav">
			<div class="nav__item">판매 상품</div>
			<div class="nav__item">받은 후기</div>
			<div class="nav__item">동네 생활</div>
		</div>
		<div class="board__contents"></div>
	</div>

</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
</body>
</html>