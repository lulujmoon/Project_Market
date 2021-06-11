<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<c:import url="../template/setting.jsp"></c:import>
<link rel="stylesheet" href="/resources/css/productNote.css"> 
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>판매상품 등록</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<form class="form" id="uploadForm" action="./insert" method="POST" enctype="multipart/form-data" onsubmit="return submitCheck();">


			<div id=file>
			<input type="button" id="add" value="ADD">
			<div id="thumb">
				<div class="inputimg">
						<input type="file" name="file" accept="image/*" required class="img" onchange="previewImage(this,0)">
						<div id="preview0"></div>
					</div>

			 </div>
			</div>
		
		
		<div class="form-group">
	    <label>상품 명</label>
	    <input type="text" name="productName" required>
	  </div>
	    <div class="form-group">
	    <label>판매자</label>
	    <input readonly="readonly" type="text" name="username" value="${principal.username}">
	  </div>
	  	<div>
		  <div class="form-group">
		    <label for="category">카테고리</label>
		    <select class="form-control" id="category" name="categoryCode">
		      <option value="1">디지털/가전</option>
		      <option value="2">가구/인테리어</option>
		      <option value="3">유아동/유아도서</option>
		      <option value="4">생활/가공식품</option>
		      <option value="5">스포츠/레저</option>
		      <option value="6">여성의류</option>
		      <option value="7">여성잡화</option>
		      <option value="8">남성의류</option>
		      <option value="9">남성잡화</option>
		      <option value="10">게임/취미</option>
		      <option value="11">뷰티/미용</option>
		      <option value="12">반려동물용품</option>
		      <option value="13">도서/티켓/음반</option>
		      <option value="14">삽니다</option>
		    </select>
		  </div>
		</div>
	    <div class="form-group">
	    <label for="productContents">상품 설명</label>
	    <textarea id="productContent" rows="5" name="productContent" required></textarea>
	  </div>
	    <div class="form-group">
	    <label>상품 가격</label>
	    <input type="text" name="productPrice" required>
	  </div>
	   <div class="form-group">
	    <label>판매 지역</label>
	    <select class="form-control" id="locationCode" name="locationCode">
	   		 <c:forEach items="${location}" var="vo">
		      	<option value="${vo.locationCode}">${vo.locationName}</option>
		     </c:forEach>
		    </select>
	  </div>
	  
	   <input type="hidden" name="productNum">

	 <button class="btn-insert">Write</button><br><br><br>
	</form>
</div>


<c:import url="../template/footer.jsp"></c:import>
<script type="text/javascript" src="../resources/js/common.js"></script>
<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
<script type="text/javascript" src="../resources/js/productInsert.js"></script>
</body>
</html>