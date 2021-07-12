<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/sign.css">
<title>Join</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="join">
<div class="hidden checked">${checked}</div>
	<div class="container">
		<div class="logo">
			<i class="fas fa-lemon"></i><span>&nbsp;Join</span>
		</div>
		<form:form modelAttribute="memberVO"  id="form-submit" action="./join" method="post" enctype="multipart/form-data">
				<div class="form-group username">
					<span><label for="username" class="form-title">Username</label></span>
					<form:input class="form-control myCheck" type="text" id="username" name="username" path="username" required="required"/>	
					<input type="button" class="btn-check" value="중복확인">
					<small class="form-notice"><form:errors path="username"></form:errors></small>
			</div>
			<div class="form-group">
				<label for="password" class="form-title">Password</label> 
				<form:input type="password"	class="form-control myCheck" id="password" name="password" path="password" required="required"/>
				<small class="form-notice"><form:errors path="password"></form:errors></small>
			</div>
			<div class="form-group" class="form-title">			
				<label for="password1">Check Password</label> 
				<form:input type="password" class="form-control myCheck" id="password1" name="password1" path="password1" required="required"/>
				<small class="form-notice"><form:errors path="password1"></form:errors></small>
			</div>
			<div class="form-group">
				<label for="name" class="form-title">Name</label> 
				<form:input type="text" class="form-control myCheck" id="name" name="name" path="name" required="required"/>
				<small class="form-notice"><form:errors path="name"></form:errors></small>
			</div>
			<div class="form-group">
				<label for="phone" class="form-title">Phone</label> 
				<form:input type="text" class="form-control myCheck" id="phone" name="phone" path="phone" required="required"/>
				<small class="form-notice"><form:errors path="phone"></form:errors></small>
			</div>
			<div class="form-group">
				<label for="email" class="form-title">Email</label> 
				<form:input type="text" class="form-control myCheck" id="email" name="email" path="email" required="required"/>
				<small class="form-notice"><form:errors path="email"></form:errors></small>
			</div>
			<div class="form-group">
				<label for="avatar" class="form-title">Avatar</label> 
				<input type="file" class="form-control etc"	id="avatar" name="avatar" required="required">
			</div>
			<div class="btn-wrapper">
				<button class="hidden btn-submit"></button>
				<input type="button" class="btn-wide btn-preSubmit" value="Join">
			</div>
		</form:form>
	</div>
</div>
	
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/memberJoin.js"></script>
</body>
</html>