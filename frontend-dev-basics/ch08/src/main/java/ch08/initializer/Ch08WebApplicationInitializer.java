package ch08.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ch08.config.WebConfig;

public class Ch08WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// web.xml 역할을 하는 initializer 
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// root Application context 설정하는 클래스 넣기
		// web.xml의 Context Parmeters에서 context Config, context class를 지우고, Load Listener도 지워진다.
		return new Class<?>[] {};
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
		return new Filter[] {new CharacterEncodingFilter("UTF-8")};
	}

}
