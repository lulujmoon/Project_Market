<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>가격 제안하기</title>
</head>
<body>

	<form action="../notification/nego" method="POST">
		<input type="hidden" name="username" value="${principal.username}">
		<input type="hidden" name="productNum" value="${noti.productNum}">
		<input type="hidden" name="notiRecvUser" value="${noti.notiRecvUser}">
		
		<div>제안할 가격을 입력하세요</div>
		<input type="number" name="notiContent">
		<input type="submit"> 
	</form>
</body>
</html>