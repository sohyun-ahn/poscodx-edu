package com.poscodx.mysite.controller.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.repository.GuestbookDao;
import com.poscodx.mysite.vo.GuestbookVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* action이 없을때 들어오는 곳 */
		/* mysite2/user는 기본 페이지가 없으니깐 메인으로 돌려보낸다던지 처리하기 */
		List<GuestbookVo> list = new GuestbookDao().findAll();
		request.setAttribute("guestList", list);
		
		request
			.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp")
			.forward(request, response);
	}

}
