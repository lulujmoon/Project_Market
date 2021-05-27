<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body>
<h2>Product Insert Page</h2>
<form action="./productInsert" method="post">
<!--   <div class="form-group">
	<label for="thumbnail">썸네일</label>
	<input type="file" name="file" required>
  </div> -->
  <div class="form-group">
    <label>상품명</label>
    <input type="text" class="form-control" id="productName">
  </div>
    <div class="form-group">
    <label>판매자</label>
    <input type="text" class="form-control" id="username">
  </div>
  <div class="form-group">
    <label>카테고리</label>
    <input type="text" class="form-control" id="productCategory">
  </div>
    <div class="form-group">
    <label>상품 설명</label>
    <input type="text" class="form-control" id="productContent">
  </div>
    <div class="form-group">
    <label>상품 가격</label>
    <input type="text" class="form-control" id="productPrice">
  </div>
</form>
 <input type="submit" id="btn" value="WRITE" class="btn btn-primary">
</body>
</html>
