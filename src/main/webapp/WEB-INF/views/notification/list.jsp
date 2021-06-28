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
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/noticeList.css"/>
<title>알림</title>
<style type="text/css">
	.unread {
		background-color: gray;
	}

</style>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
			<div>안읽은 알림 : ${unread} </div>
	<div class="notice-container">
		<c:forEach items="${list}" var="noti">
			<div class="ntc__card">
				<div class="ntc__left">
						<input type="hidden" id="notiReadChk" value="${noti.notiReadChk}"> 

					<div class="ntc__title">
						<a href="/product/select/${noti.product.productNum}">[ ${noti.product.productName} ]</a>
						<a onclick="read(${noti.notiNum})">${noti.notiSendUser}님이 가격제안을 하셨어요!</a>
					</div>
					<div class="ntc__date">
						${noti.notiSendTime}
					</div>
				<a href="/chat/chatSendInList?room=0&otherUser=${noti.notiSendUser}&content=※${principal.username}님이 ${noti.product.productName} 가격제안을 수락하셨습니다." onclick="if(confirm('수락하시겠습니까?')==false){return false;}" class="btn btn-edit" >수락</a>
				<div class="btn btn-del">삭제</div>
				<div class="ntc__content">
					${noti.notiContent}
				</div>
				</div>
			</div>
			
		</c:forEach>
	</div>
	
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/functions.js"></script>
<script type="text/javascript" src="../resources/js/notificationList.js"></script>
</body>
</html>