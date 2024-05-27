package com.poscodx.mysite.controller;

import com.poscodx.mysite.controller.action.main.MainAction;

public class MainServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Action getAction(String actionName) {
		// 부모인 ActionServlet에서 이미 구현했기 때문에
		return new MainAction(); // action만 리턴해주는 팩터리메소드
	}
	
}

//factory method pattern에 맞춰서 Refactoring

//public class MainServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request
//			.getRequestDispatcher("/WEB-INF/views/main/index.jsp") // jsp로 코드의 흐름을 바꿔놓는 것. jsp 경로를 지정해주기
//			.forward(request, response); 
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
