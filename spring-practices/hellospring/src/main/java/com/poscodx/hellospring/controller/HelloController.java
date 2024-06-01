package com.poscodx.hellospring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}

	@RequestMapping("/hello2")
	public String hello2(String name) {
		System.out.println("name: " + name);
		return "/WEB-INF/views/hello.jsp";
	}

	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name); // map을 넘겨줘도 되고, 이렇게 넘겨줘도 된다
		mav.setViewName("/WEB-INF/views/hello3.jsp");

		return mav;
	}

	@RequestMapping("/hello4")
	public String hello4(String name, Model model) {
		model.addAttribute("name", name);
		return "/WEB-INF/views/hello3.jsp";
	}

	@ResponseBody
	@RequestMapping("/hello5")
	public String hello5() {
		return "<h1>Hello World</h1>"; // 한글은 깨짐. 한글을 하려면 messageConverter를 가지고 utf-8로 설정을 해줘야한다.s
	}

	@RequestMapping("/hello6")
	public String hello6(String name, Model model) {
		return "redirect:/hello";  // 톰캣에서의 contextPath 기술 /hellospring/hello할 생각하지 말것
	}
	
	@RequestMapping("/hello7")
	public void hello7(HttpServletResponse response) throws IOException {
		response.getWriter().print("<h1>Hello World</h1>");
	}
}
