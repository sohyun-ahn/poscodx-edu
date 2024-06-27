package com.poscodx.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 1. 스프링 애플리케이션의 부트스트래핑 역할 : BootStrapping Class
 * 2. 설정 클래스 : Configuration Class
 */
@SpringBootApplication
// @ComponentScan // 하부를 전체 스캐닝함
public class HelloWorldApplication {
	public static void main(String[] args) {
		/**
		 * SpringApplication.run(...) 안에서 일어나는 일 : Bootstrap
		 * 1. 어플리케이션 컨텍스트(Application Context = Spring Container) 생성
		 * 2. 만약, 웹어플리케이션이면, 웹어플리케이션 타입 결정(SpringMVC(서버필요), Reactive(비동기식으로 요청을 갖다 처리, 서비필요아닌거))
		 * 3. annotation scanning + configuration class를 통해서 빈 생성/등록/와이어링(DI) 작업 - 컨테이너 안에 빈 생성
		 * 4. 만약, 웹어플리케이션이고 타입이 Spring MVC이면 디스패처서블릿 필요
		 * 		- 내장(embeded) 서버(TomcatEmbededServiceServletContainer) 인스턴스 생성
		 * 		- 서버 인스턴스에 웹어플리케이션을 배포
		 * 		- 서버 인스턴스 실행
		 * 5. ApplicationRunner 인터페이스를 구현한 빈을 Application Context에서 찾아서 실행
		 */
		try(
			// close를 자동으로 하려는 목적 closable : try with resources
			ConfigurableApplicationContext ac = SpringApplication.run(HelloWorldApplication.class, args);
		){};
				
				
//		ApplicationContext ac = null;
//		
//		try {
//			ac = SpringApplication.run(HelloWorldApplication.class, args); // run 안에서 container 생성, 거기서 만든 컨테이너를 리턴
//		} catch(Throwable ex) {
//		
//		} finally{
//			((ConfigurableApplicationContext) ac).close();
//		}
		
	}

}
