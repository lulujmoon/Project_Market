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
	<div class="category-container">
		<ul class="category-list">
			<c:forEach items="${categories}" var="category">
				<li><a class="category__item" href="#" onclick="goListByCategory(${category.categoryCode}, '${pager.search}')">${category.categoryName}</a></li>
			</c:forEach>
		</ul>
		
		<input type="hidden" class="category" value="category" name="categoryCode">
	</div>

	<div class="list-container">		
				<c:forEach items="${list}" var="product">
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
	
		<ul class="page-list">
			<c:if test="${pager.pre}">
				<li class="page-item"><a class="page-link p" href="#" title="${pager.startNum-1}">Previous</a></li>
			</c:if>
	
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link p" href="#" onclick="goPage('${pager.search}', ${i})">${i}</a></li>
			</c:forEach>
	
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-link p" href="#" onclick="goPage(${pager.search}, ${pager.lastNum+1})">Next</a></li>
			</c:if>
		</ul>		
		<a href="./insert" class="btn btn-primary" role="button">Write</a>
</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/productList.js"></script>
</body>
</html>