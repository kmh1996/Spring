package net.koreate.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import net.koreate.test.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	DataSource ds;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("MemberDAO insert member");
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO tbl_member "
						+" VALUES(?,?,?,now(),now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpw());
			pstmt.setString(3, vo.getUsername());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	}

	@Override
	public MemberVO readMember(String userid) {
		return null;
	}

	@Override
	public List<MemberVO> readMemberList() {
		return null;
	}

}








