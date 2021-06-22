<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productCard.css">
<link rel="stylesheet" href="/resources/css/store.css">
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
			<div class="hidden">${pager.page}</div>
			<div class="list-container">		
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
			<ul class="page-container list-page">
				<c:if test="${pager.pre}">
					<li><a class="page-item arrow" href="./list?page=${pager.startNum-1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-left"></i></a></li>
				</c:if>
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
						<li><a class="page-item code_${i}" href="${pageContext.request.contextPath}/store/${principal.code}/products?page=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pager.next}">
					<li><a class="page-item arrow" href="./list?page=${pager.lastNum+1}&categoryCode=${pager.categoryCode}&myLocation=${myLocation}&keyword=${pager.keyword}&order=${pager.order}"><i class="fas fa-angle-double-right"></i></a></li>
				</c:if>
			</ul>
		</div>
	</div>

</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/store.js"></script>
</body>
</html>