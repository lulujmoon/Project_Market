<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Insert title here</title>
</head>
<body>
	<h2>Social Insert Page</h2>
	<form class="form" action="./insert" method="POST"
		enctype="multipart/form-data">

		<div class="form-group">
			<label>제목</label> <input type="text" name="socialTitle">
		</div>

		<div class="form-group">
			<label>작성자</label> <input type="text" name="username">
		</div>

		<div>
			<div class="form-group">
				<label for="category">카테고리</label> <select class="form-control"
					id="socialCategory" name="categoryCode">
					<option value="1">동네맛집</option>
					<option value="2">동네소식</option>
					<option value="3">취미생활</option>
					<option value="4">애완동물</option>
					<option value="5">살림/인테리어</option>
					<option value="6">출산/육아</option>
					<option value="7">기타</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label>글 내용</label>
			<textarea class="form-control" id="socialContent" rows="5"
				name="socialContent"></textarea>
		</div>

		<div>
			<input type="file" name="file">
		</div>

		<input type="hidden" name="socialNum">

		<button id="insertBtn" class="btn btn-outline-secondary">작성</button>
	</form>
</body>
</html>