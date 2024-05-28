package jstlel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 객체의 스코프 (존속범위) (변수의 스코프(자바에서는 {} if 블럭, for 블럭, stack에 들어갔다가 사라지는)랑 착각하지 말 것!) : ref로 attrMap에다가 이름으로 저장하는 것
		 * 	  = 객체의 존재 범위
		 * 
		 * 2. 객체가 오래 존속하는 순서
		 * 	  Application(Context) Scope > Session Scope > Request Scope > Page Scope
		 * 
		 * 3. EL이 이름으로 객체를 찾는 순서 (ex. ${vo.name} vo란 이름을 찾는 순서)
		 *    Application(Context) Scope < Session Scope < Request Scope < Page Scope
		 * 	  
		 *    [주의] 같은 이름으로 여러 범위에 객체를 저장하는 경우 주의가 필요.
		 *    
		 *    
		 *   
		 */
		
		// request scope
		UserVo vo3 = new UserVo();
		vo3.setNo(3L);
		vo3.setName("둘리3");
		request.setAttribute("vo" , vo3);
		
		// session scope
		UserVo vo2 = new UserVo();
		vo2.setNo(2L);
		vo2.setName("둘리2");
		request.getSession(true).setAttribute("vo", vo2); 
		
		// Application Scope (tomcat입장에서는 context가 Application이다)
		UserVo vo1 = new UserVo();
		vo1.setNo(1L);
		vo1.setName("둘리1");
		request.getServletContext().setAttribute("vo", vo1); // tomcat server를 끄지않는 이상 살아있다
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/02.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
