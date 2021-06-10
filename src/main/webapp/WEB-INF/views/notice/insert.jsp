<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>noticeInsert</h1>

	<form action="./insert" method="POST">	
	    <div class="form-group">
	    <label>작성자</label>
	    <input type="text" name="username" value="${principal.username}" readonly="readonly">
	  </div>
	  <div class="form-group">
	    <label>제목</label>
	    <input type="text" name="noticeTitle">
	  </div>
	    <div class="form-group">
	    <label>내용</label>
	    <input type="text" name="noticeContent">
	  </div>

	  
	<button type="submit">write</button>
</form>

</body>
</html>