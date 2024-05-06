package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
	     /* reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리) */
			
			try {
				while(true) {
					String message;
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
					message = bufferedReader.readLine();
					System.out.println(message);
					System.out.println("");
					if(message == null) {
						ChatClient.log( "서버가 종료되었습니다" );
						break;
					}
				}
			} catch (SocketException e) {	
				// ChatClient.log( "Thread Socket Exception: " + e);	
			} catch (UnsupportedEncodingException e) {
				ChatClient.log( "error: " + e);
			} catch (IOException e) {
				ChatClient.log( "error: " + e);
			} 
		

	}

}
