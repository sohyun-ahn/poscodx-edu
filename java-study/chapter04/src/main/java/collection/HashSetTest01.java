package collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest01 {

	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		
//		String s1 = "둘리";
		String s1 = new String("둘리");
		
		s.add("둘리");
		s.add("마이콜");
		s.add("도우너");
		s.add(s1);  // 하나는 상수풀에 있는 거고, 하나는 힙에 있는 것임. 비교
		
		System.out.println(s.size());
		System.out.println(s.contains(s1)); // 동일성이 아닌 동질성으로 자료구조는 돌아간다. // hashcode 값으로 비교
		
		// 순회
		for(String str : s) {
			System.out.println(str);
		}

	}

}
