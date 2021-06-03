<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
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
					<th>HEART</th>
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
						<td>${vo.productHeart}</td>
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
			

				<a class="heart" onclick="if ( confirm('찜 하시겠습니까?')==false ){return false;}">
					<img id="heart" src="" width="50px" height="50px"> 
				</a>
	
			

		</div>




<script type="text/javascript">
$(document).ready(function(){
	
	let heartval = ${heart}
	
	if(heartval>0) {
		console.log("heart : "+heartval);
		$("#heart").prop("src", "/resources/images/빨강.png");
		$(".heart").prop("name", heartval);
	//	$(".heart").prop("onclick", "if ( confirm('찜을 취소하시겠습니까?')==false ){return false;}");
	} else {
		console.log("heart : "+heartval);
		$("#heart").prop("src", "/resources/images/검정.png");
		$(".heart").prop("name", heartval);
	//	$(".heart").prop("onclick", "if ( confirm('찜을 취소하시겠습니까?')==false ){return false;}");
	}
	
	$(".heart").on("click", function(){
		
		let that = $(".heart");
		
		let sendData = {'productNum' : '${productVO.productNum}', 'heart' : that.prop('name')};
		$.ajax({
			url : '/product/heart',
			type : 'POST',
			data : sendData,
			success : function(data) {
				that.prop('name', data);
				if(data==1) {
					$("heart").prop("src","/resources/images/빨강.png");
				} else {
					$("heart").prop("src","/resources/images/검정.png");
				}
				
			}
		})
	

		
	})
})
</script>
</body>
</html>