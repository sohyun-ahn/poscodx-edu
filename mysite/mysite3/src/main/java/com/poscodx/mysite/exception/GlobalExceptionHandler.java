package com.poscodx.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handler(Exception e, Model model) {
		// 1. 로깅(logging) - 비기능적이지만 꼭 해야함, 나중에 디버깅위해
		StringWriter errors = new StringWriter(); 		// 출력을 메모리쪽으로 하게 하는 것, 버퍼에다가 write한다 : StringWriter
		e.printStackTrace(new PrintWriter(errors));		
		System.out.println(errors.toString()); // file로 만들어도 된다
		
		// 2. 사과 페이지 보내주기 + 3. 종료하기
		model.addAttribute("errors", errors.toString());
		return "errors/exception";
	}
}
