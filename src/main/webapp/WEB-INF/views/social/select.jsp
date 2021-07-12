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
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container">
	<div class="hidden">
		<input type="hidden" class="goodval" value="${good}">
		<input type="hidden" class="socialNum" value="${social.socialNum}">
	</div>
	<div class="top-container">
		<div class="title-wrapper">
			<div class="post__category">${social.socialCategory.categoryName}</div>
			<div class="post__title">${social.socialTitle}</div>
		</div>
		<c:if test="${principal.username == social.username}">
			<div class="top-btn-wrapper">
				<a class="insert-button" href="./update?socialNum=${social.socialNum}">수정</a>
				<a class="insert-button" href="./delete?socialNum=${social.socialNum}">삭제</a>
			</div>
		</c:if>
	</div>
	<div class="info-wrapper">
		<div class="post__writer-wrapper">
			<img src="../resources/upload/member/${social.writerFile.fileName}" class="writer__avatar" onclick="goStore(${social.writer.code})"/>
			<div class="writer__name" onclick="goStore(${social.writer.code})">${social.writer.name}</div>
			<span class="writer__middot">&middot;</span>
			<div class="post__location">${social.location.locationName}</div>
		</div>
		<div class="post__right-wrapper">
			<div class="icon_good">
				<i class="far fa-heart"></i> ${social.socialGood}
			</div>
			<div class="icon_good">
				<i class="far fa-comment-dots"></i> ${social.commentCount}
			</div>			
			<div class="post__date">${social.socialDate}</div>
		</div>
	</div>
	<div class="post__content">${social.socialContent}</div>
	<div class="response-container">
		<div class="btn-good"></div>
		<div class="response-left-wrapper">
			<a class="btn-report" href="#" onclick="openSocialReport('${social.socialNum}')"><i class="fas fa-exclamation-triangle"></i> 신고</a>
			<a href="./list" class="btn-list"><i class="fas fa-bars"></i> 목록</a>
		</div>
	</div>
	<div class="comment__container">
		<div class="comment__title">
			댓글 (${social.commentCount})
		</div>
		<c:if test="${comments.size() != 0}">
			<div class="comment__list">
				<c:forEach begin="0" end="${comments.size()-1}" var="i">
				<div class="comment__item-wrapper wrapper_${i}">
					<div class="comment__item">
							<c:catch>
								<c:forEach begin="1" end="${comments[i].depth}">
									<div class="depth-space"></div>
								</c:forEach>
							</c:catch>
							<div class="item-wrapper">
								<div class="item__top-container">
									<div class="top-left-wrapper">
										<div class="item__writer-wrapper">
											<img src="../resources/upload/member/${comments[i].writerFile.fileName}" class="item__writer__avatar" onclick="goStore(${social.writer.code})"/>
											<div class="item__writer__name" onclick="goStore(${comments[i].writer.code})">${comments[i].writer.name}</div>		
										</div>
										<div class="item__date">
											${comments[i].commentDate}
										</div>
									</div>
									<div class="item__btn-wrapper">
										<div onclick="manageReply(${i}, ${comments[i].commentNum})">답글</div>
										<c:if test="${comments[i].username == principal.username}">
											<div onclick="manageCommentEdit(${comments[i].commentNum})">수정</div>
											<a href="../comment/delete?commentNum=${comments[i].commentNum}">삭제</a>
										</c:if>
									</div>
								</div>
								<div class="item__content">
									${comments[i].commentContent}
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<form class="comment-form" action="../comment/insert" method="post">
			<input type="hidden" id="socialNum" name="socialNum" value="${social.socialNum}">
			<input type="hidden" value="${principal.username}" name="username">
			<textarea class="comment__content" id="commentContent" name="commentContent" placeholder="내용을 입력하세요." required></textarea>
			<input type="submit" class="btn-submit" value="작성" />
		</form>
	</div>
</div>

	<c:import url="../template/footer.jsp"></c:import>
	<script type="text/javascript" src="/resources/js/socialSelect.js"></script>
</body>
</html>