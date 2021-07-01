<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/store.css">
<link rel="stylesheet" href="/resources/css/storeSocialCard.css"/>
<title>내 상점</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
<div class="hidden page-value">${socialPager.page}</div>
	<c:import url="./storeCommon.jsp"></c:import>
		<div class="board__contents">
			<c:if test="${socials.size() == 0}">
				<div class="empty__info">
					아직 작성한 글이 없습니다.
				</div> 
			</c:if>
			<c:if test="${socials.size() > 0}">
				<div class="social-container">
					<c:forEach items="${socials}" var="social">
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
						<li><a class="page-item arrow" href="${pageContext.request.contextPath}/store/${member.code}/socials?page=${socialPager.startNum-1}&kind=${socialPager.kind}&search=${socialPager.search}"><i class="fas fa-angle-double-left"></i></a></li>
					</c:if>
					<c:forEach begin="${socialPager.startNum}" end="${socialPager.lastNum}" var="i">
						<li><a class="page-item code_${i}" href="${pageContext.request.contextPath}/store/${member.code}/socials?page=${i}&kind=${socialPager.kind}&search=${socialPager.search}">${i}</a></li>
					</c:forEach>
					<c:if test="${socialPager.next}">
						<li class="page-item"><a class="page-item arrow" href="${pageContext.request.contextPath}/store/${member.code}/socials?page=${socialPager.lastNum+1}&kind=${socialPager.kind}&search=${socialPager.search}"><i class="fas fa-angle-double-left"></i></a></li>
					</c:if>
				</ul>
			</c:if>
		</div>
	</div>

</div>	<!-- 스타팅 태그는 임포트한 부분에 포함되어 있음 -->

<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="/resources/js/store.js"></script>
</body>
</html>