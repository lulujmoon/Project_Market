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
	<div class="hidden">${socialPager.categoryCode}</div>
	<div class="hidden">${myLocation}</div>
	<div class="hidden">${socialPager.page}</div>
	<div class="top-container">
		<div class="top__title" onclick="goSocialHome()">우리동네</div>
		<div class="top-right-container">
			<form class="top__form-search" id="frm" action="../list" class="form-inline">
				<select class="search__select" name="kind" id="kind">
					<option class="sel" value="title">제목</option>
					<option class="sel" value="content">내용</option>
					<option class="sel" value="writer">작성자</option>
				</select>
				<i class="fas fa-sort-down"></i>
				<input type="text" class="search__input" name="search" id="search" value="${socialPager.search}" placeholder="">
				<button type="submit" class="btn btn-search">검색</button>		
			</form>
			<div class="btn-insert-wrapper">
				<a href="/social/insert" class="btn btn-insert">작성</a>
			</div>
		</div>
	</div>
	<div class="category-container list-category">
		<h3 class="category__title">카테고리</h3>
		<ul class="category__list">
			<li class="category__item code_0"><a href="./list">전체</a></li>
			<c:forEach begin="0" end="${categories.size()-1}" var="i">
				<li class="category__item code_${i+1}"><a href="../list?categoryCode=${categories[i].categoryCode}&myLocation=${myLocation}&kind=${socialPager.kind}&search=${socialPager.search}">${categories[i].categoryName}</a></li>
			</c:forEach>
		</ul>
		<h3 class="category__title">지역</h3>
		<ul class="category__list list-myLocation">
			<li class="category__item-location code_0"><a href="../list?page=1&categoryCode=${socialPager.categoryCode}&search=${socialPager.search}">전체</a></li>
			<c:if test="${locations.size()!=0}">
				<c:forEach begin="1" end="3" var="i">
					<li class="category__item-location code_${i}"><a href="../list?page=1&categoryCode=${socialPager.categoryCode}&myLocation=${i}&kind=${socialPager.kind}&search=${socialPager.search}">${locations[i].locationName}</a></li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<c:if test="${list[0].socialTitle != null}">
		<div class="social-container">
			<c:forEach items="${list}" var="social">
				<div class="scl__card" onclick="goSocialSelect(${social.socialNum})">
					<div class="scl__top-wrapper">
						<div class="scl__title">${social.socialTitle}</div>
						<div class="scl__date">${social.socialDate}</div>
					</div>
					<div class="scl__mid-wrapper">
						<div class="scl__writer">${social.writer.name}</div>
						<span>&middot;</span>
						<div class="scl__location">${social.location.locationName}</div>
						<span>&middot;</span>
						<div class="scl__category">${social.socialCategory.categoryName}</div>
					</div>
					<div class="scl__content">${social.socialContent}</div>
					<div class="scl__response">
						<div class="scl__good"><i class="far fa-heart"></i> ${social.socialGood}</div>
						<div class="scl__comment"><i class="far fa-comment-dots"></i> ${social.commentCount}</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<ul class="page-container list-page">
			<c:if test="${socialPager.pre}">
				<li><a class="page-item arrow" href="../list?page=${socialPager.startNum-1}&kind=${socialPager.kind}&search=${socialPager.search}"><i class="fas fa-angle-double-left"></i></a></li>
			</c:if>
			<c:forEach begin="${socialPager.startNum}" end="${socialPager.lastNum}" var="i">
				<li><a class="page-item code_${i}" href="../list?page=${i}&kind=${socialPager.kind}&search=${socialPager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${socialPager.next}">
				<li class="page-item"><a class="page-item arrow" href="../list?page=${socialPager.lastNum+1}&kind=${socialPager.kind}&search=${socialPager.search}"><i class="fas fa-angle-double-left"></i></a></li>
			</c:if>
		</ul>
	</c:if>
	<c:if test="${list.size() == 0}">
		<div class="zero-container">
			조회된 글이 없습니다.
		</div>
	</c:if>
</div>
<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/socialList.js"></script>
</body>
</html>