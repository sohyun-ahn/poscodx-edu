package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			File file = new File("./phone.txt");
			if(!file.exists()) { // FileNotFoundException 처리
				System.out.println("file not found");
				return;
			}
			
			System.out.println("=== 파일정보 ===");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.length() + "Bytes");
			System.out.println(file.lastModified());
			
			Date d = new Date(file.lastModified()); 
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d));		
			
			System.out.println("=== 전화번호 ===");
			
			// 1. 기반스트림
			FileInputStream fis = new FileInputStream(file);
			
			// 2. 보조스트림02 ( byte|byte|byte 들어오면 => char로 변환 )
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			// 3. 보조스트림03 ( char|char|char|\n => "charcharchar" 스트링으로 변환 )
			br = new BufferedReader(isr);
			
			// 4. 처리
			String line = null;
			while((line = br.readLine()) != null) {
				// Tokenizer 만들기
				StringTokenizer st = new StringTokenizer(line, "\t "); // seperator로 나눠줄 문자 세팅
				
				int index = 0; // 둘리:111-111-1111\n 으로 만들기 위해
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					if(index == 0) { // 이름
						System.out.print(token + ":");
					} else if(index == 1) {  // 전화번호1
						System.out.print(token + "-");
					} else if(index == 2) {  // 전화번호2
						System.out.print(token + "-");
					} else if(index == 3) {  // 전화번호3
						System.out.print(token + "\n");
					}
					index++;
				}
				
//				System.out.println(line);
			}
			
		}catch(UnsupportedEncodingException e) {
			// utf-8 => utt-8 이런 식으로 오타 있을때
			System.out.println("error: " + e);
		} catch(IOException e) {
			System.out.println("error:" + e);
		} finally {
			 try {
				 if(br != null) {
					 br.close();
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
