package net.koreate.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("HomeController 요청");
		System.out.println("HomeController 처리 완료");
		return "home";
	}
	
	@GetMapping("test1")
	public String test1() {
		System.out.println("HomeController test1 요청");
		System.out.println("HomeController test1  처리 완료");
		return "home";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("HomeController test2 get 요청");
		model.addAttribute("result","test2 job");
		System.out.println("HomeController test2 get 처리완료");
		return "home";
	}
	
	@GetMapping("test3")
	public String test3() {
		System.out.println("test3() 호출");
		System.out.println("test3() 처리완료");
		return "result";
	}
	
}





