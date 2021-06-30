<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/socialSelect.css" />
<title>우리동네</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="title-wrapper">
		<div class="post__category">${social.socialCategory.categoryName}</div>
		<div class="post__title">${social.socialTitle}</div>
	</div>
	<div class="info-wrapper">
		<div class="post__writer-wrapper">
			<img src="../resources/upload/member/${social.writerFile.fileName}" class="writer__avatar"/>
			<div class="writer__name">${social.writer.name}</div>
			<span class="writer__middot">&middot;</span>
			<div class="post__location">${social.location.locationName}</div>
		</div>
		<div class="post__right-wrapper">
			<div class="post__date">${social.socialDate}</div>
			<div class="icon_good">
				<i class="fas fa-thumbs-up"></i> ${social.socialGood}
			</div>
		</div>
	</div>
	<div class="post__content">${social.socialContent}</div>

	<div class="table-box2">
		<a class="insert-button" href="./update?socialNum=${social.socialNum}"
			class="btn btn-primary" role="button">수정</a>
		<a class="insert-button" href="./delete?socialNum=${social.socialNum}"
			class="btn btn-primary" role="button">삭제</a>
		<div class="btn-good"></div>
		<div class="btn-report" onclick="openSocialReport('${social.socialNum}')"><i class="fas fa-exclamation-triangle"></i> 신고</div>
	</div>
	<div class="hidden">
		<input type="hidden" class="goodval" value="${good}">
		<input type="hidden" class="socialNum" value="${social.socialNum}">
	</div>
	<br><br><br><br>

	<!--  댓글  -->
	<div class="table-box2">
		<label for="comment">댓글</label>
		<form id="form" action="../comment/insert?socialNum=${social.socialNum}" method="post">
			<input type="hidden" id="socialNum" name="socialNum">
			<textarea class="form-control myCheck" id="commentContent"
				name="commentContent" placeholder="내용을 입력하세요."></textarea>
			<input type="text" readonly="readonly" value="${social.username}" name="username">
			<input type="submit" value="작성" />
		</form>
	</div>
	<br>

	<!-- 댓글 리스트 -->
	<c:if test="${comment ne null}">
		<c:forEach items="${list}" var="comment">
			<ul class="table-box2">
				<li><c:catch>
						<c:forEach begin="1" end="${comment.depth}">
								&ensp;>
						</c:forEach>
					</c:catch>
					${comment.commentContent}</li>
				<li>
					<div class="container">
						${comment.username}<br>${comment.commentDate}
						<a href="../comment/update?commentNum=${comment.commentNum}">수정</a>
						<a href="../comment/delete?commentNum=${comment.commentNum}">삭제</a>
						<a href="../comment/reply?socialNum=${social.socialNum}&commentNum=${comment.commentNum}">답글</a>
					</div>
				</li>
			</ul>
		</c:forEach>
	</c:if>
</div>

	<c:import url="../template/footer.jsp"></c:import>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/socialSelect.js"></script>
</body>
</html>