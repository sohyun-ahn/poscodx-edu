package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // brower가 보내는 헤더 내용
			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );
					
			String request = null;
			
			while(true) {				
				String line = br.readLine();
				
				// 브라우저에서 연결을 끊으면...
				if(line == null) {
					break;
				}
				
				// SimpleHttpServer는 HTTP Header만 처리
				if("".equals(line)) {
					break;
				}
				
				// request header의 첫 줄만 읽음
				if(request == null) {
					request = line;
					break;
				}	
			}
			consoleLog(request);	
			
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				responseStaticResource(outputStream, tokens[1], tokens[2]);
			}else {
				/* methods: POST(CREATE), PUT(UPDATE), DELETE, GET(READ), HEAD, CONNECT */
				// SimpleHttpServer에서는 무시(400 Bad Request)
				response400BadRequest(outputStream, tokens[2]); // 함수 구현
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.

		} catch( Exception ex ) {
			consoleLog( "error:" + ex );
		} finally {
			// clean-up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
				
			} catch( IOException ex ) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	private void responseStaticResource(OutputStream outputStream, String url, String protocol) throws IOException {
		// url에 해당되는 자원을 읽어서 outputStream에 맞게 처리
		// webapp 이라는 정적인 웹사이트 보내주기
		
		// default(welcome) file set 
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		if(!file.exists()) {
			// 없다면, 404 응답을 보내주기
			response404Error(outputStream, protocol); // 함수 구현
			return;
		}
		
		// new io // nio
		byte[] body = Files.readAllBytes(file.toPath()); // 여기 에러는 바깥으로 던져버리겠다. 
		String contentType = Files.probeContentType(file.toPath());
		outputStream.write( (protocol + " 200 OK\n").getBytes( "UTF-8" ) );
		outputStream.write( ("Content-Type: " + contentType + "; charset=utf-8\n").getBytes( "UTF-8" ) );
		outputStream.write( "\n".getBytes() ); 
		outputStream.write( body ); // body
	}
	
	private void response400BadRequest(OutputStream outputStream, String protocol) throws IOException {
		// Bad Request
		File file = new File(DOCUMENT_ROOT + "/error/400.html");
		byte[] body = Files.readAllBytes(file.toPath());
		outputStream.write( ( protocol + " 400 Bad Request\n" ).getBytes() );
		outputStream.write( "Content-Type:text/html\n".getBytes() );
		outputStream.write( "\n".getBytes() ); 
		outputStream.write( body );
		
	}

	private void response404Error(OutputStream outputStream, String protocol) throws IOException {
		// File Not Found
		File file = new File(DOCUMENT_ROOT + "/error/404.html");
		byte[] body = Files.readAllBytes(file.toPath());
		outputStream.write( ( protocol + " 404 File Not Found\n" ).getBytes() );
		outputStream.write( "Content-Type:text/html\n".getBytes() );
		outputStream.write( "\n".getBytes() ); 
		outputStream.write( body );
		
	}

	public void consoleLog( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}
