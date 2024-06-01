package com.poscodx.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* @RequestMapping 클래스 + 메소드 매핑
*/
@Controller
@RequestMapping("/user")
public class UserController {

	// view-resolver 설정하면 /WEB-INF/views/ 랑 .jsp 설정해두면
	// return "join" 으로 쓸 수 있음
	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "/WEB-INF/views/join.jsp";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		return "redirect:/";
	}

	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam("n") String name) {
		/*
		 * 만일 n이라는 이름의 파라미터가 없으면 400 Bad Request Error가 발생한다.
		 */
		return "UserController.update(" + name + ")";
	}

	@ResponseBody
	@RequestMapping("/update2")
	public String update2(@RequestParam(value = "n", required = true, defaultValue="") String name) {
		/*
		 * required = false로 하든지,
		 * required = true, defaultValue = ""
		 * 파라미터 없다는 에러가 나지않다. optional
		 */
		return "UserController.update(" + name + ")";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public String list(@RequestParam(value = "p", required = true, defaultValue="") int pageNo) {
		/*
		 * dafaultValue도 int가 아닌 String으로 넣어줘야함 "1"
		 * 
		 */
		return "UserController.list(" + pageNo + ")";	
	}

}
