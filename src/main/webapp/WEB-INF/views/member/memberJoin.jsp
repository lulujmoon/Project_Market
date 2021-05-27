<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/sign.css">
<title>Join</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="join">
	<div class="container">
		<div class="logo">
			<i class="fas fa-lemon"></i><span>&nbsp;Join</span>
		</div>
		<form action="./memberJoin" method="post">
			<div class="form-group">
				<label for="username">Username</label> 
				<input type="text" class="form-control" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" class="form-control" id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="name">Name</label> 
				<input type="text" class="form-control" id="name" name="name" required>
			</div>
			<div class="form-group">
				<label for="phone">Phone</label> 
				<input type="text" class="form-control" id="phone" name="phone" required>
			</div>
			<div class="form-group">
				<label for="email">Email</label> 
				<input type="text" class="form-control" id="email" name="email" required>
			</div>
			<div class="join-btn">
				<button type="submit" class="btn-wide btn-submit">Join</button>
			</div>
		</form>
	</div>
</div>
	
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
</body>
</html>