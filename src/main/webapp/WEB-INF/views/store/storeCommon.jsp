<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="profile-container">
<div class="divide">
	<div class="profile__photo">
		<img class="profile__img" src = "/resources/upload/member/${file.fileName}">
		<div class="profile-hover">프로필 수정하기</div>
	</div>
	<form action="./profileUpdate" method="post" enctype="multipart/form-data" class="profile__form">
		<input type="text" name="fileNum" value="${file.fileNum}">
		<input type="file" name="avatar" class="input-file">
  </form>
	<div class="profile__info">
		<div class="profile__name">${member.name}</div>
		<div class="profile__location">${locations[0].locationName}</div>
		<div class="profile__joinDate">${member.joinDate}</div>
	</div>
	</div>
	<div class="profile__rating">
		<div class="rating__notice">
			${member.name}님이 받은 거래 평가입니다.
		</div>
		<div class="rating__content"><div class="rating__title">상품 상태</div> <div class="rate">6</div> (15)</div>
		<div class="rating__content"><div class="rating__title">거래 매너</div> <div class="rate">7</div> (23)</div>
		<div class="rating__content"><div class="rating__title">응답 속도</div> <div class="rate">8</div> (23)</div>
	</div>
</div>
<div class="board-container">	<!-- 엔드 태그는 본문에 있음 -->
	<div class="board__nav">
		<a class="nav__item nav__selected" href="./products">판매 상품</a>
		<a class="nav__item" href="./reviews">받은 후기</a>
		<a class="nav__item">동네 생활</a>
		<a class="nav__item">찜한 상품</a>
		<a class="nav__item">작성한 후기</a>
	</div>
