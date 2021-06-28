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
			<div class="send-time">
				${tmp.sendTime}
			</div>
			<div class="row">
				<div class="content">${tmp.content}</div>
				<%-- 만약 현재사용자가 안읽은 메세지 갯수가 0보다 클때만 badge를 표시한다. --%>
				<c:if test="${tmp.unread > 0}">
					<div class="unread${tmp.room}">
						<span class="badge bg-danger">${tmp.unread}</span>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</c:forEach>