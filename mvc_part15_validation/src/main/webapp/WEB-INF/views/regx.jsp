<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script>
	/*
		정규식은 문자열에 포함된 문자 조합을 찾기위해 사용되는 패턴.
		RegExp exec, test 함수
		string match, replace, search, split
	*/
	
	const regex = /\d{3}-\d{3,4}-\d{4}/;
	// \d 숫자를 의미, {} 안의 숫자는 개수를 의미
	let bool = regex.test('010-1111-2222');
	console.log("bool : "+bool);
	bool = regex.test('01-11-22');
	console.log("bool : "+bool);
	
	const text = "안녕하세요 제 번호는 010-9486-7166 입니다. call me~!";
	let result = text.match(regex);
	console.log(result);
	
	/*
		Character		내용
		|				또는
		()				group
		[]				문자셋, 괄호안의 어떤 문자든
		[^]				부정 문자셋, 괄호안의 어떤 문자가 아닐때
		(?:)			해당되는 문자열을 찾지만 그룹에 포함시키지는 않음
		?				없거나 있거나(zero or one)
		*				없거나 있거나 많거나(zero or more)
		+				하나 또는 많이(one or more)
		{n}				n만큼 반복
		{min,}			최소값만 지정
		{min,max}		범위 지정 min에서 max 개수 만큼
		
		\				특수 문자가 아닌 문자
		\d				digit 숫자
		\D				숫자가 아님
		\w				word 문자
		\W				word 문자가 아님
		\s				space 공백
		\S				space 공백이 아님
		
		/\d{2,3}/gmisy
		flags
		/jpg|jpeg|png|gif/i
		g 패턴과 일치하는 모든 것들으 찾는다. g가없으면 첫번째 결과만 반환
		i 대소문자를 구분없이 검색 , A와a가 찾이가 없다.
		m 여러행의 정보를 검색
		
		https://regexr.com/6fe9v
		
		
	*/
	
</script>
</body>
</html>







