package com.poscodx.springboot.myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class MyWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyWebApplication.class, args);
	}
	
	@Controller
	public class HelloController{
		@ResponseBody // http message converter 기본 설정됨
		@GetMapping("/hello1")
		public String hello1() {
			return "Hello World";
		}
		
		@GetMapping("/hello2")
		public String hello2(Model model) {
			model.addAttribute("name","ash");
			return "hello2"; // 미세 조정 부분 -> application.xml
		}
		
		@GetMapping("/hello3")
		public String hello3() {
			return "th/hello3"; // thymeleaf view resolver -> templates/th/hello.html을 찾는다.
		}
		
	}
}
