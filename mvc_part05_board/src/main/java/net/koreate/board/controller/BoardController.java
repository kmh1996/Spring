package net.koreate.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.board.model.Board;
import net.koreate.board.service.BoardService;
import net.koreate.board.util.Criteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	//@RequestMapping(value="register",method=RequestMethod.GET)
	@GetMapping("register")
	public void register() throws Exception{
		System.out.println("게시글 작성 페이지 요청");
	}
	
	@PostMapping("register")
	public String register(Board board,
			RedirectAttributes rttr
			//, HttpSession session
			)throws Exception{
		System.out.println("게시글 등록 요청");
		String result = service.regist(board);
		rttr.addFlashAttribute("result",result);
		rttr.addAttribute("test","test");
		//session.setAttribute("result", result);
		//return "/board/listAll";
		return "redirect:/board/listAll";
		//return "redirect:/board/listAll?test=test";
	}
	
	@GetMapping("listAll")
	public void listAll(Model model
			//,HttpSession session
			)throws Exception{
		/*
		String message = null;
		Object obj = session.getAttribute("result");
		System.out.println(obj);
		if(obj != null) {
			message = (String)obj;
			model.addAttribute("result",message);
			session.removeAttribute("result");
		}
		*/
		model.addAttribute("list",service.listAll());
	}
	
	@GetMapping("read")
	public String read(int bno, Model model, HttpSession session) throws Exception{
		service.updateCnt(bno, session);
		model.addAttribute(service.read(bno)); // board
		return "/board/read";
	}
	
	// 수정페이지 요청
	@GetMapping("modify")
	public void modify(int bno, Model model)throws Exception{
		model.addAttribute("board",service.read(bno));
	}
	
	@PostMapping("modify")
	public String modify(Board board, RedirectAttributes rttr)throws Exception{
		service.modify(board);
		rttr.addAttribute("bno",board.getBno());
		return "redirect:/board/read";
		//return "redirect:/board/read?bno="+board.getBno();
	}
	
	@PostMapping("remove")
	public String remove(int bno,
			RedirectAttributes rttr
			)throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("result","SUCCESS");
		return "redirect:/board/listAll";
	}
	
	@GetMapping("listPage")
	public void listPage(Criteria cri,Model model)throws Exception{
		model.addAttribute("list",service.listCriteria(cri));
		model.addAttribute("pm",service.getPageMaker(cri));
		
		
	}
	
}












