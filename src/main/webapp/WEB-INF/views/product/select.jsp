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
					<th>CATEGORY</th>
					<th>STATUS</th>
					<th>NAME</th>
					
					<th>DATE</th>
					<th>HIT</th>
					<th>HEART</th>
				</tr>
			</thead>
			<tbody>

					<tr>
						<td>
						<div>
							<c:forEach items="${vo.files}" var="file">
								<a href="/resources/upload/product/${file.fileName}">
								<img src="/resources/upload/product/${file.fileName}" width="100px" height="100px">
								</a>
							</c:forEach>
						</div>
						</td>
						<td>${vo.productNum}</td>
						<td>${vo.productName}</td>
						<td>${vo.category.categoryName}</td>
						<td>${vo.productStatus}</td>
						<td>${vo.username}</td>
						<td>${vo.productDate}</td>
						<td>${vo.location.locationName}</td>
						<td>${vo.productHeart}</td>
					</tr>
			  
			</tbody>
		</table>


		<div>
			<input id="heartNumber" type="hidden" title="${heart}">
			<input id="productNum" type="hidden" title="${vo.productNum}">
		</div>
		
		<div>
			<a href="./delete?productNum=${vo.productNum}" id="delete" class="btn btn-primary" role="button">Delete</a>
			<a href="./update?productNum=${vo.productNum}" role="button"> Update </a>
			<a href="#" class="btn btn-primary" role="button">가격제안</a>
		<!-- 	<a href="#" class="btn btn-danger" role="button">신고하기</a> -->
		
			<a class="heart" onclick="confirm();">
				<img id="heart" src="" width="50px" height="50px"> 
			</a>
			

  <!-- Button to Open the Modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#report">
    신고하기
  </button>

<div class="container">
  <!-- The Modal -->
  <div class="modal fade" id="report">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">신고하기</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
         <form action="#" method="post">
						<div class="form-group">
							<label>신고 제목</label> 
							<input type="text"class="form-control" id="reportTitle" name="reportTitle" required>
						</div>
						<div class="form-group">
							<label>신고 내용</label> 
							<textarea class="form-control" id="reportContent" name="reportContent" required ></textarea>
						</div>
					</form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">신고하기</button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>
		<!-- 신고하기 -->	

		</div>

	</div>




<script type="text/javascript" src="/resources/js/productSelect.js"></script>
</body>
</html>