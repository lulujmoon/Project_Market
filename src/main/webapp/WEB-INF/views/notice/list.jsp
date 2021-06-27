<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/setting.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class=container>
<h1>NoticeList</h1>

<sec:authorize access="hasRole('ADMIN')">
<a href="./insert">글작성</a>
</sec:authorize>

<c:forEach items="${list}" var="dto">
<div class="num"> ${dto.noticeNum}</div> <br>
<div class="title"><a href="./select?noticeNum=${dto.noticeNum}">${dto.noticeTitle}</a></div><br>

</c:forEach>
<br>


	  <div class="pagination">
	  
	   <c:if test="${noticePager.pre}">	
	  <a href="./list?curPage=${noticePager.startNum-1}&kind=${noticePager.kind}&search=${noticePager.search}">&laquo;</a>
	   </c:if>
	   
	   <c:forEach begin="${noticePager.startNum}" end="${noticePager.lastNum}" var="i">
	   
	   <a class="page-link p" href="./list?curPage=${i}&kind=${noticePager.kind}&search=${noticePager.search}" title="${i}">${i}</a>
	   </c:forEach>
	    
	    <c:if test="${noticePager.next}">
	    <a href="./list?curPage=${noticePager.lastNum+1}&kind=${noticePager.kind}&search=${noticePager.search}" title="${noticePager.lastNum+1}">&raquo;</a>
	    </c:if>
	    
	  </div>
	  
	  <div class="searchform"> 
	<div class="input-group mt-3 mb-3">
	<form id="frm" action="./list" class="form-inline">
		<input type="hidden" name="curPage" value="1" id="curPage">
	  <div class="input-group-prepend">
	   <select class="form-control" name="kind" id="kind" >
	    <option class="sel">Contents</option>
	    <option class="sel">Title</option>
	  </select>
	  </div>
	  <input type="text" class="form-control" name="search" id="search" value="${noticePager.search}" placeholder="">
	    <div class="input-group-append">
	    <button class="gradient-btn" type="submit">Search</button>
	  </div>
	 </form> 
	</div> 
	</div>
	</div>
<c:import url="../template/footer.jsp"></c:import>
</body>
</html>