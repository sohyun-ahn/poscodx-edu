package ex04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig04 {
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web
                	.ignoring()
                	.requestMatchers(new AntPathRequestMatcher("/assets/**"));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	 http
	     	.formLogin()
	     	.and()
	     	
	     	.httpBasic()
	     	.and()
	     	
	     	// AuthorizationFilter
	     	.authorizeHttpRequests(/* Access Control List */)  // 접근제어할 것들만! 인가 권한만 가지고 하면 인증이 이미 포함되어있다. 
	     	.anyRequest()
	     	.permitAll();
 	
    	
    	return http.build();
    }
}
