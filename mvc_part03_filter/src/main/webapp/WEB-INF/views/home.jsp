<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" 
session="true" 
contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8">
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

${sessionScope.member} <Br/>
<a href="write">write</a>

</body>
</html>





