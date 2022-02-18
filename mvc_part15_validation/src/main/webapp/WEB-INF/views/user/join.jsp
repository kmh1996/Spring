<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<!--
	 프로필 이미지, 
	 이메일(아이디), 
	 이름(2-6자 이내),
	 닉네임(2-6자 이내), 
	 비밀번호 ,
	 전화번호(-제외 숫자만)
	 생년월일(19820207),
	 주소(API), 
	 개인정보 이용 동의, 
 -->
 <style>
 	table{
 		max-width:500px;
 		margin:0 auto;
 	}
 	
 	.uploadImage{
 		width:100px;
 		height:100px;
 		border-radius:50px;
 		border:1px solid #ccc;
 	}
 	
 	#codeWrap, #acceptEmail, #emailCodeWrap{
 		display:none;
 	}
 
 </style>
<form id="joinForm" 
	action="${path}/user/joinPost" method="POST" 
	enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th colspan="2"><h1>JOIN PAGE</h1></th>
		</tr>
		<tr>
			<td>프로필 이미지</td>
			<td style="text-align:center">
				<img src="${path}/resources/img/profile.jpg" id="uploadImage" class="uploadImage"/>
				<br/>
				<input type="file" id="profileImage" name="profileImage" accept="image/*"/>
				<input type="button" id="removeProfile" value="지우기"/>
			</td>
		</tr>
		<tr>
			<td>아이디(email)</td>
			<td>
				<input type="text" name="u_id" id="u_id" autocomplete="off"/>
				<button type="button" id="acceptEmail">이메일 인증</button>
				<div class="result"></div>
				<div id="emailCodeWrap">
					<input type="text" id="emailCode" />
					<button type="button" id="emailAcceptBtn">인증완료</button>
				</div>
			</td>
		</tr>
		<!-- 비밀번호  -->
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="u_pw" id="u_pw"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" id="u_repw"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>이름(한글 2~6자이내)</td>
			<td>
				<input type="text" name="u_name" id="u_name"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>생년월일(ex-19900505)</td>
			<td>
				<input type="text" name="u_birth" id="u_birth"/>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<div>
					<input type="text" name="u_post" class="addr"
						   id="u_post" placeholder="우편번호"/>
					<input type="button" value="주소찾기" onclick="daumPostCode();"/>
				</div>
				<input type="text" name="u_addr" class="addr"
					   id="u_addr" placeholder="주소" /> <br/>
				<input type="text" name="u_addr_detail" class="addr"
					   id="u_addr_detail" placeholder="상세주소" /> 
			</td>
		</tr>
		<tr>
			<td>전화번호(-제외 숫자만 입력)</td>
			<td>
				<div id="phoneWrap">
					<input type="text" name="u_phone" id="u_phone" />
					<input type="button" id="sendSMS" value="인증코드 전송"/>
					<div class="result"></div>
				</div>
				<div id="codeWrap">
					<input type="text" id="code" />
					<input type="button" id="acceptCode" value="인증"/>
				</div>
			</td>
		</tr>
		<!-- 
		개인정보보호 포털
		https://www.privacy.go.kr
		 -->
		<tr>
			<td colspan="2">
				<h4>이용약관</h4>
				<textarea readonly cols="50" rows="5">
				당신의 개인정보는 언제든지 회사에서 팔아먹을 수 있으며.. 3자에게 제공됩니다.
				10원에 중국에 이미 팔렸을 수도 있습니다.
				</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>
				<input type="checkbox" name="u_info" id="u_info" value="y"/>
				개인정보 이용동의</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" id="joinBtn" value="회원가입"/>
			</td>
		</tr>
	</table>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function daumPostCode(){
    	new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                console.log(data);
                
                var fullAddr = "";	// 최종 주소
                var extraAddr = ""; // 조합형 주소
                
                // 사용자가 선택한 타입이 지번인지 도로명 주소인지 확인
                if(data.userSelectedType == "R"){
                	console.log("도로명 주소");
                	fullAddr = data.roadAddress;
                }else{
                	console.log("지번 주소");
                	fullAddr = data.jibunAddress;
                }
                
                if(data.userSelectedType == "R"){
                	// 법정 동명
                	if(data.bname !== ''){
                		extraAddr += data.bname; 
                	}
                	
                	// 건물명 
                	if(data.buildingName !== ''){
                		extraAddr += extraAddr !== '' ? ', '+data.buildingName : data.buildingName;
                	}
                	
                	fullAddr += (extraAddr !== '') ? '('+extraAddr+')' : '';
                }
                // 입력필드에 값 추가
                $("#u_post").val(data.zonecode);
                $("#u_addr").val(fullAddr);
                $("#u_addr_detail").focus();
            }
        }).open();
    }
</script>

<script>
	var imgTemp = $("#uploadImage").attr("src");
	
	console.log(imgTemp);
	
	$("#profileImage").on("change",function(){
		var files = $(this)[0].files[0];
		console.log(files.type);
		if(!files.type.startsWith("image/")){
			console.log("이미지 아님");
			alert('이미지를 선택해주세요.');
			removeImage();
		}else{
			console.log("이미지 맞음");
			var path = window.URL.createObjectURL(files);
			$("#uploadImage").attr("src",path);
		}
	});
	
	$("#removeProfile").click(function(){
		removeImage();
	});
	
	function removeImage(){
		$("#profileImage").val("");
		$("#uploadImage").attr("src",imgTemp);
	}
	
	$("#u_id").focus();
	
	var boolUid = false;
	
	// 이메일 확인 정규식	
	var regexEmail =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;       // 이메일
	
	//(메세지를 보여줄 요소,검사할 값,비교할 정규식,출력할 메세지,비동기 통신 함수)
	function checkRegex(elP,valP,regexP,messageP,ajaxP){
		if(regexP.test(valP) === false){
			showMessage(elP,messageP,false);
			return false;
		}else if(regexP.test(valP) !== false
				&& ajaxP === null){
			showMessage(elP,"사용가능합니다.",true);
			return true;
		}else{
			if(ajaxP !== null){
				ajaxP(elP);
			}
		}
	}
	// 메세지를 보여줄 요소, 메세지, 성공 실패 여부
	function showMessage(elP,messageP, isCheck){
		var html ="<span style='margin-left:5px;font-size:12px;";
		html += isCheck ? "color:green;" : "color:red;";
		html +="'>";
		html +=messageP;
		html +="</span>";
		$(elP).html(html);
	}
	// 입력태그에 값이 작성이 될때
	$("#u_id").on("input",function(){
		boolEmailCode = false;
		var tempVal = $(this).val();
		console.log(tempVal);
		var elP = $(this).parent().find(".result");
		var message = "올바른 이메일 형식이 아닙니다.";
		boolUid = checkRegex(elP,tempVal,regexEmail,message,checkUidAjax);
	});
	
	// 아이디 중복 체크
	function checkUidAjax(elP){
		$.ajax({
			type : "POST",
			url : "${path}/user/uidCheck",
			data : {
				u_id : $("#u_id").val()
			},
			dataType : "json",
			success : function(result){
				console.log(result);
				boolUid = result;
				showMessage(
						elP,
					 boolUid ? "사용가능한 아이디입니다." : "이미 존재하는 아이디입니다.",
					 boolUid		 
				);
				if(boolUid){
					// 사용가능 이메일 인증 코드 발송 버튼 활성화
					$("#acceptEmail").show();
				}else{
					$("#acceptEmail").hide();
				}
			}
		});
	}
	
	// 인증 메일에 발송된 코드 저장
	var emailCode = "";
	
	// 인증 메일 발송
	$("#acceptEmail").click(function(){
		$.ajax({
			type : "GET",
			dataType : "text",
			url : "${path}/checkEmail",
			data : {
				u_id : $("#u_id").val()
			},
			success : function(code){
				console.log(code);
				if(code){
					emailCode = code;
					alert("메일 발송 완료!\n메일을 확인해주세요.");
					$("#emailCodeWrap").show();
				}else{
					alert("메일 전송 실패 관리자에게 문의해 주세요.");
				}
			}
		});
	});
	
	// 인증 코드 확인 완료 여부
	var boolEmailCode = false;
	
	// 인증 코드 검사
	$("#emailAcceptBtn").click(function(){
		var userCode = $("#emailCode").val();
		if(emailCode == userCode){
			console.log("일치함");
			boolEmailCode = true;
			alert("이메일 인증이 완료되었습니다.");
			$("#emailCodeWrap").hide();
		}else{
			console.log("일치하지 않음");
			boolEmailCode = false;
			alert("인증 코드를 다시 확인해 주세요.");
		}
	});
	
	// 비밀번호 확인
	var boolPassword = false;
	
	// 특수문자 포함 비밀번호
	var regexPass = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
	// 비밀번호 형식 확인
	$("#u_pw").on("input",function(){
		var tempVal = $(this).val();
		var elp = $(this).parent().find(".result");
		var message = "특수문자 포함 영문/숫자 조합하여 8~16자이내 작성";
		boolPassword = checkRegex(elp,tempVal,regexPass,message,null);
	});
	
	// 비밀번호 동일 값 체크 여부
	var boolPassCheck = false;
	
	// 비밀번호 확인
	$("#u_repw").on("input",function(){
		var tempVal = $(this).val();
		var message = "";
		var originalVal = $("#u_pw").val();
		var elP = $(this).parent().find(".result");
		if(boolPassword){
			
			if(tempVal == originalVal){
				boolPassCheck = true;
				message ="비밀번호가 일치합니다.";
			}else{
				boolPassCheck = false;
				message ="비밀번호가 일치 하지않습니다.";
			}
			
		}else{
			boolPassCheck = false;
			message = "비밀번호를 먼저 확인해 주세요.";
		}
		showMessage(elP,message,boolPassCheck);
	});
	
	// 이름 확인 여부
	var boolName = false;
	
	// 한글 2~6글자 이내
	var regexName = /^[\uac00-\ud7a3]{2,6}$/;							
	
	// 사용자 이름 확인
	$("#u_name").on("input",function(){
		var tempVal = $(this).val();
		var elP = $(this).find("+ .result");
		var message = "2~6글자 이내 한글로 작성해 주세요.";
		boolName = checkRegex(elP,tempVal,regexName,message,null);
	});
	
	// 생년월일 확인 yyyyMMdd
	var boolBirth = false;
	
	// 생년월일 정규식 19900505
	var regexBirth = /^[0-9]{4}[0-9]{2}[0-9]{2}$/;							
	
	$("#u_birth").on("input",function(){
		var tempVal = $(this).val();
		var message ="19900505 형식으로 작성해주세요.";
		var elP = $(this).find("+ .result");
		boolBirth = checkRegex(elP,tempVal,regexBirth,message,null);
	});
	
	// 전화번호 작성 및 인증
	var boolPhone = false;
	
	// mobile -표시 없이 숫자만
	var regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;						
	
	$("#u_phone").on("input",function(){
		var tempVal = $(this).val();
		var elP = $(this).parent().find(".result");
		var message = "- 제외 숫자만 입력해주세요.";
		boolPhone = checkRegex(elP,tempVal,regexMobile,message,null);
		$("#sendSMS").attr("disabled",false);
		$("#codeWrap").slideUp();
	});
	
	// 발송된 인증코드 저장
	var code = "";
	// 인증 완료 여부 확인
	var boolSMS = false;
	
	$("#sendSMS").click(function(){
		
		if(!boolPhone){
			alert("전화번호를 먼저 확인해 주세요.");
			$("#u_phone").focus();
		}else{
			$("#sendSMS").attr("disabled",true);
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${path}/sendSMS",
				data : {
					userPhoneNumber : $("#u_phone").val()
				},
				success: function(data){
					// data - 성공 여부 문자열
					//      - 발송된 code
					if(data["result"] == "00"){
						$("#codeWrap").slideDown();
						code = data['code'];
					}
					console.log(code);
					console.log(data);
				}
			});
		}
	});
	
	// 전화 인증 코드 확인
	$("#acceptCode").click(function(){
		var userCode = $("#code").val();
		if(code != "" && userCode.length != 0 && 
				code == userCode){
			alert("인증 완료");
			boolSMS = true;
			$("#codeWrap").slideUp();
			$("#code").val("");
		}else{
			alert("발송된 인증코드를 확인해주세요.");
			$("#code").focus();
		}
	});
	
	// submit event 실행 전
	// 작성 내용 검증 확인
	$("#joinBtn").click(function(){
		
		// 주소 입력 태그 공백 확인
		var addObj = null;
		var boolAddress = false;
		$(".addr").each(function(){
			var val = $(this).val();
			console.log(val);
			
			boolAddress = (val !== '') ? true : false;
			if(!boolAddress){
				addrObj = $(this);
				// 반복문 탈출
				return false;
			}
			var hint = $(this).attr("placeholder");
			console.log(hint);
		});
		
		// 확인 후 주석
		// $("#joinForm").submit();
		
		// email 검증 미 실행
		if(!boolUid){
			alert("아이디를 확인해 주세요.");
			$("#u_id").focus();
		}else if(!boolEmailCode){
			alert("이메일 인증을 완료해 주세요.");
			$("#emailCode").focus();
		}else if(!boolPassword){
			alert("비밀번호를 확인해주세요.");
			$("#u_pw").focus();
		}else if(!boolPassCheck){
			alert("비밀번호 일치 여부를 확인해주세요.");
			$("#u_repw").focus();
		}else if(!boolName){
			alert("이름을 확인해 주세요.");
			$("#u_name").focus();
		}else if(!boolBirth){
			alert("생년월일 확인해 주세요.");
			$("#u_birth").focus();
		}else if(!boolAddress){
			alert($(addrObj).attr("placeholder")+"을 확인해 주세요.");
			$(addrObj).focus();
		}else if(!boolPhone){
			alert("전화번호를 확인해 주세요.");
			$("#u_phone").focus();
		}else if(!boolSMS){
			alert("전화번호 인증을 완료해 주세요.");
			$("#code").focus();
			// u_info checkbox 체크 여부 확인
		}else if(!$("#u_info").is(":checked")){
			alert("개인정보 이용동의를 확인해주세요.");
			$("#u_info").focus();
		}else{
			$("#joinForm").submit();
		}
	});
	
</script>
</body>
</html>











