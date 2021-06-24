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
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
<h1>noticeInsert</h1>

	<form action="./insert" method="POST">	
	    <div class="form-group">
	    <label>작성자</label>
	    <input type="text" name="username" value="${principal.username}" readonly="readonly">
	  </div>
	  <div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control myCheck" id="noticeTitle" name="noticeTitle">
			</div>

			<div class="form-group">
				<label for="contents">내용</label>
				<textarea class="form-control myCheck" rows="5" id="summernote"
					name="noticeContent"></textarea>
			</div>
	  

	  
	<button type="submit">write</button>
</form>
	</div>
<script>
$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
});
</script>
<c:import url="../template/footer.jsp"></c:import>

</body>
</html>