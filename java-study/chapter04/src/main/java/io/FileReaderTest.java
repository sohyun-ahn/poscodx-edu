package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class FileReaderTest {
 
	// test.txt => 가나다라마바사 3바이트씩 총 21bytes
	public static void main(String[] args) {
		Reader in = null;
		InputStream is = null;
		
		try {
			in = new FileReader("test.txt"); // 주 스트림! // try-catch 로 감싸줘야하는 상투적인 코드, char단위로 읽는 것
			is = new FileInputStream("test.txt"); // byte단위로 읽는 것
			
			int count = 0;
			int data = -1;
			while((data = in.read()) != -1) {
				System.out.print((char)data); // char 단위씩 읽어서 글자가 안깨짐
				count++;
			}
			System.out.println("");
			System.out.println("count: " + count); // 7
			
			System.out.println("===============");
			
			// 기본 사항 초기화 후 
			count = 0;
			data = -1;
			while((data = is.read()) != -1) {
				System.out.print((char)data); // byte 단위로 읽어서 글자가 깨짐
				count++;                      
			}
			System.out.println("");
			System.out.println("count: " + count); // 21
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(in != null) {				
					in.close();
				}
				if(is != null) {				
					is.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
