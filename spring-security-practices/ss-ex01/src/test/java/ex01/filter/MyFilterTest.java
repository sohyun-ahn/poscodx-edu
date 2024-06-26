package ex01.filter;

import javax.servlet.Filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import ex01.config.AppConfig;
import ex01.config.WebConfig;

@ExtendWith(SpringExtension.class) // spring 환경에서 test 해야하기 때문에
@ContextConfiguration(classes= {WebConfig.class, AppConfig.class})
@WebAppConfiguration
public class MyFilterTest {
	private MockMvc mvc;
	
	@BeforeEach // test 1개 할 때마다 mvc 추가 되게 만들어지게
	public void setup(WebApplicationContext applicationContext) {
		Filter myFilter = applicationContext.getBean("myFilter", Filter.class);
		mvc = MockMvcBuilders
				.webAppContextSetup(applicationContext)
				.addFilter(new DelegatingFilterProxy(myFilter), "/*")
				.build();
	}
	
	@Test
	public void testMyFilter() throws Throwable {
		// MockMvc Test
		mvc
		.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(cookie().value("MyFilter", "Works"))
		.andDo(print()); // 지금까지 했던 결과들을 화면에 출력
		
	}
	
	
	
	
	
}
