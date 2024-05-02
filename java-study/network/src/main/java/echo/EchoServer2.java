package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
	public static final int PORT = 6000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null; // close()해주려고
		
		try {
			 serverSocket = new ServerSocket();
			 serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT), 10);
			 log("starts....[port: " + PORT + "]");
			 
			 // EchoServer의 try-catch문을 thread에 넣음!
			 // EchoRequestHandler는 thread를 상속받음
			 while(true) {
				 Socket socket = serverSocket.accept();   // blocking
				 new EchoRequestHandler(socket).start();  // 
			 }
			 
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				// 두번 닫으면 오류일 수 있음 -> close 확인하고 처리할 것 (String socket을 닫았을지(isClosed())도 모르니깐)
				if(serverSocket != null && !serverSocket.isClosed()) {					
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[EchoServer] " + message);
	}
}
