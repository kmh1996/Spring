package net.koreate.comment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.comment.dao.CommentDAO;
import net.koreate.comment.vo.CommentVO;
import net.koreate.mvc.common.util.Criteria;
import net.koreate.mvc.common.util.PageMaker;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	CommentDAO dao;

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return dao.commentList(bno);
	}

	@Override
	public String addComment(CommentVO vo) throws Exception {
		int result = dao.add(vo);
		return result > 0 ? "SUCCESS" : "FAILED";
	}

	@Override
	public String updateComment(CommentVO vo) throws Exception {
		return dao.update(vo) > 0 ? "SUCCESS" : "FAILED";
	}

	@Override
	public String deleteComment(int cno) throws Exception {
		return dao.delete(cno) > 0 ? "SUCCESS" : "FAILED";
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int bno) throws Exception {
		int totalCount = dao.totalCount(bno);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}

}
