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
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>${product.productName}</title>
</head>
<body>

    <h2>메일 발송</h2>
<%--         <input readonly="readonly" type="text" name="address" value="${principal.email}"><br> --%>

    <form action="/report/report" method="post">
    	<input readonly="readonly" type="text" name=username value="${principal.username}">
        <input readonly="readonly" type="text" name="productNum" value="${product.productNum}"><br>
        <input readonly="readonly" type="text" name="title" value="${product.productNum}번 상품을 신고합니다."><br>
        <textarea name="message" placeholder="메일 내용을 입력해주세요." cols="60" rows="20"></textarea>
        <input type="submit" value="Report">
    </form>
</body>
</html>