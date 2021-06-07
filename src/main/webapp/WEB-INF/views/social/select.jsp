<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>Select Page</h2>
		<h3>번호 : ${vo.socialNum}</h3>
		<h3>카테고리 : ${vo.categoryCode}</h3>
		<h3>제목 : ${vo.socialTitle}</h3>
		<h3>작성자 : ${vo.username}</h3>
		<h3>
			내용 : ${vo.socialContent}
			<c:forEach items="${vo.files}" var="file">
				<img src="/resources/upload/social/${file.fileName}">
			</c:forEach>
		</h3>
		<h3>작성 날짜 : ${vo.socialDate}</h3>

		<a href="./delete?socialNum=${vo.socialNum}" class="btn btn-primary"
			role="button">삭제</a> <a href="./update?socialNum=${vo.socialNum}"
			class="btn btn-primary" role="button">수정</a>
	</div>

	<!--  댓글  -->
	<div class="container">
		<label for="comment">댓글</label>
		<form id="form"
			action="../comment/commentInsert?socialNum=${vo.socialNum}"
			method="post">
			<input type="hidden" id="socialNum" name="socialNum">
			<textarea class="form-control myCheck" id="commentContent"
				name="commentContent" placeholder="내용을 입력하세요."></textarea>
			<input type="text" readonly="readonly" value="${vo.username}" name="username">

			<input type="submit" value="작성" />
		</form>

			<c:forEach items="${list}" var="comment">
				<ul>
					<li>
						<div class="container">
							<div class="container">
								${vo.username}<br>${vo.commentDate}
							</div>
							<a class="insert-button"
								href="./update?commentNum=${vo.commentNum}">수정</a> <a
								class="insert-button"
								href="./delete?commentNum=${vo.commentNum}">삭제</a>
						</div>
					</li>

					<li>${comment.commentContent}</li>
				</ul>
			</c:forEach>
	</div>
</body>
</html>