<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/logOut.jsp</title>
</head>
<body>
	<form method="POST" 
		action="${pageContext.request.contextPath}/logout">
		<input type="submit" value="LOGOUT" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>











