<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/setting.jsp"></c:import>
<title>우리동네</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style type="text/css">
.main-container {
	padding-top: 125px;
	width: 1252px;
	min-height: 55vh;
	margin: 30px auto;
	display: flex;
	flex-direction: column;
}

h2 {
	text-align: center;
}

.button {
	padding-top: 25px;
	width: 1252px;
	margin: 30px auto;
	display: flex;
	flex-direction: column;
	text-align: center;
}

.table-box {
	border: 3px solid #f8cb45;
	width: 80%;
	height: auto;
	margin-left: 10%;
	margin-bottom: 2%;
	padding: 0 0 0;
	color: black;
	text-align: center;
	font-size: 12pt;
}

.table-box2 {
	border: 3px solid white;
	width: 80%;
	height: auto;
	margin-left: 10%;
	margin-bottom: 2%;
	padding: 0 0 0;
	color: black;
	text-align: center;
	font-size: 12pt;
}

.comment-container {
	border: 1px solid black;
	width: 95%;
	margin-top: 2%;
	margin-left: 2%;
}
</style>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>
	<div class="main-container">
		<h2>${social.socialTitle}</h2>
		<table class="table-box">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${social.socialNum}</td>
					<td>${social.username}</td>
					<td>${social.socialDate}</td>
				</tr>
			</tbody>
		</table>
		<table class="table-box2">
			<thead class="thead-dark">
				<tr>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${social.socialContent}</td>
				</tr>
			</tbody>
		</table>
	</div>

	<a class="good">
		<img id="good" src="" width="50px" height="50px">
	</a>
	<a class="insert-button" href="./update?socialNum=${social.socialNum}"
		class="btn btn-primary" role="button">수정</a>
	<a class="insert-button" href="./delete?socialNum=${social.socialNum}"
		class="btn btn-primary" role="button">삭제</a>
	<br>
	<br>
	<br>
	<br>

	<!--  댓글  -->
	<div class="table-box2">
		<label for="comment">댓글</label>
		<form id="form"
			action="../comment/insert?socialNum=${social.socialNum}"
			method="post">
			<input type="hidden" id="socialNum" name="socialNum">
			<textarea class="form-control myCheck" id="commentContent"
				name="commentContent" placeholder="내용을 입력하세요."></textarea>
			<input type="text" readonly="readonly" value="${social.username}"
				name="username"> <input type="submit" value="작성" />
		</form>
	</div>
	<br>

	<c:if test="${comment ne null}">
		<c:forEach items="${list}" var="comment">
			<ul class="table-box2">
				<li><c:catch>
						<c:forEach begin="1" end="${comment.depth}">
								&ensp;>
							</c:forEach>
					</c:catch> ${comment.commentContent}</li>
				<li>
					<div class="container">
						${comment.username}<br>${comment.commentDate} <a
							href="../comment/update?commentNum=${comment.commentNum}">수정</a>
						<a href="../comment/delete?commentNum=${comment.commentNum}">삭제</a>
						<a
							href="../comment/reply?socialNum=${social.socialNum}&commentNum=${comment.commentNum}">답글</a>
					</div>
			</ul>
		</c:forEach>
	</c:if>
	<c:import url="../template/footer.jsp"></c:import>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/functions.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			let goodval = ${good}

			if (goodval>0) {
				console.log("good : "+goodval);
				$("#good").prop("src", "/resources/images/빨강.png");
				$(".good").prop("name", goodval);
			} else {
				console.log("good : "+goodval);
				$("#good").prop("src", "/resources/images/검정.png");
				$(".good").prop("name", goodval);
			}

			$(".good").on("click", function() {

				let that = $(".good");

				let sendData = {'socialNum' : '${socialVO.socialNum}', 'good' : that.prop('name')};
				$.ajax({
					url : '/social/good',
					type : 'POST',
					data : sendData,
					success : function(data) {
						that.prop('name', data);
						if (data == 1) {
							$("good").prop("src", "/resources/images/빨강.png");
						} else {
							$("good").prop("src", "/resources/images/검정.png");
						}

					}
				})
			})
		})
	</script>
</body>
</html>