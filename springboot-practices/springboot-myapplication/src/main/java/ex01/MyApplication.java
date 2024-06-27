package ex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication // meta annotation 
@Configuration // spring 에서 사용
public class MyApplication {

	@Bean
	public MyComponent myComponent() {
		return new MyComponent(); // 명시적 설정이라 MyComponent는 @Component 안달아도 가능
	}
	
	public static void main(String[] args) {
		try(ConfigurableApplicationContext ac = SpringApplication.run(MyApplication.class, args)){
			MyComponent myComponent = ac.getBean(MyComponent.class);
			System.out.println(myComponent);
		}
		
	}

}
