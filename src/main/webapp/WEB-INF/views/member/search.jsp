<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/sign.css">
<title>Insert title here</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="alert hidden">${alert}</div>
	<form action="./search" method="post" onclick="submitForm()">
		<div class="logo">
			<i class="fas fa-lemon"></i><span>&nbsp;Find</span>
		</div>
		<div class="info">가입한 이메일로 임시 비밀번호를 발송합니다.</div>
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" class="form-control" name="username" id="username"/>
		</div>
		<div class="form-group">
			<label for="email">이메일</label>	
			<input type="text" class="form-control" name="email" id="email"/>
		</div>
		<div class="btn-wrapper">
			<button class="btn-wide btn-submit">비밀번호 찾기</button>
		</div>
	</form>
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/memberSearch.js"></script>
</body>
</html>