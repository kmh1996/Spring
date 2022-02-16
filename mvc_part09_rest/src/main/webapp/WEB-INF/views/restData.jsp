<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>restData.jsp</title>
</head>
<body>
	<!-- GET POST PUT PATCH DELETE -->
	<h1>getSample Get</h1>
	<form action="getSample" method="GET"
		  enctype="application/x-www-form-urlencoded">
		<input type="text" name="name" /> <br/>	
		<input type="number" name="age" /> <br/>
		<input type="submit" value="getSample GET"/>
	</form>
	<hr/>
	<h1>getSample POST</h1>
	<form action="getSample" method="POST" >
		<input type="text" name="name" /> <br/>	
		<input type="number" name="age" /> <br/>
		<input type="submit" value="getSample POST"/>
	</form>	
	
	<hr/>
	<h1>getSample PUT</h1>
	<form action="getSample" method="POST" >
		<input type="hidden" name="_method" value="PUT"/>
		<input type="text" name="name" /> <br/>	
		<input type="number" name="age" /> <br/>
		<input type="submit" value="getSample PUT"/>
	</form>	
	
	<hr/>
	<h1>getSample PATCH</h1>
	<form action="getSample" method="POST" >
		<input type="hidden" name="_method" value="PATCH"/>
		<input type="text" name="name" /> <br/>	
		<input type="number" name="age" /> <br/>
		<input type="submit" value="getSample PATCH"/>
	</form>	
	
	<hr/>
	<h1>getSample DELETE</h1>
	<form action="getSample" method="POST" >
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="text" name="name" /> <br/>	
		<input type="number" name="age" /> <br/>
		<input type="submit" value="getSample DELETE"/>
	</form>	
	
	
</body>
</html>







