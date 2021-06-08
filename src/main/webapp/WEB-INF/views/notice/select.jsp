<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<a href="#">이전글</a>
<a href="#">다음글</a>
</div>
<br>
<a href="./update?noticeNum=${dto.noticeNum}"> 수정 </a>
<a href="./delete?noticeNum=${dto.noticeNum}"> 삭제 </a>

</body>
</html>