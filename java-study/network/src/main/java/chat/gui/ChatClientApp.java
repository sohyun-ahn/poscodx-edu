package chat.gui;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClientApp {

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = null;
		
		Socket socket = null;
		PrintWriter printWriter = null;
		
		try {
			
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
	
			scanner = new Scanner(System.in);
	
			socket = new Socket();
			
			socket.connect(new InetSocketAddress(hostAddress, 8088));
			
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); 
			
			while( true ) {
				System.out.println("대화명을 입력하세요.");
				System.out.print(">> ");
				name = scanner.nextLine();
				
				if (!name.isEmpty()) {
					System.out.println( name + "님 이름으로 채팅에 입장합니다." );
					printWriter.println( "join:"+ name );
					break;
				}
				System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
			}
			scanner.close();
			
			new ChatWindow(name, socket).show(); 
			
		} catch (UnknownHostException e) {
			  log( "error: " + e);
		} catch (SocketException e) {
			  log( "Socket Exception: " + e);
		} catch( IOException ex ) {
		      log( "error: " + ex );
		}
		
	}
		
	public static void log(String message) {
		System.out.println("[ChatClient] " + message);
	}

}
