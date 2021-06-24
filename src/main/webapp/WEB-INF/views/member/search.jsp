<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="../resources/css/sign.css">
<title>Insert title here</title>
</head>
<body>

<form action="./search" method="post">

<h2>정보를 입력해주세요.</h2>
아이디<input type="text" class="username" name="username"/>
이메일<input type="text" class="email" name="email"/>

<button type="submit" onclick="sendMail()">입력</button>
</form>

</body>
</html>