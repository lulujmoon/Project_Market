<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>마이페이지</h1>

	<table>
		<tbody>
			<tr>
				<th scope="row">
					<div class="thcell">
						사용자 <span class="word_br"> ID </span>
					</div>
				</th>
				<td>
					<div class="tdcell">
						<p class="contxt_tit">${principal.username}</p>
						<p class="contxt_desc">로그인 전용 아이디입니다.</p>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">
					<div class="thcell">
						사용자 <span class="word_br">PASSWORD</span>
					</div>
				</th>
				<td>
					<div class="tdcell">
						<p class="contxt_tit">password</p>
						<p class="contxt_desc">패스워드를 변경하실수있습니다.</p>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">
					<div class="thcell">
						사용자 <span class="word_br">이름</span>
					</div>
				</th>
				<td>
					<div class="tdcell">
						<p class="contxt_tit">${principal.name}</p>
						<p class="contxt_desc">사용자 이름입니다.</p>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">
					<div class="thcell">
						사용자 <span class="word_br">이메일</span>
					</div>
				</th>
				<td>
					<div class="tdcell">
						<p class="contxt_tit">${principal.email}</p>
						<p class="contxt_desc">계정의 이메일 주소입니다.</p>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">
					<div class="thcell">
						사용자 <span class="word_br">전화번호</span>
					</div>
				</th>
				<td>
					<div class="tdcell">
						<p class="contxt_tit">${principal.phone }</p>
						<p class="contxt_desc">계정의 전화번호입니다.</p>
					</div>
				</td>
			</tr>


		</tbody>
	</table>
<a href="#" class="btn_model"><b class="btn2">수정</b></a>
								
								

</body>
</html>