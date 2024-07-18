package com.poscodx.emaillist.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscodx.emaillist.dto.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handlerNoHandlerFoundException(Exception e) {
		return "index";
	}
	
	@ExceptionHandler(Exception.class)
	public void handler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		log.error(errors.toString());

		// 2. 요청구분
		// 500 error for json request 
		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*")) {
			// 3. json 응답
			JsonResult jsonResult = JsonResult.fail(errors.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json; charset=utf-8");
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
			
			return;
		}

		// 404 for html request
		// api로 처리
		if (e instanceof NoHandlerFoundException) {
			// 404error 처리
			// request.getRequestDispatcher("/WEB-INF/views/errors/404.jsp").forward(request, response);
			request.getRequestDispatcher("/error/404").forward(request, response);
			return;
		} 
		
		// 500 for html
		request.setAttribute("error", errors.toString());
		// request.getRequestDispatcher("/WEB-INF/views/errors/exception.jsp").forward(request, response);
		request.getRequestDispatcher("/error/500").forward(request, response);
		

		
//		// 로깅
//		StringWriter errors = new StringWriter();
//		e.printStackTrace(new PrintWriter(errors));
//		log.error(errors.toString());
//		
//		// 에러 페이지 포워딩
//		request.setAttribute("errors", errors.toString());
//		request.getRequestDispatcher("/error/500").forward(request, response);
	}
}
