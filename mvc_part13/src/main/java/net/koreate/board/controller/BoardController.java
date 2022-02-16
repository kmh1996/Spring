package net.koreate.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.board.service.BoardService;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.Criteria;
import net.koreate.common.utils.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService bs;
	
	@GetMapping("register")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("register")
	public String register(BoardVO vo)throws Exception{
		System.out.println("regist : "+vo);
		// 게시글(원본) 등록
		bs.regist(vo);
		// 게시글 목록 페이지로 요청 변경
		return "redirect:/board/listReply";
	}
	
	// 페이징 처리된 게시글 목록
	@GetMapping("listReply")
	public String listReply(
			Criteria cri,
			Model model
			)throws Exception{
		System.out.println(cri);
		
		model.addAttribute("list",bs.listReply(cri));
		PageMaker pm = bs.getPageMaker(cri);
		model.addAttribute("pm",pm);
		return "board/listReply";
	}
	
	@GetMapping("readPage")
	public String readPage(
			Criteria cri,
			int bno,
			RedirectAttributes rttr
			) throws Exception{
		// 조회수 만 증가
		bs.updateCnt(bno);
		
		rttr.addAttribute("bno",bno);
		rttr.addFlashAttribute("cri",cri);
		return "redirect:/board/read";
		//return "redirect:/board/read?bno="+bno;
	}
	
	@GetMapping("read")
	public String read(
			int bno,
			Model model
			)throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute("board",vo);
		return "board/readPage";
	}
	
	// 답변글 작성 페이지 요청
	@GetMapping("replyRegister")
	public String replyRegister(
			@ModelAttribute("cri") Criteria cri,
			int bno ,// 원본글 번호
			Model model)throws Exception{
		BoardVO origin = bs.read(bno);
		model.addAttribute("original",origin);
		return "board/replyRegister";
	}
	
	@PostMapping("replyRegister")
	public String replyRegister(
				Criteria cri,
				BoardVO vo,
				RedirectAttributes rttr
			)throws Exception{
		// 답변글 등록
		bs.replyRegister(vo);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/listReply";
	}
	
	@GetMapping("modifyPage")
	public String modifyPage(
				int bno, 		// 수정할 게시글 번호
				@ModelAttribute("cri") Criteria cri, 	//수정완료 후 이동할 페이지 정보
				Model model
			)throws Exception{
		BoardVO vo = bs.read(bno);
		model.addAttribute("board",vo);
		// model.addAttribute("board",bs.read(bno));
		return "board/modifyPage";
	}
	
	// 수정 완료 요청
	@PostMapping("modifyPage")
	public String modifyPage(
				BoardVO vo, // 수정할 게시글 정보
				Criteria cri, // 수정완료 후 이동할 페이지 정보
				RedirectAttributes rttr
			) throws Exception{
		// 게시글 수정 처리
		bs.modify(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addFlashAttribute("cri",cri);
		return "redirect:/board/read";
	}
	
	// 게시글 삭제 요청 처리
	@PostMapping("remove")
	public String remove(
			int bno,	// 삭제해야할 게시글 번호
			Criteria cri,
			RedirectAttributes rttr)throws Exception{
		
		// 삭제 처리
		bs.remove(bno);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/board/listReply";
	}
	
	
}

















