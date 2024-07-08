package ch08.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch08.dto.JsonResult;
import ch08.vo.GuestbookVo;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@ResponseBody
	@RequestMapping("/text")
	public String text() {
		return "Text 데이터";
	}
	
	@ResponseBody
	@RequestMapping("/html")
	public String html() {
		return "<h1>AJAX 연습</h1><p>HTML 데이터</p>";
	}
	
	@ResponseBody
	@RequestMapping(value="/json", method=RequestMethod.GET)
	public JsonResult json() {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(1L);
		vo.setName("둘리");
		vo.setContent("호이~~");
		
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/post1", method=RequestMethod.POST)
	public JsonResult post1(GuestbookVo vo) {
		// service -> repository : db insert 성공한 후,
		vo.setNo(1L);
		vo.setPassword("");
		vo.setRegDate("2024-07-08 15:08:20");
		
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/post2", method=RequestMethod.POST)
	public JsonResult post2(@RequestBody GuestbookVo vo) {
		// json으로 오기 때문에 자바객체로 바꿔줌..
		// service -> repository : db insert 성공한 후,
		vo.setNo(1L);
		vo.setPassword("");
		vo.setRegDate("2024-07-08 15:08:20");
		
		return JsonResult.success(vo);
	}
}
