package ex02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
//@Configuration // spring 에서 사용
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
