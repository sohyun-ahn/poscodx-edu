package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* action이 없을때 들어오는 곳 */
		Long p = null;

		if (request.getParameter("p") == null) {
			p = 1L;
		} else {
			p = Long.parseLong(request.getParameter("p"));
		}

		BoardDao boardDao = new BoardDao();

		String kwd = request.getParameter("kwd");
		List<BoardVo> list = boardDao.find5PerPageByKWD(p, kwd);
		Long length = boardDao.getLengthByKWD(kwd);
		
		if (kwd == null || kwd == "") {
			 list = boardDao.find5PerPage(p);
			 length = boardDao.getLength();
		}

		request.setAttribute("boardList", list);
		request.setAttribute("boardListLength", length);
		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);

	}

}
