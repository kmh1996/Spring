<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<style>
	span.text-danger{
		display:block;
		color:red;
		font-size: 10px;
		padding:5px;
	}
</style>
<form id="loginForm" action="loginPost" method="POST">
	<table border="1">
		<tr>
			<th colspan="2">
				<h2>LOGIN PAGE</h2>
			</th>
		</tr>
		<tr>
			<td>아이디(email)</td>
			<td>
				<input type="text" name="u_id" id="u_id"/>
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="u_pw" id="u_pw" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>
					<input type="checkbox" name="useCookie"/>로그인 정보 저장
				</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인"/>
			</td>
		</tr>
	</table>
</form>
<script>
	// 문서가 모두 준비되었을 때
	$(function(){
		$.validator.setDefaults({
			submitHandler : function(){
				$("#loginForm").submit();
			}
		});
		
		/**
			rules 속성
			minlength : 최소 길이 체크
			maxlength : 최대 길이 체크
			rangeLenth : rangeLenth[2,6] 2글자 이상~6글자 이내
			min : 숫자의 최솟값 체크  min : 13 (13보다 작을때 false)
			max : 숫자의 최댓값 체크  max : 13 (13보다 클때 false)
			range : 숫자의 범위체크 : range[13,24] 13보다 작거나 24보다 크면 false
			email : 이메일 형식의 값인지 체크   email : true
			url : 유효한 URL 형식의 값인지 체크     url : true
			number : 숫자로 사용할 수 있는 값인지 체크  number : true
			digit : 유효한 숫자 값인지 체크 : 양의정수만 허용 소수나 음수일 경우 false
		*/
		
		// 커스텀 함수 추가
		$.validator.addMethod("regex", function(value,element,regexpr){
			return regexpr.test(value);
		});
		
		// 특수문자 포함 비밀번호
		var regexPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;				
		
		// 해당 form 이 submit 이벤트가 발생 되고 난 후에 검증 시작
		$("#loginForm").validate({
			rules : {
				u_id : {
					required : true,
					email : true,
					remote : {
						type : "post",
						url : "${path}/user/uidCheck"
					}
				},
				u_pw : {
					required : true,
					minlength : 8,
					maxlength : 16,
					regex : regexPass
				}
			},
			messages : {
				u_id : {
					required : "이메일(아이디)를 작성해 주세요.",
					email : "올바른 이메일 형식이 아닙니다.",
					remote : "이미 존재하는 ID"
				},
				u_pw : {
					required : "비밀번호를 작성해 주세요.",
					minlength : "비밀번호는 최소 8자리 이상입니다.",
					maxlength : "비밀번호는 최대 16자리만 가능합니다.",
					regex : "비밀번호 형식이 일치하지 않습니다."
				}
			},
			debug : true,	// 확인 완료 후 submit을 실행 시키지 않음,
			errorClass : 'text-danger',
			errorElement : 'span'
		});
	});
</script>
</body>
</html>





