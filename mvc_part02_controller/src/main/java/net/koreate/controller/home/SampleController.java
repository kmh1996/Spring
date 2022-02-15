package net.koreate.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.koreate.vo.ProductVO;

@Controller
public class SampleController {
	
	public SampleController() {
		System.out.println("SampleController 생성");
	}
	
	@RequestMapping("product")
	public void product(Model model) {
		ProductVO productVO = new ProductVO("TV",100);
		model.addAttribute("product",productVO);
		
		ProductVO productVO2 = new ProductVO("AUDIO",200);
		model.addAttribute(productVO2);
		// model.addAttribute("productVO",productVO2);
	}
	
	@RequestMapping("doH")
	public ModelAndView doH() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new ProductVO("sample1",100000));
		ProductVO product = new ProductVO("sample2",200000);
		mav.addObject("product",product);
		mav.setViewName("product");
		return mav;
	}
	
	@RequestMapping(value="productWrite"
				  , method=RequestMethod.POST)
	public ModelAndView productWrite(
			ModelAndView mav,
			String name,
			int price,
			ProductVO product) {
		System.out.println("name : " + name);
		System.out.println("age : " + price);
		mav.addObject(new ProductVO(name, price));
		System.out.println(product);
		mav.addObject("product",product);
		mav.setViewName("product");
		return mav;
	}
}










