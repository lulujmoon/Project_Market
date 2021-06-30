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
		<div class="form-group">
			<div class="form-title">상품 이미지</div>
			<div class="preview-container">
				<div class="add">
					<i class="fas fa-camera"></i>
					이미지 추가
				</div>
				<div class="inputs"></div>
			</div>
		</div>
		<div class="preview-info">최대 7장까지 추가할 수 있습니다.</div>
		<div class="form-group">
			<div class="form-title">상품명</div>
			<input type="text" name="productName" class="form-content" required placeholder="상품명을 입력해주세요.">				
		</div>
		<div class="form-group">
			<div class="form-title">지역</div>
			<div class="form-content form-content-select">
				<select class="form-select" id="locationCode" name="locationCode">
					<c:forEach items="${location}" var="vo">
				   <option value="${vo.locationCode}">${vo.locationName}</option>
				  </c:forEach>
				</select>
				<i class="fas fa-sort-down"></i>
			</div>
			<div class="form-info">
				내 지역으로 저장한 지역을 선택할 수 있습니다.
				<a href="${pageContext.request.contextPath}/member/info">내 지역 설정&nbsp;&nbsp;<i class="fas fa-chevron-right"></i></a>
			</div>
		</div>		
		<div class="form-group">		
			<div class="form-title">카테고리</div>
			<div class="form-content form-content-select">
				<select class="form-select" id="category" name="categoryCode">
				  <c:forEach items="${categories}" var="category">
				  	<option value="${category.categoryCode}">${category.categoryName}</option>
				  </c:forEach>
				</select>
				<i class="fas fa-sort-down"></i>
			</div>
			<div class="form-info">카테고리를 선택해주세요.</div>
		</div>
		<div class="form-group">
			<div class="form-title">상품 설명</div>
			<textarea class="form-content" id="productContent" rows="5" name="productContent" required placeholder="상품 설명을 입력해주세요."></textarea>
		</div>
		<div class="form-group">
			<div class="form-title">가격</div>
			<input type="text" name="productPrice" class="product-price form-content" required placeholder="무료나눔은 0을 입력하세요.">
			<div class="form-title">가격 제안</div>
			<input type="radio" name="productNego" value="1" id="nego-enabled">&nbsp;<label for="nego-enabled">가능</label>&nbsp;&nbsp;&nbsp;
			<input type="radio" name="productNego" value="0" id="nego-disabled" checked>&nbsp;<label for="nego-disabled">불가</label>
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