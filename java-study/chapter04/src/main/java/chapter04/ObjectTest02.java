package chapter04;

public class ObjectTest02 {

	public static void main(String[] args) {
		Point p1 = new Point(10, 20);
		Point p2 = new Point(10, 20);
		Point p3 = p2;
		
		// == : 두 객체의 동일성 비교
		System.out.println(p1 == p2); // reference값이 다르기 때문에 false
		System.out.println(p2 == p3); // true
		
		// equals() : 두 객체의 동질성 비교(내용 비교)
		//			  부모 클래스 Object의 기본 구현은 동일성(==) 비교와 같다.
		System.out.println(p1.equals(p2)); // false // Point클래스에 equals() 오버라이딩해서 하면 true
		System.out.println(p2.equals(p3)); // true

	}

}
