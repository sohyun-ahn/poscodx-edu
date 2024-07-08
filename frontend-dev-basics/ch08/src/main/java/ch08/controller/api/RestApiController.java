package ch08.controller.api;

import java.util.Arrays;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch08.dto.JsonResult;
import ch08.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class RestApiController {
	
	@PostMapping
	public JsonResult create(@RequestBody UserVo vo) {
		vo.setNo(10L);
		vo.setPassword("");
		
		return JsonResult.success(vo);
	}
	// api 만들때 잘 사용
	// ResponseBody 는 자동으로 해줌
	// RequestBody는 달아야함!
	
	@GetMapping
	public JsonResult read() {
		return JsonResult.success(Arrays.asList(
				new UserVo(1L, "둘리"),
				new UserVo(2L, "마이콜"),
				new UserVo(3L, "또치")));
	}
	
	@PutMapping("/{no}")
	public JsonResult update(
		@PathVariable("no") Long no, 
		@RequestBody UserVo vo) {
		vo.setNo(no);
		vo.setPassword("");
		
		return JsonResult.success(vo);
	}

	@DeleteMapping("/{no}")
	public JsonResult delete(
		@PathVariable("no") Long no, 
		@RequestParam(value="password", required=true, defaultValue="") String password) {
		System.out.println("password: " + password);
		return JsonResult.success(no);
	}
}
