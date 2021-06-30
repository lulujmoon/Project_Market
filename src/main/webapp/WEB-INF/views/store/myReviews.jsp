<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/store.css">
<link rel="stylesheet" href="/resources/css/reviewCard.css" />
<link rel="stylesheet" href="/resources/css/myReview.css" />
<title>내 상점</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
<div class="hidden alert">${alert}</div>
<div class="hidden page-value">${reviewPager.page}</div>
<div class="hidden type-value">${reviewPager.type}</div>
	<c:import url="./storeCommon.jsp"></c:import>

		<div class="board__contents">
			<c:if test="${reviews.size() == 0}">
				<div class="empty__info">
					아직 작성한 후기가 없습니다.
				</div> 
			</c:if>
			<c:forEach items="${reviews}" var="review">
				<div class="rv__card" onclick="manageContent()">
					<div class="rv__info">
						<div class="rv__product" onclick="showProduct(${review.product.productNum})">
							${review.product.productName}&nbsp;&nbsp;<i class="fas fa-external-link-alt"></i> 
						</div>
						<div class="right-wrapper">
							<div class="rv__date">
								${review.reviewDate}
							</div>
							<div class="btn-del" onclick="deleteReview(${review.reviewNum})">삭제</div>
						</div>
					</div>
					<div class="rv__rate">
						<c:if test="${review.type != 1}">
							<div class="rate-title">상품 상태</div>
							<div class="rate">${review.state}</div>
						</c:if>
						<div class="rate-title">거래 매너</div>
						<div class="rate">${review.manner}</div>
						<div class="rate-title">응답 속도</div>
						<div class="rate">${review.speed}</div>
					</div>
					<div class="rv__content">
						${review.reviewContent}
					</div>
					<div class="rv__more active">... 더 보기</div>
				</div>
			</c:forEach>
			<ul class="page-container list-page">
				<c:if test="${reviewPager.pre}">
					<li><a class="page-item arrow" href="./list?page=${reviewPager.startNum-1}"><i class="fas fa-angle-double-left"></i></a></li>
				</c:if>
				<c:forEach begin="${reviewPager.startNum}" end="${reviewPager.lastNum}" var="i">
						<li><a class="page-item code_${i}" href="${pageContext.request.contextPath}/store/${member.code}/products?page=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${reviewPager.next}">
					<li><a class="page-item arrow" href="./list?page=${reviewPager.lastNum+1}"><i class="fas fa-angle-double-right"></i></a></li>
				</c:if>
			</ul>
		</div>
		</div>
</div>	<!-- 스타팅 태그는 임포트한 부분에 포함되어 있음 -->

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/store.js"></script>
<script type="text/javascript" src="/resources/js/storeReview.js"></script>
<script type="text/javascript" src="/resources/js/myReview.js"></script>
</body>
</html>