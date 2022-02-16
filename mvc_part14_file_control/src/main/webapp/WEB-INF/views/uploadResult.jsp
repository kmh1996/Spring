<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadResult.jsp</title>
</head>
<body>
	<h2>Upload Result</h2>
	<c:if test="${!empty savedName}">
		<h2>savedName : ${savedName}</h2>
	</c:if>
	
	<h3>${auth}</h3>
	<h3>${content}</h3>
	
	<h3>saves</h3>
	<c:forEach var="f" items="${saves}">
		<h4>saves : ${f}</h4>
	</c:forEach>
</body>
</html>






