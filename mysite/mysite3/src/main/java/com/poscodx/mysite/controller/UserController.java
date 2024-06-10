package com.poscodx.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/user") // method class mapping 추천
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		userService.join(vo); // controller역할은 service랑 연결시켜주는 거
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(HttpSession session, UserVo vo, Model model) {
//		// 우리는 아직 인증을 못하니깐 임시처리하
//		// authentication을 필터쪽에서 옛날 방식으로 처리해보려고
//		// HttpSession session 받아와서 처리한다
//		
//		// 먼저, service한테 user를 달라고 한다
//		UserVo authUser = userService.getUser(vo.getEmail(), vo.getPassword()); // service쪽에서도 doget메소드불러와 한다
//		if(authUser == null) {
//			model.addAttribute("email", vo.getEmail());
//			model.addAttribute("result", "fail");
//			
//			return "user/login";
//		}
//		
//		// login 처리 (임시)
//		session.setAttribute("authUser", authUser);
//		
//		return "redirect:/";
//	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/";
//	}
//	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		// login한 유저
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo vo) {
		// access control / 인증을 해야함!
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////////////
		
		// login한 유저
		// form에서 넘어오는 UserVo vo 파라미터!
		
		vo.setNo(authUser.getNo());
		userService.update(vo);
		authUser.setName(vo.getName()); // userName 로그인한 유저로 변경
		
		return "redirect:/user/update?result=success";
	}
	
}
