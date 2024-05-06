package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		Scanner scanner = null;
		Socket socket = null;
		PrintWriter printWriter = null;
		String nickname = null;
		
		try {
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			
			// keyboard scanner - thread1, printwriter - thread2 
		    //1. 키보드 연결
			scanner = new Scanner(System.in);

		    //2. socket 생성
			socket = new Socket();

		    //3. 연결
			socket.connect(new InetSocketAddress(hostAddress, ChatServer.PORT));
			
		    //4. reader/writer 생성
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); 
			
		    //5. join 프로토콜
		    System.out.print("닉네임>> " );
		    nickname = scanner.nextLine();
			printWriter.println( "join:" + nickname );
		    printWriter.flush();

		    //6. ChatClientThread 시작
		    new ChatClientThread(socket).start();

		    //7. 키보드 입력 처리
		    while( true ) {
		       System.out.print( ">>" );
		       String input = scanner.nextLine();
						
		       if( "quit".equals( input ) ) {
		           // 8. quit 프로토콜 처리
		    	   printWriter.println( "quit:" + nickname );
		           break;
		       } else {
		           // 9. 메시지 처리 
		    	   printWriter.println( "message:" + input );
		       }
		    }

		} catch (UnknownHostException e) {
			  log( "error: " + e);
		} catch (SocketException e) {
			  log( "Socket Exception: " + e);
		} catch( IOException ex ) {
		      log( "error: " + ex );
		} finally {
			// 10. 자원정리
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public static void log(String message) {
		System.out.println("[ChatClient] " + message);
	}

}
