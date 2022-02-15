package net.koreate.board;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.board.dao.BoardDAO;
import net.koreate.board.model.Board;
import net.koreate.board.util.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"classpath:/context/root/root-context.xml"
})
public class MysqlTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void test1() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver 확인 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(conn+" 연결 완료");
		} catch (SQLException e) {
			System.out.println("ds 연결 실패");
		}
	}
	
	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test3() {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("연결 정보 객체 생성 완료 : " + session);
		System.out.println("conn : " + session.getConnection());
		session = new SqlSessionTemplate(sqlSessionFactory);
		System.out.println("연결 정보 객체 생성 완료 : " + session);
		System.out.println("conn : " + session.getConnection());
	}

	@Inject
	SqlSession session;
	
	@Test
	public void test4() {
		System.out.println("session : " + session);
		System.out.println("session conn : " + session.getConnection());
	}
	
	@Inject
	BoardDAO dao;
	
	@Test
	public void test6() throws Exception{
		Board board = new Board();
		board.setBno(1);
		board.setTitle("수정 게시물");
		board.setContent("수정내용");
		dao.update(board);
		
		System.out.println(dao.listCount());
		System.out.println(dao.listAll());
		dao.updateCnt(1);
		Criteria cri = new Criteria(1,10);
		System.out.println(dao.listCriteria(cri));
		
		
	}
	
	//@Test
	public void test5() throws Exception{
		System.out.println(dao.getClass().getName());
		Board board = new Board();
		board.setTitle("두번째 게시물");
		board.setContent("냉무");
		board.setWriter("최기근");
		int result = dao.create(board);
		System.out.println("test : " + result);
	}
	
	
}











