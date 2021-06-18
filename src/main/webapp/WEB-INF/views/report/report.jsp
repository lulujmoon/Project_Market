<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>${product.productName}</title>
</head>
<body>
<%-- <c:import url="../template/header.jsp"></c:import> --%>
    <h2>메일 발송</h2>

    <form action="/report" method="post">
    
        <input name="address" value=""><br>
        <input readonly="readonly" name="productNum" value="${product.productNum}"><br>
        <input readonly="readonly" name="title" value="${product.productNum}번 상품을 신고합니다."><br>
        <textarea name="message" placeholder="메일 내용을 입력해주세요." cols="60" rows="20"></textarea>
        <input type="button">발송
    </form>
</body>
</html>