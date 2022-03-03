<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- logOff.jsp -->
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h1>다른 사용자가 로그인 하였습니다.</h1>
	<h2>본인 외에 다른 사용자에게 계정을 알려주는 것은 위험합니다.</h2>
	<a class="btn btn-primary form-control" 
	   href="${pageContext.request.contextPath}">HOME</a>
</div>
</body>
</html>



