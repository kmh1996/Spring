package net.koreate.validator;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:/spring/**/*-context.xml"
})
public class SendMailTest {

	@Autowired
	@Qualifier(value="mailSender")
	JavaMailSender mailSender;
	
	@Test
	public void sendTest()throws Exception{
		MimeMessage message = mailSender.createMimeMessage();
		/*
		// multipart 메세지를 사용하겠다는 의미
		MimeMessageHelper messageHelper
		= new MimeMessageHelper(message,true,"UTF-8");
		*/
		// multipart 메세지를 사용하지않겠다는 의미
		// 일반 텍스트 메세지
		MimeMessageHelper messageHelper
			= new MimeMessageHelper(message,"UTF-8");
		messageHelper.setFrom("chlrlrms1@gmail.com");
		messageHelper.setTo("hap0p9y@nate.com");
		messageHelper.setSubject("메일전송 테스트 제목");
		// true - html 을 사용하겠다는 의미
		messageHelper.setText("<h1>내용 텍스트</h1>",true);
		
		mailSender.send(message);
		
		System.out.println("발신 완료");
	}
	
}











