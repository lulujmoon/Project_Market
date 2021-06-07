<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/sign.css">
<title>Login</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
	<div class="login">
		<div class="container">
			<div class="logo">
				<i class="fas fa-lemon"></i><span>&nbsp;Login</span>
			</div>
			<form action="./login" method="post">
				<div class="form-group">
					<label for="username">Username</label><br>
					<input type="text"	class="form-control" id="username" name="username"><br>
				</div>
				
				<div class="form-group">
					<label for="password">Password</label><br> 
					<input type="password" class="form-control" id="password" name="password">
				</div>
				<div class="login-btn">
					<button type="submit" class="btn-wide btn-submit">Login</button>
					<a href="./join">회원가입</a>
					<a href="#">아이디/비밀번호 찾기</a>
				</div>
			</form>
			<div class="login-btn">
				<button type="button" class="btn-wide btn-naver">Login with Naver</button>
			</div>
			<div class="login-btn">
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=bdf85067bd67f89b950ae22189274a9c&redirect_uri=http://localhost/member/auth/kakao/callback&response_type=code"><button type="button" class="btn-wide btn-kakao">Login with Kakao</button></a>
			</div>
		</div>
	</div>
	
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
</body>
</html>