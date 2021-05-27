<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>member Login Page</h1>

<form action="./memberLogin"  method="post">
			<div class="form-group">
				<label for="username">ID</label> 
				<input type="text" class="form-control" id="username" name="username"
					aria-describedby="idlHelp"> 
					<small id="idlHelp"	class="form-text text-muted">
						We'll never share your email with anyone else.
					</small>
			</div>
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" class="form-control" id="password" name="password">
			</div>
			
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1">Check me
					out</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
</body>
</html>