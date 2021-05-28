<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>memberJoin Page</h1>

<form:form modelAttribute="memberVO" id="frm" action="./memberJoin" method="post">


			<div class="form-group">
				<label for="username">Id:</label> 
				<form:input class="form-control myCheck"
				 type="text" id="username" name="username" path="username"/>	
				<form:errors path="username"></form:errors>
				
			</div>
			
				<div class="form-group">
				<label for="pw">Pw:</label>
				 <form:input type="text"
					class="form-control myCheck" id="password" name="password" path="password"/>
					<form:errors path="password"></form:errors>
			</div>
			
				<div class="form-group">
				<label for="pw">PwCheck:</label> <form:input type="text"
					class="form-control myCheck" id="password1" name="password1" path="password1"/>
					<form:errors path="password1"></form:errors>
			</div>

			<div class="form-group">
				<label for="name">Name:</label> <form:input type="text"
					class="form-control myCheck" id="name" name="name" path="name"/>
					<form:errors path="name"></form:errors>
			</div>

			<div class="form-group">
				<label for="phone">Phone:</label>
				<form:input type="text"
					class="form-control myCheck" id="phone" name="phone" path="phone"/>
					<form:errors path="phone"></form:errors>
			</div>
			
					<div class="form-group">
				<label for="email">Email:</label>
					<form:input type="text"
					class="form-control myCheck" id="email" name="email" path="email"/>
					<form:errors path="email"></form:errors>
			</div>
			
		 <input type="submit" value="Join">
 
</form:form>

</body>
</html>