package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		String s1 = "Hello " + "World" + " java" + 17;
		String s2 = new StringBuffer("Hello ").append("World")
				         .append(" java").append(17).toString();
		
		System.out.println(s1);
		System.out.println(s2); // s1은 사실 s2처럼 자동으로 해주는 것.
	}
}
