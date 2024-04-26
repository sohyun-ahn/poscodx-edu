package exception;

import java.io.IOException;

public class MyClass {
	public void danger() throws IOException, MyException{
		System.out.println("Some Code1...");
		System.out.println("Some Code2...");
		
		if(2 - 2 == 0) {
			throw new MyException();
		}
		
		//  throws IOException 해줌으로써 속음
		if(1 - 1 == 0) {
			throw new IOException();
		}
		
		System.out.println("Some Code3...");
		System.out.println("Some Code4...");
	}
}
