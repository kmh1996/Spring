<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>readPage.jsp</title>
</head>
<body>
	<h3>READ BOARD PAGE</h3>
	<div>
		<label>Title</label>
		<input type="text" name="title"
			   required value="${board.title}" readonly/>
	</div>
	<div>
		<label>Writer</label>
		<input type="text" name="writer"
			   required value="${board.writer}" readonly/>
	</div>
	<div>
		<label>Content</label>
		<textarea name="content"
			   required readonly rows=5>${board.content}</textarea>
	</div>
	<div>
		<input type="button" id="modify" value="MODIFY" />
		<input type="button" id="delete" value="DELETE" />
		<input type="button" id="list" value="LIST" />
	</div>
	<!-- cri ${cri} -->
	<form id="readForm" method="GET">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="hidden" name="page" value="${cri.page}"/>
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var formObj = $("#readForm");

	$("#list").click(function(){
		formObj.attr("action","listPage");
		formObj.submit();
		//location.href="listPage";
	});
	
	$("#modify").click(function (){
		formObj.attr("action","modifyPage");
		formObj.submit();
	});
	
	$("#delete").click(function(){
		formObj.attr("action","removePage");
		formObj.attr("method","POST");
		formObj.submit();
	});
</script>
</body>
</html>








