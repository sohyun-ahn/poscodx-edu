package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().findByNoAndPassword(email, password);
		
		if(authUser == null) {
			request.setAttribute("email", email); // email은 맞았으면 email을 또 치게하지말고 전달해주면 사용자가 편하게 됨
			request.setAttribute("result", "fail");// '로그인이 실패했습니다'를 보여줄지 말지 결정하게하려고
			
			request
				.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp")
				.forward(request, response);
			return;
		}
		
		// 정보은닉 캡슐화
		// authUser가 있다는 의미 = login 처리
		HttpSession session = request.getSession(true); // 처음부를땐, true를 적어주는 게 좋은데 이것은 없으면 생성하라는 의미/ null을 주고 싶으면 비워두던지 false로 만들어두기
		session.setAttribute("authUser", authUser);
		
		// redirect to main
		response.sendRedirect(request.getContextPath());
	}

}
