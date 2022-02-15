package net.koreate.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.board.model.Board;
import net.koreate.board.util.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	public String namespace = "net.koreate.board.dao.BoardDAO";

	@Inject
	SqlSession session;
	
	@Override
	public int create(Board vo) throws Exception {
		int result = session.insert(namespace+".create",vo);
		System.out.println("result : " + result);
		return result;
	}

	@Override
	public Board read(int bno) throws Exception {
		return session.selectOne(namespace+".read",bno);
	}

	@Override
	public void update(Board vo) throws Exception {
		int result = session.update(
			namespace+".update",vo
		);
		System.out.println("update result : " + result);
	}

	@Override
	public void delete(int bno) throws Exception {
		session.delete(namespace+".delete",bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		session.update(namespace+".updateCnt",bno);
	}

	@Override
	public int listCount() throws Exception {
		return session.selectOne(namespace+".listCount");
	}

	@Override
	public List<Board> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace+".listCriteria",cri);
	}
}





