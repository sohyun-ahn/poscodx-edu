package ex01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc // messageconverter 기본 설정
@ComponentScan(basePackages= {"ex01.controller"})
public class WebConfig {
	
}
