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
	<div class="list-container">		
				<c:forEach items="${list}" var="dto">
					<div class="prd__card">
						<img class="card__img" src="/images/바탕화면.jpg">
						<div class="card__info">
							<div class="info__name"><a href="./select?productNum=${dto.productNum}"> ${dto.productName}</a></div>
							<div class="info__price">45000원</div>
							<div class="info__date">${dto.productDate}</div>
							<div class="info__location"><i class="fas fa-map-marker-alt"></i> ${dto.productDate}</div>
						</div>
					</div>
				</c:forEach>
	</div>
	
	<div class="page-container">
		<ul class="pagination">
			<c:if test="${pager.pre}">
				<li class="page-item"><a class="page-link p" href="#" title="${pager.startNum-1}">Previous</a></li>
			</c:if>
	
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link p" href="#" title="${i}">${i}</a></li>
			</c:forEach>
	
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-link p" href="#" title="${pager.lastNum+1}">Next</a></li>
			</c:if>
		</ul>
	
		<a href="./insert" class="btn btn-primary" role="button">Write</a>
		
	</div>
</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
</body>
</html>