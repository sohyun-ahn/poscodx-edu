package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	protected static final int PORT = 8088;
	
	public static void main(String[] args) {
		
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();
		
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			
			serverSocket.bind( new InetSocketAddress( hostAddress, PORT ) );			
			log( "waiting for connection... " + hostAddress + ":" + PORT );
			
			// 3. 요청 대기 
			while( true ) {
				Socket socket = serverSocket.accept();
				new ChatServerThread( socket, listPrintWriters ).start();
			}
		} catch (SocketException e) {
			 log( "Socket Exception: " + e);
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {					
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[ChatServer] " + message);
	}

}
