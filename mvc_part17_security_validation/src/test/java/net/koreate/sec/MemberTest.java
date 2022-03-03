package net.koreate.sec;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.sec.dao.MemberDAO;
import net.koreate.sec.vo.ValidationMemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations= {
			"classpath:root/root-context.xml",
			"classpath:security/security-context.xml"
	}	
)
public class MemberTest {
	
	@Inject
	MemberDAO dao;
	
	@Inject
	PasswordEncoder pwencoder;
	
	@Inject
	DataSource ds;
	
	@Test
	public void passwordEncoder()throws Exception{
		String u_pw = "!chlrlrms1";
		String encoder = pwencoder.encode(u_pw);
		System.out.println("암호화 전 : "+u_pw );
		System.out.println("암호화 후 : "+encoder);
		// 암호화 후 : $2a$10$idI709V./nnhCqnSD0b/rO1R7.wy8iRaxRY1nHEYoFuMTvEJdcCWy
		// 			$2a$10$5h/nL5ytUo.xiq6Syr61F.UxTMsKAyxJjkBf0tsa.apGGjfD95uQS
		System.out.println(pwencoder.matches("!chlrlrms",encoder));
		
		String sql = "UPDATE validation_member SET u_pw = ? WHERE u_id = ?";
		String u_id = "hap0p9y@nate.com";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, encoder);
		pstmt.setString(2, u_id);
		int result = pstmt.executeUpdate();
		System.out.println(result);
		pstmt.close();
		conn.close();
	}
	
	
	// @Test
	public void testDAO() {
		System.out.println(dao);
		ValidationMemberVO vo = new ValidationMemberVO();
		vo.setU_id("hap0p9y@nate.com");
		vo.setU_pw("!chlrlrms1");
		vo.setU_name("최기근");
		vo.setU_phone("01094867166");
		vo.setU_birth("19820207");
		vo.setU_post("58763");
		vo.setU_addr("부산광역시 동래구 충렬대로 84");
		vo.setU_addr_detail("영남빌딩 9층");
		dao.memberJoin(vo);
	}
	
}











