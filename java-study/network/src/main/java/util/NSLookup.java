package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		// ppt 01네트워크소켓의 이해 - p.34
		// 
		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName("www.naver.com");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
