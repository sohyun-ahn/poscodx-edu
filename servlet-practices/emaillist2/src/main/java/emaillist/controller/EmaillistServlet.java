package emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// utf-8로 되어있는 데이터가 들어오니깐
		// body 내용도 encoding을 해야 함
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");
		
		// 요청을 제어한다. (action이 무엇인지 구분)
		if("form".equals(action)) {
			RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/views/form.jsp"); 
			rd.forward(request, response);
			
		} else if("add".equals(action)) {
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			String email = request.getParameter("email");
			
			EmaillistVo vo = new EmaillistVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			new EmaillistDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/el");
			
		} else{
			// 이상한 요청이 오더라도 기본 액션으로 처리
			// default action (list)
			List<EmaillistVo> list = new EmaillistDao().findAll();
			request.setAttribute("list", list);
			
			RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/views/index.jsp"); // request가 연장해야하는 위치, 코드의 흐름을 jsp에 할 건지. jsp의 위치를 알려줘야함
			rd.forward(request, response); // forward, servlet에서 받았던 것들과 똑같이 
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
