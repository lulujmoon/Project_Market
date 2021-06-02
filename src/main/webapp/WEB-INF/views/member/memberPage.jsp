<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>마이페이지</h1>
<a href="./memberUpdate">회원정보수정</a>


<%-- <p>principal.username : <sec:authentication property="principal.username"/></p>
<p>principal.password : <sec:authentication property="principal.password"/></p>
<p>principal.email : <sec:authentication property="principal.email"/></p> --%>
</body>
</html>