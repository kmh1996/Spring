<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>
</head>
<body>
	<h3>REGISTER BOARD</h3>
	<form method="POST">
		<div>
			<label>Title</label>
			<input type="text" name="title" required/>
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" required rows=3></textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type="text" name="writer" required/>
		</div>
		<div>
			<input type="submit" value="작성완료"/>
		</div>
	</form>
</body>
</html>








