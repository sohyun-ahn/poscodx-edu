package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L; // 객체를 갖다가 바깥으로 전송할때 쓰는 시리얼 no
	
	protected abstract Action getAction(String actionName);
	
	// operation
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// java에서는 optional이라는 것을 가지고 null 을 다룬다.
		// 값으로 다룰때는 if문 쓰기
		String actionName = Optional.ofNullable(req.getParameter("a")).orElse(""); // 만약 a가 없다면? null -> "" 로 처리해버리기
		
		Action action = getAction(actionName);
		if(action == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // 아무것도 없으면 web.xml의 common error page에서 처리하게 해봄
        	// resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ""); // body : ""에 메시지를 담는 것
			return; // return으로 웹플로우의 흐름을 끝내버리기 (= 뒤의 자바 코드를 실행시키지 않게!)
		}
		
		// 자바의 코드흐름과 웹플로우의 흐름을 착각하지 말것.
		// return을 하지 않았으면 자바 코드 실행됨
		action.execute(req, resp);
		
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public static interface Action {
		void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	}

}
