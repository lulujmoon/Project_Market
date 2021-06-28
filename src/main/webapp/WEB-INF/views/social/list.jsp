<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../template/setting.jsp"></c:import>
<title>우리 동네</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/socialList.css"/>
<link rel="stylesheet" href="/resources/css/socialCard.css"/>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<input type="hidden" class="category" value="category" name="categoryCode">
	<h2>우리동네</h2>
	<div class="category-container">
		<h3 class="category__title">카테고리</h3>
		<ul class="category__list">
			<c:forEach items="${categories}" var="category">
				<li class="category__item"><a href="./list?categoryCode=${category.categoryCode}" onclick="${category.categoryCode}">${category.categoryName}</a></li>
			</c:forEach>
		</ul>
		<h3 class="category__title">지역</h3>
		<ul class="category__list list-myLocation">
			<li class="category__item-location code_0"><a href="./list?page=1&categoryCode=${pager.categoryCode}&keyword=${pager.keyword}">전체</a></li>
			<c:if test="${locations.size()!=0}">
				<c:forEach begin="1" end="3" var="i">
					<li class="category__item-location code_${i}"><a href="./list?page=1&categoryCode=${pager.categoryCode}&myLocation=${i}&keyword=${pager.keyword}">${locations[i].locationName}</a></li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="social-container">
		<c:forEach items="${list}" var="social">					
			<div class="scl__card" onclick="goSelect(${social.socialNum})">
				<div class="scl__top-wrapper">
					<div class="scl__title">${social.socialTitle}</div>
					<div class="scl__date">${social.socialDate}</div>
					<div class="scl__location"><i class="fas fa-map-marker-alt"></i> ${social.location.locationName}</div>
				</div>
				<div class="scl__mid-wrapper">
					<div class="scl__writer">${social.writer.name}</div>
					<span>&middot;</span>
					<div class="scl__location">지역</div>
					<span>&middot;</span>
					<div class="scl__category">${social.socialCategory.categoryName}</div>
				</div>
				<div class="scl__content">${social.socialContent}</div>
				<div class="scl__response">
					<div class="scl__good"><i class="far fa-thumbs-up"></i> 0</div>
					<div class="scl__comment"><i class="far fa-comment-dots"></i> 2</div>
				</div>
			</div>
		</c:forEach>
	</div>	

		<ul class="page-container">
			<c:if test="${pager.pre}">
				<li><a class="page-item arrow" href="#" title="${pager.startNum-1}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a class="page-item code_${i}" href="#" title="goPage('${pager.search}', ${i})">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.next}">
				<li class="page-item"><a class="page-item arrow" href="#"	title="goPage(${pager.search}, ${pager.lastNum+1})">다음</a></li>
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
	<script type="text/javascript" src="/resources/js/socialList.js"></script>
</body>
</html>