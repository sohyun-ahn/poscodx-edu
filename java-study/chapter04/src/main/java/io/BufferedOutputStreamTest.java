package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		BufferedOutputStream bos = null;
		
		try {
			// 기반 스트림
			FileOutputStream fos = new FileOutputStream("hello.txt");
			
			// 보조 스트림 만들기
			bos = new BufferedOutputStream(fos);
			
			for(int i = 'a'; i <= 'z'; i++) { // char를 int 형상화 ( i = 92; i <= 122 와 같은 의미 )
				bos.write(i);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 기반 스트림 열다가 보조 스트림이 null인 상황이면 안되니깐
				if(bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
