package net.koreate.board.dao;

import java.util.List;

import net.koreate.board.model.Board;
import net.koreate.board.util.Criteria;

public interface BoardDAO {
	
	// 게시글 작성
	int create(Board vo) throws Exception;
	
	// 게시글 상세보기
	Board read(int bno) throws Exception;
	
	// 게시글 수정
	void update(Board vo)throws Exception;
	
	// 게시글 삭제
	void delete(int bno) throws Exception;
	
	// 전체 게시글 보기
	List<Board> listAll() throws Exception;
	
	// 조회수 증가
	public void updateCnt(int bno) throws Exception;
	
	// 전체 게시물 개수
	int listCount()throws Exception;
	
	// 페이징 처리된 게시물 목록
	List<Board> listCriteria(Criteria cri) throws Exception;
}













