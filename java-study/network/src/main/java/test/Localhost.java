package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost(); // ip address
			String hostName = inetAddress.getHostName();  // domain이름 아니고 컴퓨터 이름 Sohyunui-MacBookPro.local
			String hostIpAddress = inetAddress.getHostAddress(); // 192.168.0.170
			
			System.out.println(hostName);
			System.out.println(hostIpAddress); // localhost는 내 localhost이름, 내 반대편 remotehost 이름
			
			byte[] IpAddresses = inetAddress.getAddress(); // original 4byte로 가져오는 것
			for (byte IpAddress : IpAddresses) {
				System.out.println(IpAddress & 0x000000ff); // 범위 넘어가면 2의 보수로 나옴 ex. 192 => -64, 168 => -88, 0x000000ff 하는 이유는 byte를 int로 만들어서 -값이 안나오게
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
