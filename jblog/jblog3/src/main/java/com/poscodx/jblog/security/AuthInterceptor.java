package com.poscodx.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// handlermapping
		// 1. handler 종류 확인
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method에 @Auth가 없으면 Type(Class)에 붙어 있는지 확인
		if(auth == null) {
			auth = handlerMethod
					.getMethod()
					.getDeclaringClass()
					.getAnnotation(Auth.class);
		}
		
		// 5. Type이나 Method에 @Auth가 없는 경우
		if (auth == null) {
			return true;
		}

		// 6. 여기까지 왔다는 것은 @Auth가 붙어있다는 것이기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 7. 인증이 안되어 있는 경우
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false; // 뒤로 못가게 꼭! 뒤로가면 핸들러가 있는 상황
		}

		return true;

	}

}
