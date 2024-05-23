package guestbook.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.GuestbookDao;
import guestbook.vo.GuestbookVo;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		
		if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			vo.setContent(content);
			
			new GuestbookDao().insert(vo);
			response.sendRedirect(request.getContextPath() +"/gb");
			
		} else if("deleteform".equals(action)) {
			RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp"); 
			rd.forward(request, response);
			
		} else if("delete".equals(action)) {
			Long no = Long.parseLong(request.getParameter("no"));
			String password = request.getParameter("password");
			new GuestbookDao().deleteByNoAndPassword(no, password);		
			response.sendRedirect(request.getContextPath()+"/gb") ; 
			
		} else {
			/* default action (list) */
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("guestList", list);
			
			RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/views/index.jsp"); // request가 연장해야하는 위치, 코드의 흐름을 jsp에 할 건지. jsp의 위치를 알려줘야함
			rd.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
