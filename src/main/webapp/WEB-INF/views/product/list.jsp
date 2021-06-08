<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/productList.css">
<title>Insert title here</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<div class="top-container">
		<h3 class="top__title">카테고리</h3>
		<ul class="top__list">
			<c:choose>
				<c:when test="${pager.categoryCode != 0}">
					<li class="top__item-category"><a href="./list?page=1&keyword=${pager.keyword}">전체</a></li>
				</c:when>
				<c:otherwise>
					<li class="top__item-category selected"><a href="./list?page=1&keyword=${pager.keyword}">전체</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach items="${categories}" var="category">
				<c:if test="${category.categoryCode == pager.categoryCode}">
					<li class="top__item-category selected"><a href="./list?page=1&categoryCode=${category.categoryCode}&keyword=${pager.keyword}&myLocation=${myLocation}">${category.categoryName}</a></li>
				</c:if>
				<c:if test="${category.categoryCode != pager.categoryCode}">
					<li class="top__item-category"><a href="./list?page=1&categoryCode=${category.categoryCode}&keyword=${pager.keyword}&myLocation=${myLocation}">${category.categoryName}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<h3 class="top__title">지역</h3>
		<ul class="top__list">
			<c:choose>
				<c:when test="${myLocation >= 0}">
					<li class="top__item-location"><a href="./list?page=1&categoryCode=${pager.categoryCode}&keyword=${pager.keyword}">전체</a></li>
				</c:when>
				<c:otherwise>				
					<li class="top__item-location selected"><a href="./list?page=1&categoryCode=${pager.categoryCode}&keyword=${pager.keyword}">전체</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="0" end="2" var="i">
				<c:if test="${myLocation == i}">
					<li class="top__item-location selected"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${i}&keyword=${pager.keyword}">${locations[i].locationName}</a></li>
				</c:if>			
				<c:if test="${myLocation != i}">
					<li class="top__item-location"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${i}&keyword=${pager.keyword}">${locations[i].locationName}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>

	<div class="list-container">
		<div class="order-container">
			<div class="hidden order-value">${pager.order}</div>
			<a class="order-content" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=date">최신순</a>
			<a class="order-content" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=lowPrice">저가순</a>
			<a class="order-content" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=highPrice">고가순</a>
		</div>
		<div class="product-container">
			<c:forEach items="${products}" var="product">
				<div class="prd__card" onclick="goSelect(${product.productNum})">
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
		<ul class="page-container">
			<c:if test="${pager.pre}">
				<li><a class="page-item arrow" href="./list?page=${pager.startNum-1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-left"></i></a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<c:if test="${pager.page == i}">
					<li><a class="page-item current-page" href="./list?page=${i}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}">${i}</a></li>
				</c:if>
				<c:if test="${pager.page != i}">
					<li><a class="page-item" href="./list?page=${i}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}">${i}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${pager.next}">
				<li><a class="page-item arrow" href="./list?page=${pager.lastNum+1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-right"></i></a></li>
			</c:if>
		</ul>
	</div>

</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/productList.js"></script>
</body>
</html>