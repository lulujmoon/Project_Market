<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>



<c:forEach var="tmp" items="${clist}">
	
	<c:choose>
		<c:when test="${principal.username ne tmp.sendUser}">
		<!-- 받은 메세지 -->
		<div class="incoming_msg">
				<input type="hidden" value="${tmp.otherUser}">
				<input type="hidden" value="${tmp.profile}">
				<input type="hidden" value="${tmp.recvUser}">
			<div class="received-msg">
				<img class="rcv__avatar" src="/resources/upload/member/${tmp.profile}" alt="보낸사람 프로필">
				<div class="rcv__content-wrapper">
					<div class="rcv__sendUser">${tmp.sendUser}</div> 
					<div class="rcv__content">${tmp.content}</div>
				</div>
				<div class="rcv__time">${tmp.sendTime}</div>
			</div>
		</div>
		</c:when>
		
		<c:otherwise>
		<!-- 보낸 메세지 -->
		<div class="outgoing_msg">
			<div class="sent-msg">
					<div class="sent__time">${tmp.sendTime}</div> 
					<div class="sent__content">${tmp.content}</div>
			</div>
		</div>

<c:if test="${principal.username eq tmp.sendUser}">
<a href="../reservation/insert?buyer=${clist[0].sendUser}&&productNum=${param.productNum}&&locationCode=${param.locationCode}">이사람한테판매</a>
</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>



	