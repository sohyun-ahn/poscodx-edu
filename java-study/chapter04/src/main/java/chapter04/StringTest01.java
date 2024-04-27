package chapter04;

public class StringTest01 {

	public static void main(String[] args) {
		// c:\temp 로 출력하고 싶다면?
		System.out.println("c:\temp");
		
		// "Hello" 	\는 escape할때 사용
		System.out.println("\"Hello\"");

		// \t : tab
		// \n : newline
		// \r : carriage return
		// \b : beep 
		
		System.out.print("hello\tworld\n"); // hello	world
		
		// char
		char c = '\'';		
		System.out.println(c);
	}

}
