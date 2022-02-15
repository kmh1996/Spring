<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>read.jsp</title>
</head>
<body>
	<h3>READ BOARD</h3>
	<div>
		<div>
			<label>Title</label>
			<input type="text" name="title" value="${board.title}" readonly/>
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" readonly rows=3>${board.content}</textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type="text" name="writer" value="${board.writer}" readonly/>
		</div>
		<div>
			<button id="modify">Modify</button>
			<button id="remove">Delete</button>
			<button id="list">List</button>
		</div>
	</div>
	<form id="btnForm" method="POST">
		<input type="hidden" name="bno" value="${board.bno}"/>
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var formObj = $("#btnForm");
	$("#modify").click(function(){
		formObj.attr("action","modify");
		formObj.attr("method","GET");
		formObj.submit();
	});
	
	$("#remove").click(function(){
		formObj.attr("action","remove");
		formObj.submit();
	});
	
	$("#list").click(function(){
		self.location="listAll";
	});
</script>
</body>
</html>











