<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="profile-container">
		<img class="profile__photo" src = "/resources/images/test01.jpg">
		<h1>프러필업뎃</h1>

		<h1>${file.username}</h1>
		
		<form action="./profileUpdate" method="post" enctype="multipart/form-data">
				<div class="form-group">
						<label for="fileNum">fileNum</label> 
						<input type="text" class="form-control etc"	id="fileNum" name="fileNum" readonly="readonly" value="${file.fileNum}">
					</div>
			
			<div class="form-group">
						<label for="avatar">Avatar</label> 
						<input type="file" class="form-control etc"	id="avatar" name="avatar">
					</div>
					<div class="join-btn">
						<button type="submit" class="btn-wide btn-submit">ok</button>
					</div>
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
</div>
