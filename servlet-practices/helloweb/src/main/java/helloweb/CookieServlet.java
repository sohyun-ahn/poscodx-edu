package helloweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int visitCount = 0;
		
		// 쿠키 읽기
		// visitCount : 특정 한 client에게 tomcat servlet을 얼마나 호출했나
		// Disk에 cookie store
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())){
					visitCount = Integer.parseInt(cookie.getValue());
					
				}
			}
		}
		
		visitCount++;
		
		// 쿠키 쓰기
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount));
		cookie.setPath(request.getContextPath()); // /helloweb
		cookie.setMaxAge(24 * 60 * 60);  // 초단위로 들어감, 1day로 세팅했음
		response.addCookie(cookie);
		
		
		// 출력
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("<h1>방문횟수: " + visitCount + "</h1>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
