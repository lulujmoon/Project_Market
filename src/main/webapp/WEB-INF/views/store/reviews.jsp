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
<link rel="stylesheet" href="/resources/css/reviewCard.css" />
<title>내 상점</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<c:import url="./storeCommon.jsp"></c:import>

		<div class="board__contents">
			<c:forEach items="${buyerReviews}" var="review">
				<div class="rv__card">
					<div class="rv__info">
						<div class="rv__product">
							${review.product.productName}&nbsp;&nbsp;<i class="fas fa-external-link-alt"></i>					
						</div>
						<div class="rv__date">${review.reviewDate}</div>
					</div>
					<div class="rv__rate">
						<div class="rate-title">
							상품 상태
						</div><div class="rate"></div>
						<div class="rate-title">
							거래 매너
						</div>
						<div class="rate"></div>
						<div class="rate-title">
							응답 속도
						</div>
						<div class="rate"></div>
					</div>
					<div class="rv__content">
						${review.reviewContent}
					</div>
				</div>
			</c:forEach>
			<c:forEach items="${sellerReviews}" var="review">
				${review.reviewContent}
				${review.reviewerVO.name}
				${review.product.productName}
			</c:forEach>
		</div>
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
</div>	<!-- 스타팅 태그는 임포트한 부분에 포함되어 있음 -->

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/functions.js"></script>
<script type="text/javascript" src="/resources/js/store.js"></script>
</body>
</html>