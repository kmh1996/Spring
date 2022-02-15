<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product.jsp</title>
</head>
<body>
	<h2>${requestScope.product.name}</h2>
	<h2>${requestScope.product.price}</h2>
	<hr/>
	<h2>${productVO.name}</h2>
	<h2>${productVO.price}</h2>
</body>
</html>


