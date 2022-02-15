<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MODIFY</title>
</head>
<body>
	<!-- board -->
	<h3>MODIFY BOARD</h3>
	<form method="POST">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<div>
			<label>Title</label>
			<input type="text" name="title" value="${board.title}" required/>
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" required rows=3>${board.content}</textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type="text" name="writer" value="${board.writer}" required/>
		</div>
		<div>
			<input type="submit" value="MODIFY"/>
		</div>
	</form>
</body>
</html>








