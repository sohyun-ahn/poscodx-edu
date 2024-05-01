package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 6000;
	public static void main(String[] args) {
		ServerSocket serverSocket = null; // close()해주려고
		
		try {
			 serverSocket = new ServerSocket();
			 
			 serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT), 10);
			 
			 Socket socket = serverSocket.accept();  // blocking
			 
			 
			 try {
				 InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				 String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				 int remotePort = inetRemoteSocketAddress.getPort();
				 log( "connected by client[" + remoteHostAddress + " : " + remotePort + "]");
				 
				 InputStream is = socket.getInputStream();
				 OutputStream os = socket.getOutputStream();
				 
				 PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // 버퍼가 꽉차야 outputStream한테 전달가능. autoflush = true로 설정해서 바로바로 전달되게끔
				 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				 
				 while(true) {
					 String data = br.readLine();     // blocking
					 if(data == null) {  
						 log("closed by client");
						 break;
					 }
					 
					 log("received: " + data);
					 pw.println(data);
				 }
			 } catch (SocketException e) {
				log( "suddenly closed by client");
			 } catch (IOException e) {
				 log( "error: " + e);
			 } finally {
				 try {
					 if(socket != null && !socket.isClosed()) {
						 socket.close();					 
					}
				 } catch(IOException e) {
					 e.printStackTrace();
				 }
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
	private static void log(String message) {
		System.out.println("[EchoServer] " + message);
	}
}
