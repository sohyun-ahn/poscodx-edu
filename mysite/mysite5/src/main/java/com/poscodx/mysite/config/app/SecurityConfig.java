package com.poscodx.mysite.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.poscodx.mysite.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web
            		.ignoring()
            		.requestMatchers(new AntPathRequestMatcher("/favicon.io"))
            		.requestMatchers(new AntPathRequestMatcher("/assets/**"));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	String s = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
    	
    	http
    		.logout()
    		.logoutUrl("/user/logout") // spring security가 로그아웃 시켜주는 것
    		.logoutSuccessUrl("/")
    		.and()
    	
    		// login configurer
	   		.formLogin() // 로 들어오면, authentication manager 가 빈 등록이 되어서 세팅하고 있다. 이 매니저를 설정해야한다.
	   		.loginPage("/user/login") // auth필요한 곳은 여기로 빠지게
	   		.loginProcessingUrl("/user/auth")
	   		.usernameParameter("email")
	   		.passwordParameter("password") // authentication manager한테 인증하라고 해서 애가 security context에서 봐서
	   		.defaultSuccessUrl("/") // 성공시 메인으로 보내기
	   		.failureUrl("/user/login?result=fail") // 실패시 url
	   		.and()
	   		
	   		.csrf()
	   		.disable()
	   		
	   		// authorization configurer
	   		.authorizeHttpRequests(registry -> {
	   			registry
		   			/* ACL */
	   			

	   				.requestMatchers(new RegexRequestMatcher("^/admin/?.*$", null))
					.hasRole("ADMIN")

					.requestMatchers(new RegexRequestMatcher("^/board/?(write|reply|delete|modify)?/.*$", null))
					.hasAnyRole("ADMIN", "USER")
					
	   				.requestMatchers(new RegexRequestMatcher("^/user/update$", null))
		   			.hasAnyRole("ADMIN", "USER") // security가 세션에 context라는 걸 저장한다. 거기서 assertication - authority가 있음
		   			
		   			.anyRequest()
		   			.permitAll();
	   		});
//    	.exceptionHandling(exceptionHandlingConfigurer -> {
//		exceptionHandlingConfigurer.accessDeniedPage("/WEB-INF/views/error/403.jsp")
//});
    	
    	return http.build();
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setPasswordEncoder(passwordEncoder);
    	authenticationProvider.setUserDetailsService(userDetailsService);
    	
    	return new ProviderManager(authenticationProvider);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder(16);
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new UserDetailsServiceImpl();
    }
    
}