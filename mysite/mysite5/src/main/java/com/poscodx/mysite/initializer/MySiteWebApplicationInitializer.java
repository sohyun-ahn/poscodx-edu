package com.poscodx.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscodx.mysite.config.AppConfig;
import com.poscodx.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// web.xml 역할을 하는 initializer 
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// root Application context 설정하는 클래스 넣기
		// web.xml의 Context Parmeters에서 context Config, context class를 지우고, Load Listener도 지워진다.
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// web application context 설정하는 클래스 넣기
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// Servlet을 달라는 게 아니라, mapping하고 싶은 url을 넣기
		// Dispatcher Servlet 지우기
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// Character Encoding Filter 지우기
		return new Filter[] {new CharacterEncodingFilter("UTF-8"), new DelegatingFilterProxy("springSecurityFilterChain")};
	}
	
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// 없으면 404로 가지말고, exception을 일으키기
		DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		servlet.setThrowExceptionIfNoHandlerFound(true);
		
		return servlet;
	}
	
}
