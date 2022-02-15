<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="WEB-INF/view/common/header.jsp"/>
	<h1>INDEX PAGE</h1>
	<a href="doA">doA</a> <br/>
	<a href="doB">doB</a> <br/>
	<a href="doC">doC</a> <br/>
	<a href="doD?msg=hi">doD GET</a> <br/>
	<form action="doD" method="POST">
		<input type="text" name="msg"/>
		<input type="number" name="age" required/>
		<input type="submit" value="doD POST"/>
	</form>
	<br/>
	<hr/>
	<a href="product">product</a> <br/>
	<a href="doH">doH</a> <br/> <br/>
	<form action="productWrite" method="POST">
		<input type="text" name="name" /> <br/>
		<input type="number" name="price" required /> <br/>
		<input type="submit" value="전송"/>
	</form> 
	<br/>
	<hr/>
	<a href="main.do">MAIN</a> <br/>
<jsp:include page="WEB-INF/view/common/footer.jsp"/>	
</body>
</html>







