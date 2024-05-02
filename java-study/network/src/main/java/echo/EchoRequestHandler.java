package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;
	
	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
		
	}

	@Override
	public void run() {
		 try {
			 InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			 String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			 int remotePort = inetRemoteSocketAddress.getPort();
			 EchoServer2.log( "connected by client[" + remoteHostAddress + " : " + remotePort + "]");
			 
			 InputStream is = socket.getInputStream();
			 OutputStream os = socket.getOutputStream();
			 
			 PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // 버퍼가 꽉차야 outputStream한테 전달가능. autoflush = true로 설정해서 바로바로 전달되게끔
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			 
			 while(true) {
				 String data = br.readLine();     // blocking
				 if(data == null) {  
					 EchoServer2.log("closed by client");
					 break;
				 }
				 
				 EchoServer2.log("received: " + data);
				 pw.println(data);
			 }
		 } catch (SocketException e) {
			 EchoServer2.log( "Socket Exception by client" + e);
		 } catch (IOException e) {
			 EchoServer2.log( "error: " + e);
		 } finally {
			 try {
				 if(socket != null && !socket.isClosed()) {
					 socket.close();					 
				}
			 } catch(IOException e) {
				 e.printStackTrace();
			 }
		 }
		 
	}
}
