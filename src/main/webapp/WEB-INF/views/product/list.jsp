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
		<h3>카테고리</h3>
		<ul class="top__list">
			<c:forEach items="${categories}" var="category">
				<li class="top__item"><a href="#" onclick="goListByCategory(${category.categoryCode}, '${pager.keyword}')">${category.categoryName}</a></li>
			</c:forEach>
		</ul>
		
		<h3>지역</h3>
		<ul class="top__list">
			<li class="top__item-all"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=-1&keyword=${pager.keyword}">전체</a></li>
			<c:forEach begin="0" end="2" var="i">
				<li class="top__item"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${i}&keyword=${pager.keyword}">${locations[i].locationName}</a></li>
			</c:forEach>
		</ul>
	</div>

	<div class="list-container">
		<div class="order-container">
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
				<li class="page-item"><a class="page-link p" href="#" title="${pager.startNum-1}">Previous</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link p" href="./list?page=${i}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-link p" href="#" onclick="goPage(${pager.keyword}, ${pager.lastNum+1})">Next</a></li>
			</c:if>
		</ul>
	</div>

</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/productList.js"></script>
</body>
</html>