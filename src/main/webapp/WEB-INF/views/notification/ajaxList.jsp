<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<c:forEach items="${list}" var="noti">
	<div class="noti__card <c:if test="${noti.notiReadChk == 1}">read</c:if>">
		<div class="hidden notiNum">${noti.notiNum}</div>
		<div class="hidden counterpart">${noti.notiSendUser}</div>
		<div class="hidden user">${principal.username}</div>
		<div class="hidden product">${noti.product.productName}</div>
		<div class="hidden productNum">${noti.product.productNum}</div>
		<input type="hidden" id="notiReadChk" value="${noti.notiReadChk}"/>
		<div class="noti__top-wrapper">
			<div class="noti__product-name"><a href="/product/select/${noti.product.productNum}">${noti.product.productName}</a></div>
			<div class="noti__del"><a href="#" onclick="notiDelete(${noti.notiNum})">삭제</a></div>
		</div>
		<c:if test="${noti.notiFrom == false}">
			<div>${noti.notiSendUser}님이 가격을 제안하셨어요! : ${noti.notiContent}원</div>			
		</c:if>
		<c:if test="${noti.notiFrom == true}">
			<div class="notiContent">${noti.notiContent}</div>
		</c:if>
		<div class="noti__date">${noti.notiSendTime}</div>
	</div>
</c:forEach>
</body>
</html>