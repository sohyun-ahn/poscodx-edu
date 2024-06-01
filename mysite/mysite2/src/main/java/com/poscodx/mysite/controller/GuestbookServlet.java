package com.poscodx.mysite.controller;

import java.util.Map;

import com.poscodx.mysite.controller.action.guestbook.DeleteAction;
import com.poscodx.mysite.controller.action.guestbook.DeleteFormAction;
import com.poscodx.mysite.controller.action.guestbook.InsertAction;
import com.poscodx.mysite.controller.action.guestbook.ListAction;

public class GuestbookServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Action> mapAction = Map.of(
			"insert", new InsertAction(),
			"deleteform", new DeleteFormAction(),
			"delete", new DeleteAction()
			);

	@Override
	protected Action getAction(String actionName) {
		return mapAction.getOrDefault(actionName, new ListAction());
	}
	
}
	
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		String action = request.getParameter("a");
//		
//		if("insert".equals(action)) {
//			String name = request.getParameter("name");
//			String password = request.getParameter("password");
//			String content = request.getParameter("content");
//			
//			GuestbookVo vo = new GuestbookVo();
//			vo.setName(name);
//			vo.setPassword(password);
//			vo.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			vo.setContent(content);
//			
//			new GuestbookDao().insert(vo);
//			response.sendRedirect(request.getContextPath() +"/guestbook");
//			
//		} else if("deleteform".equals(action)) {
//			request
//				.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp") 
//				.forward(request, response);
//			
//		} else if("delete".equals(action)) {
//			Long no = Long.parseLong(request.getParameter("no"));
//			String password = request.getParameter("password");
//			
//			new GuestbookDao().deleteByNoAndPassword(no, password);
//			response.sendRedirect(request.getContextPath() +"/guestbook");
//			
//		} else {
//			/* action이 없을때 들어오는 곳 */
//			/* mysite2/user는 기본 페이지가 없으니깐 메인으로 돌려보낸다던지 처리하기 */
//			List<GuestbookVo> list = new GuestbookDao().findAll();
//			request.setAttribute("guestList", list);
//			
//			request
//				.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp")
//				.forward(request, response);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

