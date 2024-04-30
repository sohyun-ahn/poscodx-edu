package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {

	public static void main(String[] args) {
		
		BufferedReader br = null ;
		try {
			// 기반 스트림  // .: chapter04
			FileReader fr = new FileReader("./src/main/java/io/BufferedReaderTest.java");
			
			// 보조 스트림
			// 보조 스트림만 닫아주면 됨
			br = new BufferedReader(fr);
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found: " + e);
		} catch (IOException e) { // readLine()에서 발생가능
			System.out.println("error:" + e);
		} finally {
			try {
			if(br != null) {
				br.close();
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
