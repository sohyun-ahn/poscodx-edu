package ch08.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ApiTestController {
	
	@RequestMapping("/text")
	public String text() {
		return "text";
	}
	
	@RequestMapping("/html")
	public String html() {
		return "html";
	}
	
	@RequestMapping("/json1")
	public String json1() {
		return "json1";
	}
	
	@RequestMapping("/json2")
	public String json2() {
		return "json2";
	}
	
	@RequestMapping("/json3")
	public String json3() {
		return "json3";
	}

	@RequestMapping("/json4")
	public String json4() {
		return "json4";
	}

}
