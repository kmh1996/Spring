package net.koreate.sec.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.sec.dao.MemberDAO;
import net.koreate.sec.vo.AuthVO;
import net.koreate.sec.vo.ValidationMemberVO;

@Service
public class MemberServiceImpl 
					implements MemberService {

	@Inject
	MemberDAO dao;
	
	@Inject
	PasswordEncoder encoder;
	
	@Transactional
	@Override
	public void memberJoin(ValidationMemberVO vo) throws Exception {
		String u_pw = vo.getU_pw();
		System.out.println("암호화 전 : " + u_pw);
		vo.setU_pw(encoder.encode(u_pw));
		System.out.println("암호화 후 : " + vo.getU_pw());
		dao.memberJoin(vo);
		dao.insertAuth(vo.getU_id());
	}

	@Override
	public boolean getMemberByID(String u_id) throws Exception {
		ValidationMemberVO vo = dao.getMemberByID(u_id);
		System.out.println(vo);
		return vo == null ? true : false;
	}

	@Override
	public void updateVisteDate(String u_id) throws Exception {
		// 로그인 시 최종 방문 시간을 현재시간으로 수정
		dao.updateVistDate(u_id);
	}

	@Override
	public void deleteYN(ValidationMemberVO vo) throws Exception {
		dao.deleteYN(vo);
	}

	@Override
	public List<AuthVO> updateAuth(AuthVO vo) throws Exception {
		List<AuthVO> list = dao.getAuthList(vo.getU_id());
		boolean isNull = true;
		for(AuthVO auth : list) {
			if(vo.getU_auth().equals(auth.getU_auth())) {
				// 해당 사용자의 권한 삭제
				dao.deleteAuth(auth);
				isNull = false;
				break;
			}
		}
		
		// 권한이 존재하지 않을때 권한 추가
		if(isNull) dao.insertMemberAuth(vo);
		
		// 변경된 권한 리스트 반환
		return dao.getAuthList(vo.getU_id());
	}

	@Override
	public List<ValidationMemberVO> getMemberList() throws Exception {
		return dao.getMemberList();
	}

}




