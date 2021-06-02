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


		<h2>Product List</h2>
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
			
				<c:forEach items="${list}" var="dto">
					<tr>
						<td><a href="#"><img src="/images/바탕화면.jpg" width="90" height="90"/></a></td>
						<td>${dto.productNum}</td>
						<td>[ ${dto.categoryCode} ]<a href="./select?productNum=${dto.productNum}"> ${dto.productName}</a></td>
						<td>${dto.username}</td>
						<td>${dto.productDate}</td>
						<td>${dto.productHit}</td>
					</tr>
				</c:forEach>
			  
			</tbody>
		</table>

		
		
		 <div class="container">

			<ul class="pagination">
				<c:if test="${pager.pre}">
					<li class="page-item"><a class="page-link p" href="#" title="${pager.startNum-1}">Previous</a></li>
				</c:if>

				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					<li class="page-item"><a class="page-link p" href="./list?curPage=${i}&kind=${pager.kind}&search=${pager.search}" title="${i}">${i}</a></li>
				</c:forEach>

				<c:if test="${pager.next}">
					<li class="page-item"><a class="page-link p" href="#" title="${pager.lastNum+1}">Next</a></li>
				</c:if>
			</ul>


			<div class="input-group mt-3 mb-3">
				<form id="frm" action="./list" class="form-inline">
				<input type="hidden" name="curPage" value="1" id="curPage">
					<div class="input-group-prepend">
						<select class="form-control" name="kind" id="kind">
							<option class="sel">ProductName</option>
							<option class="sel">ProductContent</option>
							<option class="sel">Username</option>
						</select>
					</div>
					<input type="text" class="form-control" name="search" id="search" placeholder="" value="${pager.search}">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit">Search</button>
					</div>
				</form>
			</div>


			<a href="./insert" class="btn btn-primary" role="button">Write</a>
			

		</div>
</div>



</body>
</html>