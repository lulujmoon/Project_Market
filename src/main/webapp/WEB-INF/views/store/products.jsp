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
			<div class="nav__item nav__selected">판매 상품</div>
			<div class="nav__item">받은 후기</div>
			<div class="nav__item">동네 생활</div>
		</div>
		<div class="board__contents">
				<div class="list-container">		
				<c:forEach items="${products}" var="product">
					<div class="prd__card" onclick="goSelect(${dto.productNum})">
						<c:if test="${product.files[0].fileName != null}">
							<img class="card__img" src="/resources/upload/product/${product.files[0].fileName}">
						</c:if>
						<c:if test="${product.files[0].fileName == null}">
							<img class="card__img" src="/resources/images/productDefault.jpg">
						</c:if>
						<div class="card__info">
							<div class="info__name"> ${product.productName}</div>
							<div class="info__price">${product.productPrice}</div>
							<div class="info__date">${product.productDate}</div>
							<div class="info__location"><i class="fas fa-map-marker-alt"></i> ${product.location.locationName}</div>
						</div>
					</div>
				</c:forEach>
	</div>
		</div>
	</div>

</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/store.js"></script>
</body>
</html>