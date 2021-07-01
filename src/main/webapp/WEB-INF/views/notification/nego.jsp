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
<link rel="stylesheet" href="/resources/css/nego.css" />
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>가격 제안하기</title>
</head>
<body>
<form action="../notification/nego" method="POST" name="nego">
	<div class="nego-container">
		<input type="hidden" name="username" value="${principal.username}">
		<input type="hidden" name="productNum" value="${noti.productNum}">
		<input type="hidden" name="notiRecvUser" value="${noti.notiRecvUser}">
		<div class="nego-info">제안할 가격을 입력하세요.</div>
		<div class="price-wrapper">
			<input type="number" class="nego-price" id="notiContent" name="notiContent">
			<div class="btn-presubmit" onclick="check()">제안</div>
			<button class="hidden btn-submit"></button>
		</div>
	</div>
</form>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/functions.js"></script>
<script type="text/javascript">

function check() {
	if(confirm('가격을 제안하시겠습니까?')){		
	  if(nego.notiContent.value == "") {
	    alert("값을 입력해 주세요.");
	  }else {
		  $.post(
				'/notification/nego',
				{username: '${principal.username}',
				 productNum: '${noti.productNum}',
				 notiRecvUser: '${noti.notiRecvUser}',
				 notiContent: $(".nego-price").val()
				},
				function(result){
					alert("가격을 제안했습니다. 답장을 기다려주세요.");
					window.open("about:blank", "_self").close();
				});
		 }		  
	}
}

</script>
</body>
</html>