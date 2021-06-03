<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Hello, world!</title>
</head>
<body>
<h2>Product Insert Page</h2>
	<form action="./insert" method="POST" enctype="multipart/form-data">

		<div class="productimg">
			<label>상품 이미지</label>
			<input type="file" name="file" multiple>
		</div>
		
	  <div class="form-group">
	    <label>상품 명</label>
	    <input type="text" name="productName">
	  </div>
	    <div class="form-group">
	    <label>판매자</label>
	    <input type="text" name="username">
	  </div>
	  	<div>
	  	<label>카테고리</label>
		  <div class="form-group">
		    <label for="category">Example select</label>
		    <select class="form-control" id="category" name="categoryName">
		      <option>디지털/가전</option>
		      <option>가구/인테리어</option>
		      <option>유아동/유아도서</option>
		      <option>생활/가공식품</option>
		      <option>스포츠/레저</option>
		      <option>여성의류/잡화</option>
		      <option>남성의류/잡화</option>
		      <option>게임/취미</option>
		      <option>뷰티/미용</option>
		      <option>반려동물용품</option>
		      <option>도서/티켓/음반</option>
		      <option>삽니다</option>
		    </select>
		    
		  </div>
		</div>
	    <div class="form-group">
	    <label>상품 설명</label>
	    <input type="text" name="productContent">
	  </div>
	    <div class="form-group">
	    <label>상품 가격</label>
	    <input type="text" name="productPrice">
	  </div>
	  
	   <input type="hidden" name="productNum">
	  
	  <div>
			<input type="file" name="file">
			<input type="file" name="file">
		</div>
	  
	 <button class="btn btn-outline-secondary">Write</button><br><br><br>
</form>

</body>
</html>
