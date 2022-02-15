package net.koreate.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.koreate.board.model.Board;
import net.koreate.board.util.Criteria;
import net.koreate.board.util.PageMaker;

public interface BoardService {
	// 게시글 삽입
	String regist(Board board) throws Exception;
	
	// 게시글 상세보기
	Board read(int bno) throws Exception;
	
	// 게시글 수정
	void modify(Board board) throws Exception;
	
	// 게시글 삭제
	void remove(int bno) throws Exception;
	
	// 게시글 목록
	List<Board> listAll() throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno, HttpSession session) throws Exception;
	
	// 페이징 처리된 리스트
	List<Board> listCriteria(Criteria cri)throws Exception;
	
	// 페이징 블럭 정보
	PageMaker getPageMaker(Criteria cri) throws Exception;
}












