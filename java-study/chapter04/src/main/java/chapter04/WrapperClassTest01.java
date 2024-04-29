package chapter04;

public class WrapperClassTest01 {

	public static void main(String[] args) {
		// 불변, 상수풀 있음.
		// 직접 생성하면 Heap상에 객체가 존재하게 된다.
		// literal(값 자체)를 사용하면 JVM안의 Constant Pool(상수 풀)에서 관리하게 된다.
		Integer i = new Integer(10);
		Character c = new Character('c');
		Boolean b = new Boolean(true);
		
		// Auto Boxing 자동으로 객체를 생성해준다는 의미
		Integer j1 = 10;
		Integer j2 = 20;
		
		System.out.println(j1 == j2);
		System.out.println(j1.equals(j2));
		
		// Auto UnBoxing 그냥 기본 값으로 되어버리는 것
		int m = j1 + 10;
		// 같은 의미 int m = j1.intValue() + 10;

	}

}
