package com.poscodx.mysite.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.poscodx.mysite.event.ApplicationContextEventListener;
import com.poscodx.mysite.interceptor.SiteInterceptor;

@SpringBootConfiguration
//@EnableWebMvc // autoconfiguration 할때 자동 설정됨
public class MvcConfig implements WebMvcConfigurer {
	
	// Thymeleaf View Resolver
	@Bean
	public ViewResolver thymeleafViewResolver(ISpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCharacterEncoding("UTF-8");
		//viewResolver.setOrder(1);

		return viewResolver;
	}
	
//	// JSP View Resolver
//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setExposeContextBeansAsAttributes(true);
//		viewResolver.setExposedContextBeanNames("site");
//		
//		// view : jsp로 갈때 path 설정 (thymeleaf 경로에서 th/ 빼기위해서)
//		viewResolver.setViewNames("views/*");
//		viewResolver.setOrder(0);
//		
//		return viewResolver;
//	}
//	

	// Locale Resolver
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("lang");
		localeResolver.setCookieHttpOnly(false);
		
		return localeResolver;
	}
	
	// 미세설정에서 안되기 때문에 bean으로 설정해서 해야한다!
	// Site Interceptor
	@Bean
	public HandlerInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(siteInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/assets/**");
	}

	// ApplicationContext Event Listener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
	
}
