package ex01;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Spring Test Integration (Boot 테스트가 아님) 
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MyApplication.class})
public class MyApplicationTest01 {

	@Autowired
	private MyComponent myComponent;
	
	@Test
	public void testMyComponentNotNull() {
		assertNotNull(myComponent);
	}

}
