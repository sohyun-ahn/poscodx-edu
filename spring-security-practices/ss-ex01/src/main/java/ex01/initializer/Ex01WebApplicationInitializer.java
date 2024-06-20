package ex01.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ex01.config.AppConfig;
import ex01.config.WebConfig;

public class Ex01WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; // url mapping
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new DelegatingFilterProxy("myFilter")}; // myFilter를 proxy하니깐 // appConfig에 filter이름을 갖게해야한다.
	}
}
