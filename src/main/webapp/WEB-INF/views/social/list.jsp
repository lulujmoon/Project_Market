<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../template/setting.jsp"></c:import>
<title>우리동네</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style type="text/css">
.container {
	padding-top: 125px;
	width: 1252px;
	min-height: 55vh;
	margin: 30px auto;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.hidden {
	display: none;
}

.category-container {
	width: 1170px;
	margin: 30px auto;
	padding: 0 80px;
	display: flex;
	flex-flow: row wrap;
	background-color: white;
}

.category-list li {
	background-color: #fadd85;
	border-radius: 5px 5px 5px 5px;
	border: 1px solid #fadd85;
	list-style-type: none;
	float: left;
	margin-left: 40px;
	display: inline;
	font-size: 12pt;
}

.table-box {
	border: 3px solid #f8cb45;
	width: 80%;
	height: auto;
	margin-left: 7%;
	margin-bottom: 2%;
	padding: 0 0 0 0;
	color: black;
	text-align: center;
	font-size: 12pt;
}
</style>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<div class="container">
		<h2>우리동네</h2>
		<div class="category-container">
			<ul class="category-list">
				<c:forEach items="${categories}" var="category">
					<li><a href="./list?categoryCode=${category.categoryCode}"
						onclick="${category.categoryCode}">${category.categoryName}</a></li>
				</c:forEach>
			</ul>

			<input type="hidden" class="category" value="category"
				name="categoryCode">
			<table class="table-box">
				<thead class="thead-dark">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성 날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td>${dto.socialNum}</td>
							<td><a href="./select?socialNum=${dto.socialNum}">
									${dto.socialTitle}</a></td>
							<td>${dto.username}</td>
							<td>${dto.socialDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<ul class="pagination">
			<c:if test="${pager.pre}">
				<li class="page-item"><a class="page-link p" href="#"
					title="${pager.startNum-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item "><a class="page-link p" href="#"
					title="goPage('${pager.search}', ${i})">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-link p" href="#"
					title="goPage(${pager.search}, ${pager.lastNum+1})">다음</a></li>
			</c:if>
		</ul>
		<div class="input-group mt-3 mb-3">
			<form id="form" action="./list" class="form-inline">
				<input type="hidden" name="curPage" value="1" id="curPage">
				<div class="input-group-prepend">
					<select class="form-control" name="kind" id="kind">
						<option class="sel">제목</option>
						<option class="sel">내용</option>
						<option class="sel">작성자</option>
					</select>
				</div>
				<input type="text" class="form-control" name="search" id="search"
					value="${pager.search}" placeholder="">
				<div class="input-group-append">
					<button class="btn btn-success" type="submit">검색</button>
				</div>
			</form>
			<a href="./insert" class="btn btn-primary" role="button">작성</a>
		</div>
	</div>
	<c:import url="../template/footer.jsp"></c:import>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/functions.js"></script>
</body>
</html>