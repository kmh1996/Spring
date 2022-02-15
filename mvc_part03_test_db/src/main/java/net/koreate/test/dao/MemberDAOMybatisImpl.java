package net.koreate.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.test.vo.MemberVO;

@Repository("myBatisMember")
public class MemberDAOMybatisImpl 
				implements MemberDAO,MemberDAOMultiParam{
	
	private String namespace = "memberMapper";
	
	@Inject
	SqlSession session;

	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("MemberDAOMybatisImpl insert member");
		System.out.println(vo);
		//session.insert("memberMapper.insertMember",vo);
		session.insert(namespace+".insertMember",vo);
	}

	@Override
	public MemberVO readMember(String userid) {
		MemberVO vo = session.selectOne(namespace+".readMember",userid);
		return vo;
	}

	@Override
	public List<MemberVO> readMemberList() {
		List<MemberVO> memberList 
			= session.selectList(namespace+".list");
		return memberList;
	}

	@Override
	public MemberVO readMemberWithPass(String userid, String userpass) {
		Map<String, String> memberInfo = new HashMap<>();
		memberInfo.put("id",userid);
		memberInfo.put("pw",userpass);
		MemberVO member 
			= session.selectOne(
					namespace+".readMemberPass"
					,memberInfo);
		return member;
	}

}











