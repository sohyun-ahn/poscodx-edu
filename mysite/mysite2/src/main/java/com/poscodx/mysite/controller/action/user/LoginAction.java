package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().findByNoANdPassword(email, password);
		
		if(authUser == null) {
			request.setAttribute("email", email); // email은 맞았으면 email을 또 치게하지말고 전달해주면 사용자가 편하게 됨
			request.setAttribute("result", "fail");// '로그인이 실패했습니다'를 보여줄지 말지 결정하게하려고
			
			request
				.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp")
				.forward(request, response);
			return;
		}
		
		// authUser가 있다는 의미 = login 처리
		
	}

}
