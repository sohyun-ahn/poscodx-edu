package jstlel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값을 넘기기
		int iVal = 10; 
		long lVal = 10; // casting이기 때문에 가능
		float fVal = 3.14f;
		boolean bVal = true;
		String sVal = "가나다";
		
		request.setAttribute("ival", iVal); // 객체만 저장할 수 있음. iVal에서 autoboxing(int->Integer)이 일어남 // 그래서 Wrapper클래스를 쓰도록 노력하자
		request.setAttribute("lval", lVal);
		request.setAttribute("fval", fVal);
		request.setAttribute("bval", bVal);
		request.setAttribute("sval", sVal);
		
		
		// 객체
		Object obj = null;
		UserVo vo = new UserVo();
		vo.setNo(10L); 
		vo.setName("둘리");
		
		request.setAttribute("obj", obj);
		request.setAttribute("userVo", vo);
		
		
		// map
		Map<String, Object> map = new HashMap<>();
		map.put("ival", iVal);
		map.put("fval", fVal);
		map.put("sval", sVal);
		
		request.setAttribute("m", map);
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/01.jsp")
			.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
