package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listWriters;
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	
	public ChatServerThread( Socket socket, List<PrintWriter> listWriters ) {
		   this.socket = socket;
		   this.listWriters = listWriters;
	}

	@Override
	public void run() {
		try {		
			//1. Remote Host Information
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			ChatServer.log("connected by client[" + remoteHostAddress + " : " + remotePort + "]");
			
			//2. 스트림 얻기
			bufferedReader = 
					new BufferedReader( new InputStreamReader( socket.getInputStream(), StandardCharsets.UTF_8 ) );
			printWriter = 
					new PrintWriter( new OutputStreamWriter( socket.getOutputStream(), StandardCharsets.UTF_8 ), true );
			
			while( true ) {
				//3. 요청 처리 			
				String request = bufferedReader.readLine();  // blocking
				if( request == null ) {
					doQuit(printWriter);
					ChatServer.log( "closed by client[" + remoteHostAddress + " : " + remotePort + "]" );
					break;
				}
				
				// 4. 프로토콜 분석
				// ":" 처리하기 위해
				// (optional) base64이용해서 하기
				String[] tokens = request.split( ":" );
				tokens[0] = tokens[0].toLowerCase(); // 모두 소문자로 변경
				if(tokens.length > 2) {
					for(int i=2;i<tokens.length;i++) {
						tokens[1] += ":" + tokens[i];
					}
				}
				
				if( "join".equals( tokens[0] ) ) {
					doJoin( tokens[1], printWriter );
					
				} else if( "message".equals( tokens[0] ) ) {
					doMessage( tokens[1] );
					
				} else if( "quit".equals( tokens[0] ) ) {	
					doQuit(printWriter);
					
				} else {
					ChatServer.log( "에러: 알 수 없는 요청(" + tokens[0] + ")" );
				}
			} 
		}catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	
	private void doJoin( String nickName, PrintWriter writer ) {
		   this.nickname = nickName;
				
		   String data = "📢 " + nickName + "님이 참여하였습니다."; 
		   broadcast( data, false );
				
		   /* writer pool에  저장 */
		   addWriter( writer );

		   // ack
		   printWriter.println( "📢 " + nickName + "님 채팅 접속 성공!!" );
		   printWriter.flush();
		}

	private void addWriter( PrintWriter writer ) {
		// synchronized : 여러 스레드가 하나의 공유 객체에 접근할 때, 동기화를 보장
		synchronized( listWriters ) {
		      listWriters.add( writer );
		   }
		}

	private void doMessage(String data) { 
		broadcast( data, true );
	}
	
	private void doQuit( PrintWriter writer ) {
	   removeWriter( writer );
			
	   String data = "📢 " + nickname + "님이 퇴장하였습니다."; 
	   broadcast( data, false );
	}

	private void broadcast( String data , boolean isDoMessage) {
	   synchronized( listWriters ) {
		   for( Writer writer : listWriters ) {
			   PrintWriter printWriter = (PrintWriter) writer;
			   if(isDoMessage && (printWriter != this.printWriter)) {
				   printWriter.println( nickname + ": " + data );
			   } else {
				   printWriter.println( data );
			   }
	       }
	   }
	}

	private void removeWriter( PrintWriter writer ) {
		synchronized( listWriters ) {
		      listWriters.remove( writer );
		   }
		}
	
}
