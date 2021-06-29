<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="tmp" items="${list}">
	<div class="chat_list" room="${tmp.room}" otherUser="${tmp.otherUser}">
		<img class="chat_img" src="/resources/upload/member/${tmp.profile}" alt="상대방 프로필">
		<div class="info-container">
			<div class="other-user">
				${tmp.otherUser}
			</div>
			<c:if test="${tmp.unread > 0}">
				<div class="badge-unread">${tmp.unread}</div>
			</c:if>
			<div class="row">
				<div class="content">${tmp.content}</div>
				<div class="send-time">
					${tmp.sendTime}
				</div>
			</div>
		</div>
	</div>
</c:forEach>