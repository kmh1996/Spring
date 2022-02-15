<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listPage.jsp</title>
</head>
<body>
	<h3>BOARD LIST PAGE</h3>
	<button onclick="location.href='register';">NEW BOARD</button>
	<h3>LIST</h3>
	<table border=1>
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bno}</td>
				<td>
					<a href="read?bno=${board.bno}">${board.title}</a>
				</td>
				<td>${board.writer}</td>
				<td>
					<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/>
				</td>
				<td>${board.viewcnt}</td>
			</tr>
		</c:forEach>
		<tr>
			<!-- pm -->
			<th colspan="5">
				<c:if test="${pm.first}">
					<a href="listPage?page=1">[처음]</a>
				</c:if>
				<c:if test="${pm.prev}">
					<a href="listPage?page=${pm.startPage-1}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
					<a href="listPage?page=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${pm.next}">
					<a href="listPage?page=${pm.endPage+1}">[다음]</a>
				</c:if>
				<c:if test="${pm.last}">
					<a href="listPage?page=${pm.maxPage}">[마지막]</a>
				</c:if>
			</th>
		</tr>
	</table>
	<script>
		var result = '${result}';
		if(result != null && result != ''){
			alert(result);
		}
	</script>
</body>
</html>














