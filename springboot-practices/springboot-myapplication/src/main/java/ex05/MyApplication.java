package ex05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication // 3가지 어노테이션 포함하는 메타어노테이션
public class MyApplication {
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(MyApplication.class, args);
		// 이렇게 실행하는 방식보다 좋은 방식이 존재한다. => Runner 클래스를 만들어서 인터페이스의 run 메소드로 실행시키자
		// 스프링은 모든 빈들이 컨테이너에 들어있다. 주입받기도 쉬워진다.
		// MyComponent myComponent = ac.getBean(MyComponent.class);
		// myComponent.printHello();
	}

}
