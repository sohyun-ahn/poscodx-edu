package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userNo = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(userNo);
		vo.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		vo.setHit(0L);

		if ("".equals(request.getParameter("gNo"))) {
			// 새글일 경우
			vo.setOrderNo(1L);
			vo.setDepth(0L);
			new BoardDao().insert(vo);

			response.sendRedirect(request.getContextPath() + "/board");

		} else {
			// 답글일 경우
			Long groupNo = Long.parseLong(request.getParameter("gNo"));
			Long orderNo = Long.parseLong(request.getParameter("oNo"));
			Long depth = Long.parseLong(request.getParameter("depth"));
			String kwd = request.getParameter("kwd");
			String search = (kwd == null || kwd == "") ? "" : "&kwd=" + kwd;

			vo.setGroupNo(groupNo);
			vo.setOrderNo(orderNo + 1L);
			vo.setDepth(depth + 1L);

			BoardDao boardDao = new BoardDao();
			boardDao.updateByGroupNo(vo);
			boardDao.insertReply(vo);

			response.sendRedirect(request.getContextPath() + "/board?p=" + request.getParameter("p") + search);
		}

	}

}
