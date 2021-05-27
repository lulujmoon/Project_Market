<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Hello, world!</title>
</head>
<body>
	
	<c:if test="${empty SPRING_SECURITY_CONTEXT}">
	<a href="./member/memberJoin">회원가입</a>
	<a href="./member/memberLogin">로그인</a>
	</c:if>
	<c:if test="${not empty SPRING_SECURITY_CONTEXT}">
	<a href="./member/memberLogout">로그아웃</a>
	</c:if>

</body>
</html>
