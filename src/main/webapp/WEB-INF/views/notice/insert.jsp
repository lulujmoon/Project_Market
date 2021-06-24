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
<c:import url="../template/setting.jsp"></c:import>
<script src="../resources/js/summernote/summernote-lite.js"></script>
<script src="../resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/css/summernote/summernote-lite.css">
<link rel="stylesheet" href="../resources/css/noticeNote.css"/>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
	<div class="title-container">
		공지 등록
	</div>
	<form action="./insert" method="POST" class="upload-form">	
	  <input type="hidden" name="username" value="${principal.username}">
	  <input type="text" class="title" name="noticeTitle" placeholder="제목">
		<textarea class="content" id="summernote" name="noticeContent"></textarea>
		<div class="btn-wrapper">
			<button type="submit" class="btn-submit">등록</button>
		</div>
	</form>
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/noticeNote.js"></script>
</body>
</html>