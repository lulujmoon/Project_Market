<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>NoticeList</h1>

<a href="./insert">글작성</a>

<c:forEach items="${list}" var="dto">
<div class="num"> ${dto.noticeNum}</div> <br>
<div class="title"><a href="./select?noticeNum=${dto.noticeNum}">${dto.noticeTitle}</a></div><br>
<div class="content">${dto.noticeContent}</div>
</c:forEach>
<br>


</body>
</html>