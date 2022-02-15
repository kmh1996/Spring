package net.koreate.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import net.koreate.board.dao.BoardDAO;
import net.koreate.board.model.Board;
import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO dao;
	
	@Override
	public String regist(Board board) throws Exception {
		System.out.println("regist : " + board);
		int result = dao.create(board);
		String message = (result != 0) ? "SUCCESS" : "FAILED";
		return message;
	}

	@Override
	public Board read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(Board board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public void updateCnt(int bno, HttpSession session) throws Exception {
		// session = "boardSession"+bno
		List<String> boardReadBno 
		 = (ArrayList<String>)session.getAttribute("boardReadBno");
		if(boardReadBno != null) {
			for(String s : boardReadBno) {
				System.out.println("readList : " + s);
				if(s.equals("boardSession"+bno)) {
					return;
				}
			}
		}else {
			boardReadBno = new ArrayList<>();
		}
		boardReadBno.add("boardSession"+bno);
		session.setAttribute("boardReadBno", boardReadBno);
		dao.updateCnt(bno);
		System.out.println("조회수 증가 완료");
	}

	@Override
	public List<Board> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		int totalCount = dao.listCount();
		PageMaker pm = new PageMaker(totalCount,cri);
		return pm;
	}

}
