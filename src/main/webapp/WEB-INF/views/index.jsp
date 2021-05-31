<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<title>Hello, world!</title>
</head>
<body>	
	
	<c:if test="${empty SPRING_SECURITY_CONTEXT}">
	<a href="./member/memberJoin">회원가입</a>
	
	
	
	
	
	<a href="./member/memberLogin">로그인</a>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=bdf85067bd67f89b950ae22189274a9c&redirect_uri=http://localhost/member/auth/kakao/callback&response_type=code"><img src="/img/kakao_login_button.png"></a>
	</c:if>
	<c:if test="${not empty SPRING_SECURITY_CONTEXT}">
	<a href="./member/memberLogout">로그아웃</a>
	</c:if>
	
<%-- 	<sec:authorize access="isAuthenticated()">
	로그인상태
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	관리자
	</sec:authorize>
	
	<sec:authentication property="principal.username"/>님 환영합니다 --%>
	


</body>
</html>
