<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productList.css">
<link rel="stylesheet" href="/resources/css/productCard.css">
<title>상품 목록</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="hidden">${pager.categoryCode}</div>
<div class="hidden">${myLocation}</div>
<div class="hidden">${pager.order}</div>
<div class="hidden">${pager.page}</div>

<div class="container">
	<div class="top-container">
		<h3 class="top__title">카테고리</h3>
		<ul class="top__list list-category">
			<li class="top__item-category code_0"><a href="./list?page=1&keyword=${pager.keyword}">전체</a></li>
			<c:forEach begin="0" end="${categories.size()-1}" var="i">
				<li class="top__item-category code_${i+1}"><a href="./list?page=1&categoryCode=${categories[i].categoryCode}&keyword=${pager.keyword}&myLocation=${myLocation}">${categories[i].categoryName}</a></li>
			</c:forEach>
		</ul>
		<h3 class="top__title">지역</h3>
		<ul class="top__list list-myLocation">
			<li class="top__item-location code_0"><a href="./list?page=1&categoryCode=${pager.categoryCode}&keyword=${pager.keyword}">전체</a></li>
			<c:if test="${locations.size()!=0}">
				<c:forEach begin="1" end="3" var="i">
					<li class="top__item-location code_${i}"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${i}&keyword=${pager.keyword}">${locations[i].locationName}</a></li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="list-container">
		<c:if test="${products.size()>0}">
			<div class="order-container list-order">
				<a class="order-content code_date" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=date">최신순</a>
				<a class="order-content code_lowPrice" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=lowPrice">저가순</a>
				<a class="order-content code_highPrice" href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=highPrice">고가순</a>
			</div>
			<div class="product-container">
				<c:forEach items="${products}" var="product">
					<div class="prd__card" onclick="goSelect(${product.productNum})">
						<c:if test="${product.files[0].fileName != null}">
							<img class="card__img" src="/resources/upload/product/${product.files[0].fileName}">
							<input type="hidden" class="category" value="${product.categoryCode}">
						</c:if>
						<c:if test="${product.files[0].fileName == null}">
							<img class="card__img" src="/resources/images/productDefault.jpg">
						</c:if>
						<div class="card__info">
							<div class="info__name"> ${product.productName}</div>
							<c:if test="${product.productPrice eq 0 && product.categoryCode != 14}">
								<div class="info__price">무료나눔</div>
							</c:if>
							<c:if test="${product.productPrice ne 0 || product.categoryCode == 14}">
								<div class="info__price">${product.productPrice}</div>
							</c:if>
							<div class="info__date">${product.productDate}</div>
							<div class="info__location"><i class="fas fa-map-marker-alt"></i> ${product.location.locationName}</div>
						</div>
					</div>
				</c:forEach>
				<input type="hidden" id="size" value="${products.size()}">
			</div>
			<ul class="page-container list-page">
				<c:if test="${pager.pre}">
					<li><a class="page-item arrow" href="./list?page=${pager.startNum-1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-left"></i></a></li>
				</c:if>
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<li><a class="page-item code_${i}" href="./list?page=${i}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}">${i}</a></li>
				</c:forEach>
				<c:if test="${pager.next}">
					<li><a class="page-item arrow" href="./list?page=${pager.lastNum+1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-right"></i></a></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${products.size() == 0}">
			<div class="zero-container">
				일치하는 상품이 없습니다.
			</div>
		</c:if>
	</div>

</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/productList.js"></script>
</body>
</html>