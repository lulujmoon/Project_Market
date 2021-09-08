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
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/report.css"/>
<title>신고하기</title>
</head>
<body>
	<div class="container">
		<div class="title-container">
			신고하기
		</div>
		<form action="/report/report" method="post" class="send-form">
		  <input type="hidden" name="productNum" value="${product.productNum}">
			<div class="form-content">${principal.name}</div>
		  <input readonly="readonly" type="text" class="form-content" name="title" value="상품 신고 : ${product.productName}">
		  <textarea name="message" class="form-content message" placeholder="내용을 입력해주세요."></textarea>
		  <input type="button" class="btn-report" value="신고하기">
		</form>
	</div>
<script type="text/javascript" src="../resources/js/report.js"></script>
</body>
</html>