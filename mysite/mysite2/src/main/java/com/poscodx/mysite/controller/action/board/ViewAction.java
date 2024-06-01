package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = boardDao.findByNo(no);
		boardDao.updateHit(boardVo);

		request.setAttribute("boardVo", boardVo);

		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
	}

}
