package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null; // close()해주려고
		
		// 예외가 많음
		// server socket에 대한 예외처리
		try {
			// 1. Server Socket 생성
			 serverSocket = new ServerSocket();
			 
			 // 1.1 FIN_WAIT2 -> TIME_WAIT 상태에서도 소켓 포트 할당이 가능하도록 하기위해
			 serverSocket.setReuseAddress(true);
			 
			 // 2. 바인딩(binding)
			 //    Socket에 InetSocketAddress[InetAddress(IpAddress + Port)]를 바인딩 한다. ()의미 => 객체가 있다는 의미
			 // "127.0.0.1" => 이렇게 지정안하고, "0.0.0.0" 으로 설정하는 이유 : 특정 호스트 IP를 바인딩 하지 않기 위해서 (특정 IP대역을 받지않고, 다 받겠다는 의미)
			 // 5000 : 포트번호
			 // 10 : 백로그 큐의 길이
			 serverSocket.bind(new InetSocketAddress("127.0.0.1", 5000), 10);
			 
			 // 3. accept
			 Socket socket = serverSocket.accept();  // blocking = code가 멈춤, telnet으로 찔러주면 실행됨 connetion.
			 //System.out.println("연결!!!");
			 
			 
			// data통신하고 있는 socket에 대한 예외처리
			 try {
				 // inetRemoteSockeetAddress : client쪽의 ipaddress와 포트번호가 들어있음
				 InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				 String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				 int remotePort = inetRemoteSocketAddress.getPort();
				 System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");
				 
				 // 4. IO Stream 받아오기
				 InputStream is = socket.getInputStream();
				 OutputStream os = socket.getOutputStream();
				 
				 // 5. 데이터 읽기
				 while(true) {
					 System.out.println("try to read");
					 // write를 해놓고 read할 때, 
					 // SocketException by clientjava.net.SocketException: Connection reset 예외 발생
					 
					 byte[] buffer = new byte[256];
					 int readByteCount = is.read(buffer); // blocking, -1이면 끊어진 것
					 System.out.println(readByteCount);
					 if(readByteCount == -1) {
						 // 통신이 끊어진 상태
						 // closed by client (close() 호출)
						 System.out.println("[server] closed by client");
						 break;
					 }
					 
					 // close는 안되었고, 데이터가 buffer에 있을때 도달하는 코드
					 String data = new String(buffer, 0, readByteCount, "UTF-8");
					 System.out.println("[server] received: " + data);
					 
					 // 6. 데이터 쓰기
					 os.write(data.getBytes("utf-8"));
					 // SO_TIMEOUT 테스트
					 try {
						 Thread.sleep(3000);
					 } catch (InterruptedException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }
					 
//					 // socketException 발생시키는 예
//					 // MacOS를 위해
//					 // 종료될때까지 3초 기다리기
//					 try {
//						 // client가 내려감
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					 // client가 내려가고 나서 write를 하기 때문에 MacOS에서는 
//					 // (tcp/ip통신에서 그대로 연결이 남아있음.) 이제 rst를 보내게 되어 예외발생
//					 os.write(data.getBytes("utf-8")); 
				 }
				 
				 
			 } catch (SocketException e) {
				 // 연결이 끊어졌을때 발생할 수 있다.
				 // write를 할땐 exception이 없고, read할때 exception이 있다.
				 //	os가 닫아주든 프로세스가 close 명시적으로 되든 
				 // windowsOS에서는 닫혔을때 반대편에서 쓰거나 읽으면 발생할 수 있다.
				 
				 // windoswOS에서는 닫는 코드를 안보내면 반대편으로 rst(reset)를 보냄.
				 // socketexception이 발생
				 // MacOS, Linux, Unix.. 등 에서는 write해야 rst가 보내짐
				 				 
				 // closing rst(reset)
				 // Abruptly 예기치않은 돌발상황
				 System.out.println("[server] SocketException by client" + e);
			 } catch (IOException e) {
				 System.out.println("[server] error: " + e);
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
			System.out.println("[server] error: " + e);
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

}
