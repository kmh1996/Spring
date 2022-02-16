<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<h3>jackson databind -test</h3>
	<form action="test" method="post" enctype="application/x-www-form-urlencoded">
		<input type="text" name="test" 
value='{"cno":1,"bno":1,"commentText":"Hello!","commentAuth":"Choi"}'/>
		<input type="submit" value="확인"/>
	</form>
	<br/> <hr/>
	<button id="btn">JSON TEST</button>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$("#btn").click(function(){
	$.ajax({
		type : "POST",
		url : "test",
		headers : {
			"Content-Type" : "application/json"
		},
		data : JSON.stringify({
			"cno":1,
			"bno":1,
			"commentText":"Hello!",
			"commentAuth":"Choi"
		}),
		dataType : "text"
		}).done(function(result){
			alert(result);
	});
});
</script>	
</body>
</html>






