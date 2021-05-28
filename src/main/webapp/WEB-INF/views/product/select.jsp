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
						<td>${vo.productHit}</td>
					</tr>
			  
			</tbody>
		</table>

		


			<a href="./delete?productNum=${vo.productNum}" class="btn btn-primary" role="button">Delete</a>
			

		</div>




</body>
</html>