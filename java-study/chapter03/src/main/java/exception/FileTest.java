package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("hello.txt");
			int data = fis.read();
			System.out.println((char)data);
		} catch (FileNotFoundException e) {
			// 'logging, 사과, 프로그램 종료' 로 예외처리
			System.out.println("error: " + e);
		} catch (IOException e) {
			// logging, 사과, 프로그램 종료
			System.out.println("error: " + e);
		} finally {
			// 계속 try catch를 적어야해서 단점 발생!!
			// => spring때 runtime exception으로 처리
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
