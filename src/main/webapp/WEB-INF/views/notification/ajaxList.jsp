<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<title>알림</title>
</head>
<body>
<div>안읽은 알림 : ${unread} </div>
<div class="notice-container">
	<c:forEach items="${list}" var="noti">
		<div class="noti__card">
			<input type="hidden" id="notiReadChk" value="${noti.notiReadChk}"/>
			<a href="/product/select/${noti.product.productNum}">[ ${noti.product.productName} ]</a>
			<a onclick="read(${noti.notiNum})">${noti.notiSendUser}님이 가격제안을 하셨어요!</a>			
			<div class="noti__date">
				${noti.notiSendTime}
			</div>
			<a href="/chat/chatSendInList?room=0&otherUser=${noti.notiSendUser}&productNum=${noti.product.productNum}&content=※${principal.username}님이 ${noti.product.productName} 가격제안을 수락하셨습니다." onclick="if(confirm('수락하시겠습니까?')==false){return false;}" class="btn btn-edit" >수락</a>
			<a href="#" onclick="notiDelete(${noti.notiNum})">삭제</a></div>
			<div class="noti__content">
				${noti.notiContent}
			</div>
			<div class="btn btn-del"></div>
			</c:forEach>
		</div>
</div>
<script type="text/javascript" src="../resources/js/notificationList.js"></script>
</body>
</html>