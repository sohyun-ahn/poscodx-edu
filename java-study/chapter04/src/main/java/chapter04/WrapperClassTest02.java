package chapter04;

public class WrapperClassTest02 {

	public static void main(String[] args) {
		String s = "123456";
		int i = Integer.parseInt(s);
		
		// cf1. 반대로 해보기 int값을 가지고 string 만들기
		String s1 = String.valueOf(i);
		
		// cf2. 
		String s2 = "" + i;
		
		System.out.println(s + ":" + s1 + ":" + s2);
		
		int a = Character.getNumericValue('A'); // 16진수 A의미 로 10 출력
		System.out.println(a);
		
		// cf: 'A'의 ASCII 코드값을 출력하고 싶다면?
		char c = 'A';
		System.out.println((int)c); // 65출력
		
		// 2진수
		String s4 = Integer.toBinaryString(-15); // 11111111111111111111111111110001
		System.out.println(s4);
		
		// 16진수
		String s5 = Integer.toHexString(15);
		System.out.println(s5);  // f 
		

	}

}
