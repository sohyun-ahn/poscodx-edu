package ex02.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

import ex02.filter.MySecurityFilter01;
import ex02.filter.MySecurityFilter02;
import ex02.filter.MySecurityFilter03;

@Configuration
public class SecurityConfig01 {
	
	@Bean
	public FilterChainProxy springSecurityFilterChain() {
		List<SecurityFilterChain> list = Arrays.asList(
				new SecurityFilterChain() {
					// 이름인데 클래스로 바꿔줘야함.
					public boolean matches(HttpServletRequest request) {
						String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
						
						return new AntPathMatcher().match("/assets/**", uri);
					}
					
					public List<Filter> getFilters(){
						return null;
					}
				},
				
				new SecurityFilterChain() {
					public boolean matches(HttpServletRequest request) {
						String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
						
						return new AntPathMatcher().match("/**", uri);
							
					}
					
					public List<Filter> getFilters(){
						// spring security filter는 나중에 쓰고, 지금은 내가 만든 필터 사용
						
						return Arrays.asList(
								mySecurityFilter01(), 
								mySecurityFilter02(), 
								mySecurityFilter03()
						); // spring security는 builder로 간편하게 설정 가능 
					}
				}
			);
		// asList() : list로 바꿔준다.
		// security filter chain 객체들이 들어와야함
		// Null 이게 url:pattern:assets 어쩌고 이다.
		
		return new FilterChainProxy(list);
	}
	
	// bean 등록
	@Bean
	public MySecurityFilter01 mySecurityFilter01() {
		return new MySecurityFilter01();
	}
	
	@Bean
	public MySecurityFilter02 mySecurityFilter02() {
		return new MySecurityFilter02();
	}
	
	@Bean
	public MySecurityFilter03 mySecurityFilter03() {
		return new MySecurityFilter03();
	}
		
	
}
