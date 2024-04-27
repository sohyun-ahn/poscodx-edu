package chapter04;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<Rect> set = new HashSet<>(); // <> 생략가능
		
		Rect r1 = new Rect(10,20);
		Rect r2 = new Rect(10,20);
		Rect r3 = new Rect(4, 50);
		
		set.add(r1);
		set.add(r2);
		set.add(r3);
		
		// Rect 클래스에서 hashCode() 오버라이딩을 안하면 두번 출력
		// 하면 한번 출력
		for(Rect r : set) {
			System.out.println(r);
		}

	}

}
