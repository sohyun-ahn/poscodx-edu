package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 접근제어 Access Control
		// 인증이 필요한 부분
		if (session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		//////////////////////////////////////////////////////

		Long no = Long.parseLong(request.getParameter("no"));

		BoardVo boardVo = new BoardDao().findByNo(no);

		// 로그인 유저랑 글쓴이가 다르다면 돌려보내기
		if (authUser.getNo() != boardVo.getUserNo()) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		request.setAttribute("boardVo", boardVo);

		request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(request, response);
	}

}
