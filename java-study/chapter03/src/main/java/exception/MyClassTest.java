package exception;

import java.io.IOException;

public class MyClassTest {

	public static void main(String[] args) {
		try {
			new MyClass().danger();
//		} catch (Exception e) {
//			e.printStackTrace(); // Exception이 부모이기 때문에 모든 예외들이 다 여기로 들어와서 나머지 catch문 실행X
		}catch (IOException e) {
			e.printStackTrace(); // error가 터진 곳을 보여줌
			// System.out.println("error: " + e); 
		}catch (MyException e) {
//			e.printStackTrace(); // error가 터진 곳을 보여줌
			 System.out.println("MyException error: " + e); 
		}

	}

}
