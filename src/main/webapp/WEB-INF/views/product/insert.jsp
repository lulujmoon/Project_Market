<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productNote.css"> 
<title>판매상품 등록</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<div class="title-container">
		상품 등록
	</div>
	<form action="./insert" method="POST" enctype="multipart/form-data" id="upload-form">
		<input type="hidden" name="username" value="${principal.username}">
		<div class="form-title">상품 이미지</div>
		<div class="preview-container">
			<div class="add">
				<i class="fas fa-camera"></i>
				이미지 추가
			</div>
			<div class="inputs"></div>
		</div>
		<div class="form-group">
			<div class="form-title">상품명</div>
			<input type="text" name="productName" class="form-content" required>				
		</div>
		<div class="form-group">		
			<div class="form-title">카테고리</div>
			<select class="form-content" id="category" name="categoryCode">
			  <option value="1">디지털/가전</option>
			  <option value="2">가구/인테리어</option>
			  <option value="3">유아동/유아도서</option>
			  <option value="4">생활/가공식품</option>
			  <option value="5">스포츠/레저</option>
			  <option value="6">여성의류</option>
			  <option value="7">여성잡화</option>
			  <option value="8">남성의류</option>
			  <option value="9">남성잡화</option>
			  <option value="10">게임/취미</option>
			  <option value="11">뷰티/미용</option>
			  <option value="12">반려동물용품</option>
			  <option value="13">도서/티켓/음반</option>
			  <option value="14">삽니다</option>
			</select>		
		</div>
		<div class="form-group">
			<div class="form-title">상품 설명</div>
			<textarea class="form-content" id="productContent" rows="5" name="productContent" required></textarea>
		</div>
		<div class="form-group">
			<div class="form-title">가격</div>
			<input type="text" name="productPrice" class="product-price form-content" required>
		</div>
		<div class="form-group">
			<div class="form-title">지역</div>
			<select class="form-content" id="locationCode" name="locationCode">
				<c:forEach items="${location}" var="vo">
			   <option value="${vo.locationCode}">${vo.locationName}</option>
			  </c:forEach>
			</select>
			<small>내 동네로 설정한 지역 중에 선택할 수 있습니다.</small>
		</div>
		<div class="btn-wrapper">
			<input type="button" class="btn-presubmit" value="등록">
		</div>

	<button class="btn-submit"></button>
	</form>
</div>


<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/productNote.js"></script>
</body>
</html>