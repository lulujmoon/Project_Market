<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 마이샵 </h1>

<!--카카오 멤버일경우 기본이미지출력 -->
<c:if test="${principal.oauth eq true}">

</c:if>
<!-- 일반멤버일경우 input된 이미지출력 -->
<c:if test="${principal.oauth eq false}">
<img src="../resources/upload/upload/member/${file.fileName}">
<a href="./profileUpdate">프로필수정</a>
</c:if>

</body>
</html>