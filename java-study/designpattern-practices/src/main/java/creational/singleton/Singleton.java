package creational.singleton;

public class Singleton {
	// 객체가 오직 하나만 생성되는 패턴
	private static Singleton instance = null;

	private Singleton() {
		// 외부에서 못 쓰게 만듦
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
