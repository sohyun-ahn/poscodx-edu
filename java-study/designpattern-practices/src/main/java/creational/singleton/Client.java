package creational.singleton;

public class Client {
	// singleton 클래스를 사용하는 클라이언트
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
		
	}

}
