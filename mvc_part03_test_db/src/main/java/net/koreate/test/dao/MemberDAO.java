package net.koreate.test.dao;

import java.util.List;

import net.koreate.test.vo.MemberVO;

public interface MemberDAO {
	// 회원 정보 삽입
	void insertMember(MemberVO vo);
	// 단일 회원 정보
	MemberVO readMember(String userid);
	// 전체 회원 목록
	List<MemberVO> readMemberList();
}







