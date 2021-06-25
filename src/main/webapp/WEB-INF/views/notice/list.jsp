<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/noticeList.css"/>
<title>공지사항</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
<div class="hidden page-value">${noticePager.page}</div>
	<div class="top-container">
		<div class="top__title">공지사항</div>
		<form class="top__form-search" id="frm" action="./list" class="form-inline">
			<input type="text" class="search__input" name="search" id="search" value="${noticePager.search}" placeholder="">
			<button type="submit" class="btn btn-search">검색</button>		
		</form>
	</div>
	<div class="notice-container">
		<c:forEach items="${list}" var="notice">
			<div class="ntc__card" onclick="manageContent()">
				<div class="ntc__left">
					<div class="ntc__title">
						${notice.noticeTitle}
					</div>
					<div class="ntc__date">
						${notice.noticeDate}
					</div>
				</div>
				<i class="fas fa-sort-down"></i>
			</div>
			<div class="content-wrapper">
				<div class="ntc__btn">
					<a href="./update?noticeNum=${notice.noticeNum}" class="btn btn-edit">수정</a>
					<div class="btn btn-del" onclick="deleteNotice('${notice.noticeNum}')">삭제</div>
				</div>
				<div class="ntc__content">
					${notice.noticeContent}
				</div>
			</div>
		</c:forEach>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
		<div class="btn-insert-wrapper">
			<a href="./insert" class="btn btn-insert">작성</a>
		</div>
	</sec:authorize>
	<ul class="page-container list-page">
		<c:if test="${noticePager.pre}">	
			<li><a class="page-item arrow" href="./list?page=${noticePager.startNum-1}&kind=${noticePager.kind}&search=${noticePager.search}"><i class="fas fa-angle-double-left"></i></a></li>
		</c:if>
		<c:forEach begin="${noticePager.startNum}" end="${noticePager.lastNum}" var="i">
			<li><a class="page-item code_${i}" href="./list?page=${i}&kind=${noticePager.kind}&search=${noticePager.search}">${i}</a></li>
		</c:forEach>
		<c:if test="${noticePager.next}">
			<li><a class="page-item arrow" href="./list?page=${noticePager.lastNum+1}&kind=${noticePager.kind}&search=${noticePager.search}"><i class="fas fa-angle-double-left"></i></a></li>
		</c:if>
	</ul>
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/functions.js"></script>
<script type="text/javascript" src="../resources/js/noticeList.js"></script>
</body>
</html>