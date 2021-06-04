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
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>작성자</th>
					<th>내용</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${vo.socialNum}</td>
					<td>${vo.categoryCode}</td>
					<td>${vo.socialTitle}</td>
					<td>${vo.username}</td>
					<td>${vo.socialContent}</td>
					<td>${vo.socialDate}</td>
				</tr>
			</tbody>
		</table>

		<a href="./delete?socialNum=${vo.socialNum}" class="btn btn-primary"
			role="button">삭제</a> <a href="./update?socialNum=${vo.socialNum}"
			class="btn btn-primary" role="button">수정</a>
	</div>

	<!--  댓글  -->
	<div class="container">
		<label for="content">댓글</label>
		<form name="commentInsert">
			<div class="input-group">
				<input type="hidden" name="socialNum" value="${social.socialNum}" />
				<input type="text" class="form-control" id="content" name="content"
					placeholder=""> <span class="input-group-btn">
					<button class="btn btn-primary" type="button"
						name="commentInsertBtn">등록</button>
				</span>
			</div>
		</form>
	</div>

	<div class="container">
		<div class="commentList"></div>
	</div>

	<script type="text/javascript" src="../comment/commentInsert.js"></script>
</html>