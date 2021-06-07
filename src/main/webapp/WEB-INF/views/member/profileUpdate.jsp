<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>프러필업뎃</h1>

<h1>${file.username}</h1>

<form action="./profileUpdate" method="post" enctype="multipart/form-data">
		<div class="form-group">
				<label for="fileNum">fileNum</label> 
				<input type="text" class="form-control etc"	id="fileNum" name="fileNum" readonly="readonly" value="${file.fileNum}">
			</div>
	
	<div class="form-group">
				<label for="avatar">Avatar</label> 
				<input type="file" class="form-control etc"	id="avatar" name="avatar">
			</div>
			<div class="join-btn">
				<button type="submit" class="btn-wide btn-submit">ok</button>
			</div>
			</form>
</body>
</html>