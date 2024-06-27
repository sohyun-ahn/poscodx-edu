package ex04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @SpringBootApplication 메타 어노테이션
 * 
 * + @SpringBootConfiguration : ex) ex01, ex02
 * + @ComponentScan	: 패키지 ex04를 포함한 하부 패키지를 스캔한다. -> 스캔해서 빈 생성
 * + @EnableAutoConfiguration:
 * 		1. MVC, AOP(Aspect), Security, JPA등을 자동으로 설정
 * 		2. 발견된 Starter Dependency(Library)기반으로 설정(관례를 따르는 default 설정)
 * 		3. application.properties or application.yaml 안의 미세 설정 (필수)
 * 		4. Spring Boot의 Auto Configuration이나 미세 설정으로 되지 않는(이외의) 설정은 Java 설정 필요
 * 
 *  이 세가지를 포함하고 있다.
 */

@SpringBootApplication // 3가지 어노테이션 포함하는 메타어노테이션
public class MyApplication {
	
	public static void main(String[] args) {
		try(ConfigurableApplicationContext ac = SpringApplication.run(MyApplication.class, args)){
		}
		
	}

}
