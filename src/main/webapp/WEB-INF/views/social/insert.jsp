<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/common.css" />
<link rel="stylesheet" href="/resources/css/socialNote.css"/>
<link rel="stylesheet" href="../resources/css/summernote/summernote-lite.css">
<script src="../resources/js/summernote/summernote-lite.js"></script>
<script src="../resources/js/summernote/lang/summernote-ko-KR.js"></script>
<title>글쓰기</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="title-container">
		글쓰기
	</div>
	<form class="upload-form" action="./insert" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="socialNum"> 
		<input type="hidden" name="username" value="${principal.username}">
		<div class="location-container">
			<select class="select-location" name="locationCode">			
				<c:forEach items="${locations}" var="location">
						<option value="${location.locationCode}">${location.locationName}</option>
				</c:forEach>
			</select>
			<i class="fas fa-sort-down"></i>		
			<div class="location-set-wrapper" id="location-set-wrapper">
				내 지역으로 저장한 지역을 선택할 수 있습니다.
				<div class="location-set" onclick="openMemberInfo()">내 지역 설정&nbsp;&nbsp;<i class="fas fa-external-link-alt"></i></div>
			</div>
		</div>
		<div class="category-container">
			<select class="select-category" name="categoryCode">
				<c:forEach items="${categories}" var="category">
					<option value="${category.categoryCode}">${category.categoryName}</option>
				</c:forEach>
			</select>
			<i class="fas fa-sort-down"></i>
		</div>
		<input type="text" class="title" name="socialTitle" placeholder="제목"> 
		<textarea class="content"	id="socialContent" name="socialContent"></textarea>
		<div class="btn-wrapper">
			<button type="submit" class="btn-submit">등록</button>
		</div>
	</form>
</div>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/summerFile.js"></script>
<script type="text/javascript" src="../resources/js/socialNote.js"></script>
</body>
</html>