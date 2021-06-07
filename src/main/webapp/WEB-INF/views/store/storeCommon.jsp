<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="profile-container">
	<div class="profile__photo">
		<img class="profile__img" src = "/resources/upload/member/${file.fileName}">
		<div class="profile-hover">프로필 수정하기</div>
	</div>
	<form action="./profileUpdate" method="post" enctype="multipart/form-data" class="profile__form">
		<input type="text" name="fileNum" value="${file.fileNum}">
		<input type="file" name="avatar" class="input-file">
  </form>
	<div class="profile__info">
		<div class="profile__name">문정현</div>
		<div class="profile__location">세종특별자치시 아름동</div>
		<div class="profile__joinDate">2021년 6월 4일 가입</div>
	</div>
	<div class="profile__rating">
		<div class="rating__state">상품 상태 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
		<div class="rating__manner">거래 매너 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
		<div class="rating__response">응답 속도 <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"><i class="fas fa-star-half-alt"></i></i><i class="far fa-star"></i></div>
	</div>
</div>
