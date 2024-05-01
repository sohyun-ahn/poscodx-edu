package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		// 과제: ppt 01네트워크소켓의 이해 - p.34
		
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String data = "";
			while(!data.equals("exit")) {
				System.out.print("> ");
				data = sc.nextLine();
				
				if (data.equals("exit")) {
					break;
				}
				
				InetAddress[] inetAddresses = InetAddress.getAllByName(data);
				for(InetAddress inetAddress : inetAddresses) {
					System.out.print(inetAddress.getHostName()+ " : ");
					System.out.println(inetAddress.getHostAddress());
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
