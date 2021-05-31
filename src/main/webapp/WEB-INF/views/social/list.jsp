<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
		<h2>동네 생활</h2>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>카테고리</th>
					<th>작성자</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.socialNum}</td>
						<td><a href="./select?num=${dto.socialNum}"> <c:catch>
									<c:forEach begin="1" end="${dto.depth}">--</c:forEach>
								</c:catch>${dto.socialTitle}</a></td>
						<td>${dto.socialCategory}</td>
						<td>${dto.socialWriter}</td>
						<td>${dto.socialDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container">
		<ul class="pagination">
			<c:if test="${pager.pre}">
				<li class="page-item"><a class="page-link p" href="#"
					title="${pager.startNum-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item "><a class="page-link p"
					href="./list?curPage=${i}&kind=${pager.kind}&search=${pager.search}"
					title="${i}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-link p" href="#"
					title="${pager.lastNum+1}">다음</a></li>
			</c:if>
		</ul>
		<div class="input-group mt-3 mb-3">
			<form id="frm" action="./list" class="form-inline">
				<input type="hidden" name="curPage" value="1" id="curPage">
				<div class="input-group-prepend">
					<select class="form-control" name="kind" id="kind">
						<option>제목</option>
						<option>내용</option>
						<option>작성자</option>
					</select>
				</div>
				<input type="text" class="form-control" name="search" id="search"
					value="${pager.search}" placeholder="">
				<div class="input-group-append">
					<button class="btn btn-success" type="submit">검색</button>
				</div>
			</form>
		</div>
		<a href="./insert" class="btn btn-primary" role="button">작성</a>
	</div>
</body>
</html>