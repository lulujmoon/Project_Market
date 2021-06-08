<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h1>select</h1>

<h2>${dto.noticeNum}</h2>
<h2>${dto.noticeTitle}</h2>
<h2>${dto.noticeContent}</h2>

<div>

이전글 :<a href="./select?noticeNum=${pre.noticeNum}">${pre.noticeTitle} </a><br>
다음글 : <a href="./select?noticeNum=${next.noticeNum}">${next.noticeTitle}</a>

</div>
<br>

<sec:authorize access="hasRole('ADMIN')">
<a href="./update?noticeNum=${dto.noticeNum}"> 수정 </a>
<a href="./delete?noticeNum=${dto.noticeNum}"> 삭제 </a>
</sec:authorize>
</body>
</html>