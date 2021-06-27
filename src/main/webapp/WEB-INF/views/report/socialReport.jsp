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

<%-- <input readonly="readonly" type="text" name="address" value="${principal.email}"><br> --%>
<div class="container">
<div class="title-container">
	신고하기
</div>
<form action="/report/socialReport" method="post" class="send-form">
  <input type="hidden" name="socialNum" value="${social.socialNum}">
	<div class="form-content">${principal.name}</div>
  <input readonly="readonly" type="text" class="form-content" name="title" value="글 신고 : ${social.socialTitle}">
  <textarea name="message" class="form-content message" placeholder="내용을 입력해주세요."></textarea>
  <input type="button" class="btn-report" value="신고하기">
</form>
</div>
<script type="text/javascript" src="../resources/js/report.js"></script>
</body>
</html>