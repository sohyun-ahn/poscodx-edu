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

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 접근제어 Access Control
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/board");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		///////////////////////////////////////////////////

		Long no = null;

		if (request.getParameter("no") != null) {
			// 답글일 경우
			no = Long.parseLong(request.getParameter("no"));

			BoardVo boardVo = new BoardDao().findByNo(no);
			request.setAttribute("boardVo", boardVo);

		}

		request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
	}

}
