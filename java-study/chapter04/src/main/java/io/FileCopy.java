package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream("loopy.jpeg"); // 주 스트림
			os = new FileOutputStream("loopy_copy.jpeg");
			
			int data = -1; // byte or int 읽기 가능 // is.read()는 파일 끝에 도달하면 -1 return
			while((data = is.read()) != -1) {
				os.write(data);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(is != null) {				
					is.close();
				}
				
				if(os != null) {				
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
