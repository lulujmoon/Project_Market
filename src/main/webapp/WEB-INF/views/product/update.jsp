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
<h2>Product Update Page</h2>

	<form action="./update" method="post">
		<input type="hidden" name="productNum" value="${vo.productNum}">
	<div>
		<label>상품 사진</label>
		<c:forEach items="${vo.files}" var="file">
			<input type="file" name="file" value="${file.fileName}">${file.originName}
		</c:forEach>
	</div>
	  <div class="form-group">
	    <label>상품 명</label>
	    <input type="text" value="${vo.productName}" name="productName">
	  </div>
	    <div class="form-group">
	    <label>판매자</label>
	    <input type="text" value="${vo.username}" name="username">
	  </div>
	  <div class="form-group">
	    <label>카테고리</label>
	    <input type="text" value="${vo.categoryCode}" name="categoryCode">
	  </div>
	    <div class="form-group">
	    <label>상품 설명</label>
	    <input type="text" value="${vo.productContent}" name="productContent">
	  </div>
	    <div class="form-group">
	    <label>상품 가격</label>
	    <input type="text" value="${vo.productPrice}" name="productPrice">
	  </div>
	  
	 <button class="btn btn-outline-secondary">Update</button><br><br><br>
</form>
 
</body>
</html>
