<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<div class="profile-container">
<div class="divide">
	<div class="profile__photo">
		<c:if test="${principal.oauth eq false}">
		<img class="profile__img" src = "/resources/upload/member/${file.fileName}">
		</c:if>	
		<c:if test="${principal.oauth eq true}">
		<img class="profile__img" src = "/resources/upload/member/basic.PNG">
		</c:if>
		<c:if test="${principal.oauth eq false}">
		<div class="profile-hover">프로필 수정하기</div>
		</c:if>
	</div>
	 <c:if test="${principal.oauth eq false}">
	<form action="./profileUpdate" method="post" enctype="multipart/form-data" class="profile__form">
		<input type="text" name="fileNum" value="${file.fileNum}">
		<input type="file" name="avatar" class="input-file">
  </form>
  </c:if>
	<div class="profile__info">
		<div class="profile__name">${member.name}</div>
		<div class="profile__location">${locations[0].locationName}</div>
		<div class="profile__joinDate">${member.joinDate}</div>
	</div>
	</div>
	<div class="profile__rating">
		<div class="avg-rating__notice">
			${member.name}님이 받은 거래 평가입니다.
		</div>
		<div class="avg-rating__content"><div class="avg-rating__title">거래 매너</div> <div class="avg-rate">${rating.avgManner}</div> (${rating.countManner})</div>
		<div class="avg-rating__content"><div class="avg-rating__title">응답 속도</div> <div class="avg-rate">${rating.avgSpeed}</div> (${rating.countSpeed})</div>
		<div class="avg-rating__content"><div class="avg-rating__title">상품 상태</div> <div class="avg-rate">${rating.avgState}</div> (${rating.countState})</div>
	</div>
</div>
<div class="board-container">	<!-- 엔드 태그는 본문에 있음 -->
	<div class="board__nav">
		<c:if test="${principal.username != member.username}">
			<a class="nav__item products" href="./products">판매 상품</a>
			<a class="nav__item reviews" href="/store/${member.code}/reviews">받은 후기</a>
			<a class="nav__item socials">동네 생활</a>
		</c:if>
		<c:if test="${principal.username == member.username}">
			<a class="nav__item my-store products" href="./products">판매 상품</a>
			<a class="nav__item my-store reviews" href="/store/${member.code}/reviews">받은 후기</a>
			<a class="nav__item my-store socials" href="/store/${member.code}/socials">동네 생활</a>
			<a class="nav__item my-store hearts" href="/store/${principal.code}/hearts">찜한 상품</a>
			<a class="nav__item my-store myReviews" href="/store/${principal.code}/myReviews">작성한 후기</a>
		</c:if>
	</div>
