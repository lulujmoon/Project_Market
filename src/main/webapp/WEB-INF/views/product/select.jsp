<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>



	<div class="container">


		<h2>Product Select Page</h2>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>IMAGES</th>
					<th>NO</th>
					<th>SUBJECT</th>
					<th>NAME</th>
					<th>DATE</th>
					<th>HIT</th>
				</tr>
			</thead>
			<tbody>

					<tr>
						<td><a href="#"><img src="/images/바탕화면.jpg" width="300" height="300"/></a></td>
						<td>${vo.productNum}</td>
						<td>${vo.productName}</td>
						<td>${vo.username}</td>
						<td>${vo.productDate}</td>
						<td>${vo.location.locationName}</td>
					</tr>
			  
			</tbody>
		</table>

		<form name="frm_read" id="frm_read" method="get">
			<input type="hidden" id="productNum" value="${vo.productNum}">
			<input type="hidden" id="categoryCode" value="${vo.categoryCode}">
			<input type="hidden" id="username" value="${vo.username}">
		</form>
		


			<a href="./delete?productNum=${vo.productNum}" class="btn btn-primary" role="button">Delete</a>
			
			
			<a href="#" class="btn btn-primary" role="button">가격제안</a>
			<a href="#" class="btn btn-danger" role="button">신고하기</a>
					<a href="javascript: like_func();" onclick="if ( confirm('찜 하시겠습니까?')==false ){return false;}"><img src="/resources/images/검정.png" width="50px" height="50px"> </a>
		
	<!--  "./setHeart?id=${vo.productNum}"
			<c:choose>
				<c:when test="${username ne null}">
				</c:when>
				<c:otherwise>
					<a href="javascript: login_need();" onclick="if ( confirm('찜 하시겠습니까?')==false ){return false;}"><img src="/resources/images/검정.png" width="50px" height="50px"> </a>
				</c:otherwise>
			</c:choose>
		
		 -->	
		

		</div>



<script type="text/javascript" src="/resources/js/productSelect.js"></script>	
</body>
</html>