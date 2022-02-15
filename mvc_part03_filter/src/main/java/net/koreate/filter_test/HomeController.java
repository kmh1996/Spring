package net.koreate.filter_test;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.vo.MemberVO;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="write",method=RequestMethod.GET)
	public void wirte() {}
	
	
	@RequestMapping(value="write",method=RequestMethod.POST)
	public String wirte(
			MemberVO memberVO,
			HttpServletRequest request,
			HttpSession session) throws UnsupportedEncodingException {
		System.out.println(memberVO);
		//request.setCharacterEncoding("utf-8");
		System.out.println("write POST method 시작");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("id : " + id);
		System.out.println("name : " + name);
		System.out.println("password : " + password);
		session.setAttribute("member",memberVO);
		System.out.println("write POST method 종료");
		return "redirect:/";
	}
	
}









