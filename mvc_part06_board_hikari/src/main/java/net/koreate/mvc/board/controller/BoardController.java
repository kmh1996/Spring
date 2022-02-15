package net.koreate.mvc.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.mvc.board.vo.BoardVO;
import net.koreate.mvc.common.service.BoardService;
import net.koreate.mvc.common.util.Criteria;
import net.koreate.mvc.common.util.PageMaker;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	BoardService<BoardVO,PageMaker,Criteria> service;
	
	// /board/register mehotd=GET
	@GetMapping("/register")
	public String register()throws Exception{
		System.out.println("/board/register GET 요청");
		return "/board/register";
	}
	
	// /board/register method = POST
	@PostMapping("/register")
	public String register(
			BoardVO boardVO,
			RedirectAttributes rttr) throws Exception{
		System.out.println(boardVO);
		String result = service.register(boardVO);
		System.out.println(result);
		rttr.addFlashAttribute("result",result);
//		Criteria cri = new Criteria();
//		model.addAttribute("list",service.list(cri));
//		model.addAttribute("pm",service.getPageMaker(cri));
//		return "/board/listPage";
		return "redirect:/board/listPage";
	}
	
	// GET /board/listPage
	@GetMapping("/listPage")
	public void listPage(Criteria cri, Model model)throws Exception{
		System.out.println(cri);
		model.addAttribute("list",service.list(cri));
		model.addAttribute("pm",service.getPageMaker(cri));
	}
	
	// /board/readPage method=GET
	@GetMapping("/readPage")
	public String readPage(
			@RequestParam(name="bno") int bno,
			Criteria cri,
			RedirectAttributes rttr
			)throws Exception{
		System.out.println(cri);
		// viewcnt 조회수 증가
		service.updateViewCnt(bno);
		rttr.addAttribute("bno",bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		return "redirect:/board/readDetail";
		//return "redirect:/board/readDetail?bno="+bno+"&page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum();
	}
	
	// /board/readDetail mehotd = GET
	@GetMapping("readDetail")
	public ModelAndView readDetail(
			@ModelAttribute("cri") Criteria cri,
			ModelAndView mav,
			int bno)throws Exception{
		System.out.println("readDetail");
		System.out.println(cri);
		BoardVO board = service.read(bno);
		mav.addObject("board",board);
		mav.addObject(board); 
		// mav.addObject("cri",cri);
		// mav.addObject("boardVO",board);
		mav.setViewName("board/readPage");
		return mav;
	}
	
	
	// /board/modifyPage method=GET
	@GetMapping("modifyPage")
	public void modifyPage(
			@ModelAttribute("cri") Criteria cri, int bno, Model model) throws Exception{
		// boardVO
		model.addAttribute(service.read(bno));
	}
	
	// /board/modifyPage method=POST
	@PostMapping("modifyPage")
	public String modifyPage(
			BoardVO board,
			Criteria cri,
			RedirectAttributes rttr
			) throws Exception{
		System.out.println(board);
		String message = service.modify(board);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("result",message);
		return "redirect:/board/listPage";
	}
	
	// /board/removePage method=POST
	@PostMapping("removePage")
	public String remove(
			int bno,
			Criteria cri,
			RedirectAttributes rttr
			)throws Exception{
		String result = service.remove(bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/listPage";
	}
	
}














