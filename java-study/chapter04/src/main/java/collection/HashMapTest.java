package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();
		
		m.put("one", 1);     // auto boxing   (key, value)
		m.put("two", 2);  
		m.put("three", 3);
		
		int i = m.get("one");       // get은 key로 접근
		int j = m.get(new String("one")); 
		System.out.println(i + ":" + j); // 1:1
		
		m.put("three", 3333);
		System.out.println(m.get("three"));  // 3333

		// 순회
		Set<String> s = m.keySet();
		for(String key : s) {
			System.out.println(m.get(key));
		}
	}

}
