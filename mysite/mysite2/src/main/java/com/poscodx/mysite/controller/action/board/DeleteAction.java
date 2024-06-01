package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.UserVo;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Access Control
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/board");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/board");
			return;
		}

		Long no = Long.parseLong(request.getParameter("no"));
		String kwd = request.getParameter("kwd");
		String search = (kwd == null || kwd == "") ? "" : "&kwd=" + kwd;

		new BoardDao().deleteByNo(no);

		response.sendRedirect(
				request.getContextPath() + "/board?result=success&p=" + request.getParameter("p") + search);

	}

}
