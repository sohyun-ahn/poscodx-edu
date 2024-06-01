package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Access Control
		if (session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		/////////////////////////////////////////

		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String kwd = request.getParameter("kwd");
		String search = (kwd == null || kwd == "") ? "" : "&kwd=" + kwd;

		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		boardVo.setTitle(title);
		boardVo.setContent(content);
		boardVo.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		new BoardDao().update(boardVo);

		response.sendRedirect(request.getContextPath() + "/board?a=view&result=success&no=" + no + "&p="
				+ request.getParameter("p") + search);
	}

}
