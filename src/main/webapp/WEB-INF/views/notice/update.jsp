<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>notice update</h1>

	<form action="./update" method="POST">	
	    <div class="form-group"> 
	   	 <label>글번호</label>
	    <input type="text" name="noticeNum" value="${dto.noticeNum}" readonly="readonly">
	  </div>
	  <div class="form-group"> 
	    <label>작성자</label>
	    <input type="text" name="username" value="${dto.username}" readonly="readonly">
	  </div>
	  <div class="form-group">
	    <label>제목</label>
	    <input type="text" name="noticeTitle" value="${dto.noticeTitle}">
	  </div>
	    <div class="form-group">
	    <label>내용</label>
	    <input type="text" name="noticeContent" value="${dto.noticeContent}">
	  </div>

	  
	<button type="submit">Update</button>
</form>
</body>
</html>