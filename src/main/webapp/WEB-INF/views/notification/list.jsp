<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/noticeList.css"/>
<title>알림</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>

	<div class="notice-container">
		<c:forEach items="${list}" var="noti">
			<div class="ntc__card" onclick="manageContent()">
				<div class="ntc__left">
					<div class="ntc__title">
						${noti.notiSendUser}님이 가격제안을 하셨어요!
					</div>
					<div class="ntc__date">
						${noti.notiSendTime}
					</div>
				</div>
				<i class="fas fa-sort-down"></i>
			</div>
			<div class="content-wrapper">
				<div class="ntc__btn">
					<div class="btn btn-del" onclick="deleteNotice('${noti.notiNum}')">삭제</div>
				</div>
				<div class="ntc__content">
					${noti.notiContent}
				</div>
			</div>
		</c:forEach>
	</div>
	
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/functions.js"></script>
<script type="text/javascript" src="../resources/js/noticeList.js"></script>
</body>
</html>