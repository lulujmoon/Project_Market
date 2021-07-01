<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/chat.css">
<title>채팅</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container" style="background-color: rgba( 255, 255, 255, 0 );">
<div class="hidden product-num">${productNum}</div>
<div class="hidden location-code">${locationCode}</div>
<br>

	<div class="inbox_msg" style="width: 300px;">
		<div class="inbox_people">
				<div class="recent_heading" style="background-color: white;">
					상품을 판매할 유저를 선택하세요.
				</div>
			<div class="inbox_chat" style="background-color: white;"></div>
		</div>
		
	</div>   
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/chatList2.js"></script>
</body>
</html>