<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/store.css">
<link rel="stylesheet" href="/resources/css/productList.css">
<title>내 상점</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<c:import url="./storeCommon.jsp"></c:import>
	<div class="board-container">
		<div class="board__nav">
			<a class="nav__item nav__selected" href="./products">판매 상품</a>
			<a class="nav__item" href="./reviews">받은 후기</a>
			<a class="nav__item">동네 생활</a>
			<a class="nav__item">찜한 상품</a>
		</div>
		<div class="board__contents">
			<c:forEach items="${buyerReviews}" var="review">
				${review.reviewContent}
			</c:forEach>
			<c:forEach items="${sellerReviews}" var="review">
				${review.reviewContent}
			</c:forEach>
		</div>
	</div>

</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/store.js"></script>
</body>
</html>