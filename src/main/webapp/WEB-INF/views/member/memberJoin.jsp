<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>memberJoin Page</h1>

<form action="./memberJoin" method="post">

			<div class="form-group">
				<label for="username">Id:</label> <input type="text"
					class="form-control myCheck" id="username" name="username">			
			</div>
			
				<div class="form-group">
				<label for="pw">Pw:</label> <input type="text"
					class="form-control myCheck" id="password" name="password">
			</div>

			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control myCheck" id="title" name="title">
			</div>

			<div class="form-group">
				<label for="phone">Phone:</label>
				<input type="text"
					class="form-control myCheck" id="phone" name="phone">
			</div>
			
					<div class="form-group">
				<label for="email">Email:</label>
					<input type="text"
					class="form-control myCheck" id="email" name="email">
			</div>
			
		 <input type="submit" value="Join">
 
 </form>

</body>
</html>