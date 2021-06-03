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
		<div>
			<input type="file" name="file">
			<input type="file" name="file">
		</div>
		
	  <div class="form-group">
	    <label>상품 명</label>
	    <input type="text" name="productName">
	  </div>
	    <div class="form-group">
	    <label>판매자</label>
	    <input type="text" name="username">
	  </div>
	  	<div class="category-container">
	  	<label>카테고리</label>
	  	<select>
			<option value=""></option>
	  	</select>
		</div>
	    <div class="form-group">
	    <label>상품 설명</label>
	    <input type="text" name="productContent">
	  </div>
	    <div class="form-group">
	    <label>상품 가격</label>
	    <input type="text" name="productPrice">
	  </div>
	  
	 <button class="btn btn-outline-secondary">Write</button><br><br><br>
</form>
 
</body>
</html>
