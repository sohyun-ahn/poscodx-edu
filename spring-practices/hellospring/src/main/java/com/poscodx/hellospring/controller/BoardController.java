package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @RequestMapping 메소드 단독 매핑
 * 
*/

@Controller
public class BoardController {
	
	@ResponseBody
	@RequestMapping("/board/write")
	public String write() {
		return "BoardController.write()";
	}
	
	@ResponseBody
	@RequestMapping("/board/view/{no}") // {} 변하는 값이다라는 뜻
	public String view(@PathVariable("no") Long number) {
		/*
		 * @RequestParam이 아닌 다르게 변수를 받는 방법!
		 * @PathVariable 은 {}
		 */
		return "BoardController.view(" + number + ")";
	}
}
