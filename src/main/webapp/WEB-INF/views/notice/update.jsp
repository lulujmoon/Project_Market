<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<title>Insert title here</title>
<script src="../resources/js/summernote/summernote-lite.js"></script>
<script src="../resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/css/summernote/summernote-lite.css">
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
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
				<label for="title">제목</label> <input type="text"
					class="form-control myCheck" id="noticeTitle" name="noticeTitle" value="${dto.noticeTitle}">
			</div>

			<div class="form-group"> 
				<label for="contents">내용</label>
				<textarea class="form-control myCheck" rows="5" id="summernote"
					name="noticeContent"></textarea>
			</div>

	  
	<button type="submit">Update</button>
</form>
</div>
<script>
$(document).ready(function() {
	$('#summernote').val("${dto.noticeContent}") //db값 불러온후 초기화
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정     		
	});
});
</script>
<c:import url="../template/footer.jsp"></c:import>
</body>
</html>