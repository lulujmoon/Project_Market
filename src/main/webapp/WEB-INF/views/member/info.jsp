<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/memberInfo.css">
<title>내 정보</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<div class="info-container">
		<div class="info__header">
			<h2>내 정보</h2>
			<div class="btn-group">
				<button class="btn btn-edit" class="active">수정</button>
				<button class="btn btn-submit">완료</button>
				<button class="btn btn-cancel">취소</button>
			</div>
		</div>
		<form id="info-form" action="./update" method="post">
			<div class="info-group">
				<div class="info__title">Username</div>
				<div class="info__content">${principal.username}</div>
				<input class="info__input username" type="text" name="username" value="${principal.username}">
			</div>
			<div class="info-group">
				<div class="info__title">Password</div>
				<div class="info__content">********</div>
				<input class="info__input" type="password" name="password" placeholder="변경 시에만 입력하세요.">
			</div>
			<div class="info-group">
				<div class="info__title">Name</div>
				<div class="info__content">${principal.name}</div>
				<input class="info__input" type="text" name="name" value="${principal.name}">
			</div>
			<div class="info-group">
				<div class="info__title">Phone</div>
				<div class="info__content">${principal.phone}</div>
				<input class="info__input" type="text" name="phone" value="${principal.phone}">
			</div>
			<div class="info-group">
				<div class="info__title">Email</div>
				<div class="info__content">${principal.email}</div>
				<input class="info__input" type="text" name="email" value="${principal.email}">
			</div>
		</form>
	</div>
	<div class="location-container">
		<div class="location__header">
			<h2> 내 지역 </h2>
			최대 3개 지역까지 설정할 수 있습니다.
			<button class="btn btn-add active">추가</button>
		</div>
		<div class="location-group">
			<div class="location__title">내 지역 1</div>
			<div class="location__content">세종시 아름동</div>
		</div>
		<div class="location-group">
			<div class="location__title">내 지역 2</div>
			<div class="location__content">세종시 아름동</div>
		</div>
		<div class="location-group">
			<div class="location__title">내 지역 3</div>
			<div class="location__content">세종시 아름동</div>
		</div>
	</div>
</div>

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="../resources/js/memberInfo.js"></script>
</body>
</html>