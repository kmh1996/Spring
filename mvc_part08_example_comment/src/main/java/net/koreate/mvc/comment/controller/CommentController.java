package net.koreate.mvc.comment.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.mvc.comment.service.CommentService;
import net.koreate.mvc.comment.vo.CommentVO;
import net.koreate.mvc.common.util.Criteria;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	
	@Inject
	CommentService cs;
	
	@PostMapping("/addComment")
	public String addComment(
			int bno,
			CommentVO vo,
			RedirectAttributes rttr)throws Exception{
		// 삽입 성공 여부 전달
		System.out.println(vo);
		System.out.println("bno : "+bno);
		
		String msg = cs.addComment(vo);
		
		rttr.addFlashAttribute("result",msg);
		rttr.addAttribute("bno",bno);
		return "redirect:/comment/commentList";
		//return "redirect:/comment/commentList?bno="+bno;
	}
	
	@GetMapping("/commentList")
	public String commentList(
			int bno, Criteria cri,
			Model model
			)throws Exception{
		// 댓글 목록 및 페이지 블럭 정보
		/*
		List<CommentVO> list = cs.commentListPage(bno, cri);
		model.addAttribute("list",list);
		*/
		model.addAttribute("list",cs.commentListPage(bno, cri));
		/*
		int page = cri.getPage();
		PageMaker pm = cs.getPageMaker(page, bno);
		model.addAttribute("pm",pm);
		*/
		model.addAttribute("pm",cs.getPageMaker(cri.getPage(), bno));
		return "/comment/commentList";
	}
	
	@PostMapping("/commentDelete")
	public String commentDelete(int bno, int  cno,
			RedirectAttributes rttr)throws Exception{
		// 댓글 삭제
		// 삭제 성공 여부 전달
		String msg = cs.delete(cno);
		rttr.addFlashAttribute("result",msg);
		rttr.addAttribute("bno",bno);
		return "redirect:/comment/commentList";
	}
	
}






