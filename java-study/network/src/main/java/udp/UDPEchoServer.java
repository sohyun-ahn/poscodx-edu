package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {
	public static final int PORT = 6000; 
	public static final int BUFFER_SIZE = 256;

	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			// 1. socket 생성
			socket = new DatagramSocket(PORT);
			
			// socket에 synchronized 걸어서, 데이터가 많이 오면 스레드로 만들어도 됨
			// 
			while(true) {
				// 2. 데이터 수신
				// receive data
				// packet안에 byte buffer를 넣어줌
				DatagramPacket rcvPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(rcvPacket); // blocking 
				
				
				byte[] rcvData = rcvPacket.getData();
				
				//offset
				int offset = rcvPacket.getLength();
				String message = new String(rcvData, 0, offset, "Utf-8");
				
				System.out.println("[UDP Echo Server] received: " + message);
				
				// 3. 데이터 송신
				// send data
				byte[] sndData = message.getBytes("utf-8");
				DatagramPacket sndPacket = new DatagramPacket(sndData, sndData.length, rcvPacket.getAddress(), rcvPacket.getPort());
				socket.send(sndPacket);
			}
			
		} catch (SocketException e) {
			System.out.println("[UDP Echo Server] error: " + e);
			
		} catch (IOException e) {
			System.out.println("[UDP Echo Server] error: " + e);
		} finally {
			if (socket != null && !socket.isClosed()){
				socket.close();
			}
		}

	}

}
