<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Comment Update</h1>

	<form id="form" action="./commentUpdate" method="post">
		<div>
			<input type="hidden" id="commentNum" name="commentNum"
				value="${param.commentNum}" /> <input type="text"
				readonly="readonly" id="username" name="username"
				value="${principal.username}" />

			<textarea id="commentContent" name="commentContent">${comment.commentContent}
		        </textarea>


			<input type="submit" value="수정" />

		</div>

	</form>

</body>
</html>