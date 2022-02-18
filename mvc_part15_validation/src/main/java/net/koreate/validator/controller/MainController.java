package net.koreate.validator.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.koreate.validator.utils.Coolsms;
import net.koreate.validator.vo.ValidationMemberVO;

@Controller
public class MainController {
	
	@Inject
	JavaMailSender mailSender;

	@GetMapping("/")
	public String main() {
		return "home";
	}
	
	@GetMapping("regx")
	public void regx() {}
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "user/login";
	}
	
	// 아이디 중복 체크
	@PostMapping("/user/uidCheck")
	@ResponseBody
	public boolean isCheck(String u_id) {
		boolean isCheck = false;
		System.out.println("u_id : " +u_id );
		if(u_id != null && !u_id.equals("chlrlrms1@gmail.com")) {
			// 등록 되지 않은 이메일 아이디
			isCheck = true;
		}
		System.out.println("isCheck :" + isCheck);
		// 이미 등록된 이메일 아이디
		return isCheck;
	}
	
	@GetMapping("/checkEmail")
	@ResponseBody
	public String sendMail(
			@RequestParam("u_id") String email)throws Exception{
		System.out.println(email);
		String code = "";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper
			= new MimeMessageHelper(message,"UTF-8");
		messageHelper.setFrom("chlrlrms1@gmail.com");
		messageHelper.setTo(email);
		messageHelper.setSubject("이메일 인증 코드 확인");
		for(int i=0; i<5; i++) {
			// 0 ~ 0.99999
			code += (int)(Math.random()*10);
		}
		System.out.println(code);
		String msg = "다음 인증 코드를 입력해주세요.<h3>"+code+"</h3>";
		messageHelper.setText(msg,true);
		mailSender.send(message);
		System.out.println("발신완료");
		return code;
	}
	
	// 전화번호 인증 문자 메세지 전송
	@PostMapping("/sendSMS")
	@ResponseBody
	public Map<String,String> sendSMS(
				String userPhoneNumber
			) throws Exception{
		System.out.println(userPhoneNumber);
		
		String api_key ="NCS1T70FDEJULAZH";
		String api_secret = "G7NT7IUL0XPAM8QINSZVNZ72QTBSVBFP";
		
		Coolsms coolsms = new Coolsms(api_key,api_secret);
		HashMap<String,String> set = new HashMap<>();
		// test : to 01000000000
		set.put("to", userPhoneNumber);
//		set.put("to", "01000000000");
//		set.put("mode","test");
		set.put("from", "01094867166");	// 발신번호
		set.put("refname", "최기근");
		// code 생성
		String code = "";
		for(int i=0; i<5; i++) {
			// 0 ~ 0.99999
			code += (int)(Math.random()*10);
		}
		set.put("text", "쉬면석이 전송한 문자메세지["+code+"] 확인해주세요!");	// 문자내용
		set.put("type", "sms");
		JSONObject result = coolsms.send(set);
		boolean resultStatus = (boolean)result.get("status");
		if(resultStatus) {
			// 메세지 보내기 성공
			System.out.println("성공");
			System.out.println("결과 코드 : " + result.get("result_code"));
			System.out.println("결과 메세지 : " + result.get("result_message"));
			System.out.println("결과 메세지 개수: " + result.get("success_count"));
			System.out.println(result);
		}else {
			// 메세지 보내기 실패
			System.out.println("보내기 실패");
			System.out.println(result);
		}
		Map<String,String> map = new HashMap<>();
		map.put("code", code);
		map.put("result", String.valueOf(result.get("result_code")));
		
		return map;
	}
	
	@Inject
	ServletContext context;
	
	@PostMapping("/user/joinPost")
	public String joinPost(
			ValidationMemberVO vo,
			MultipartFile profileImage
			)throws Exception{
		// 전달 받은 대용량 데이터가 존재 하는지 확인
		System.out.println(profileImage.isEmpty());
		System.out.println(profileImage.getOriginalFilename());
		System.out.println(profileImage.getContentType());
		System.out.println(profileImage.getName()); // 입력태그의 네임
		System.out.println(vo);
		
		if(!profileImage.isEmpty()) {
			// src/main/webapp/upload/profile/u_id
			String path = "upload"+File.separator
					+"profile"+File.separator
					+vo.getU_id();
			String realPath = context.getRealPath(path);
			File file = new File(
					realPath,
					profileImage.getOriginalFilename());
			if(!file.exists()) {
				file.mkdirs();
			}
			profileImage.transferTo(file);
			System.out.println(file.getPath());
			System.out.println(file.getCanonicalPath());
			System.out.println(file.getAbsolutePath());
		}
		
		return "home";
	}
	
}








