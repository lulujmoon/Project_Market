<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<script src="../resources/js/summernote/summernote-lite.js"></script>
<script src="../resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/css/summernote/summernote-lite.css">
<link rel="stylesheet" href="../resources/css/noticeNote.css"/>
<title>공지 수정</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
	<div class="title-container">
		공지 수정
	</div>
	<form action="./update" method="POST" class="upload-form">	
	  <input type="hidden" name="noticeNum" value="${notice.noticeNum}">
		<input type="text" class="title" name="noticeTitle" value="${notice.noticeTitle}">
		<textarea class="content" name="noticeContent">${notice.noticeContent}</textarea>
		<div class="btn-wrapper">
			<button type="submit" class="btn-submit">등록</button>
		</div>
	</form>
</div>
<script>
</script>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/noticeNote.js"></script>
</body>
</html>