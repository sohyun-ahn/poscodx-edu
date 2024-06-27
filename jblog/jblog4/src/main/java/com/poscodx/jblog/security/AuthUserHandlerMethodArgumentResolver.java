package com.poscodx.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscodx.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		// @AuthUser가 안 붙어 있다면,
		if (authUser == null) {
			return false;
		}

		// 파라미터 타입이 UserVo가 아니면,
		if (!parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}

		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		if (!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED; // unresolve
		}

		// resolve를 했다는 얘기
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(); // tomcat에 종속적인 resolve가 된다.
		HttpSession session = request.getSession();

		return session.getAttribute("authUser");

	}

}
