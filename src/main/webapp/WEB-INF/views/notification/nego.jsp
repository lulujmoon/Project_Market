<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<c:import url="../template/setting.jsp"></c:import>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>가격 제안하기</title>
</head>
<body>
<h2>가격 제안하기 페이지</h2>
<div>
	<form action="../notification/nego" method="POST" name="nego" onsubmit="return check()">
		<input type="hidden" name="username" value="${principal.username}">
		<input type="hidden" name="productNum" value="${noti.productNum}">
		<input type="hidden" name="notiRecvUser" value="${noti.notiRecvUser}">
		
		<div>제안할 가격을 입력하세요</div>
		<input type="number" id="notiContent" name="notiContent">
		<div class="btn-wrapper">
			<input type="submit" id="btn" class="btn-presubmit" value="등록">
		</div>
	</form>
</div>
<script type="text/javascript">

function check() {
	if(confirm('가격을 제안하시겠습니까?')){		
	  if(nego.notiContent.value == "") {
	    alert("값을 입력해 주세요.");
	    return false;
	  }else {
		alert("제안 성공");
		return true;
	  }		  
	}else {
		return false;
	}
}

</script>
</body>
</html>