package prob01;

public class Printer {
//	public void println(int i) {
//		System.out.println(i);
//	}
//	public void println(boolean i) {
//		System.out.println(i);
//	}
//	public void println(double i) {
//		System.out.println(i);
//	}
//	public void println(String i) {
//		System.out.println(i);
//	}
	
	// generic으로 바꾸기
//	public <T> T println(T t) {
//		System.out.println(t);
//		return t;
//	}
//	public <T, S> S println(T t, S s) {
//		System.out.println(t);
//		return s;
//	}
	// 제네릭 실습
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	// 가변 파라미터 실습
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer n : nums) {
			s += n;
		}
		return s;
	}
	
	// 가변이면서, 타입도 지정되지않는 경우
	// 오버로딩 대체하지말고, 
	public <T> void println(T... t) {
		System.out.println(t);
	}
	
}
