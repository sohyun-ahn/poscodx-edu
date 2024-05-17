package emaillist.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import emaillist.vo.EmaillistVo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmaillistDaoTest {
	// DAO : Data Access Object
	
	private static int count = 0;
	
	@BeforeAll
	public static void setUp() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		count = list.size();
	}
	
	@Test
	@Order(1)
	public void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setLastName("둘");
		vo.setFirstName("리");
		vo.setEmail("dooly@gmail.com"); 
		
		
		// insert query는 성공시 return 1 했었음
		boolean result = new EmaillistDao().insert(vo);
		assertTrue(result);
		
	
//		테스트 연습용 코드
//		int i = 10;
//		int j = i - 5;
//		
//		// j = 5여야 테스트 성공!
//		// test시 assert(단언) 이용
//		assertEquals(5, j); // expected, actual 순으로 넣기
	}
	
	@Test
	@Order(2)
	public void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		assertEquals(count + 1, list.size());
	}
	
	
	@Test
	@Order(3)
	public void testDeleteByEmail() {
		boolean result = new EmaillistDao().deleteByEmail("dooly@gmail.com");
		assertTrue(result);
	}
	
	@AfterAll
	public static void cleanup() {
		
	}
}
