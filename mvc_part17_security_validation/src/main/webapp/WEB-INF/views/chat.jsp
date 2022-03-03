<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/chat.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal.member"/>
</sec:authorize>
<!-- 
https://github.com/sockjs/sockjs-client

javascript TCP 통신 client lib
 -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<div class="container">
	<h1>CHAT PAGE - ${member.u_name}</h1>
	<textarea id="data" class="form-control" rows="4" 
	cols="50"></textarea>
	<br/>
	<div class="row">
		<div class="col-md-10">
			<input type="text" class="form-control" 
			id="message"/>
		</div>
		<div class="col-md-2">
			<input type="button" id="sendBtn"  
				class="form-control btn btn-primary"
				value="SEND"/>
		</div>
	</div>
	<script>
		var sock = new SockJS("chatHandler");
		
		// 서버와 연결이 성사 되었을때
		sock.onopen = function(){
			console.log("연결완료");
			sock.send("${member.u_name},님이 입장하셨습니다.");
		}
	
		// 서버와 연결이 끊겼을 때
		sock.onclose = onClose;
		
		function onClose(){
			$("#data").append("연결끊김");
		}
		
		// server에서 메세지가 전달되었을때 호출할 메소드
		sock.onmessage = function(message){
			console.log(message);
			var msg = message.data;
			$("#data").append(msg+"\r\n");
			$("#data").focus();
			$("#data").scrollTop($("#data").prop("scrollHeight"));
			$("#message").focus();
		}
		
		function sendMessage(){
			var userName = "${member.u_name}";
			var message = $("#message").val();
			if(message == null || message == '' || message.length == 0){
				alert('메세지를 작성해 주세요!');	
				$("#message").focus();
				return;
			}
			var sendMessage = userName+","+message;
			sock.send(sendMessage);
			console.log(sendMessage);
			$("#message").val('');
		}
		
		$("#sendBtn").click(function(){
			sendMessage();
		});
		
		$("#message").keydown(function(key){
			console.log(key.keyCode);
			if(key.keyCode == 13){
				sendMessage();
			}
		});
		
	</script>
</div>
</body>
</html>









