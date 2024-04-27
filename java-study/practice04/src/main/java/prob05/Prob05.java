package prob05;

public class Prob05 {

	public static void main(String[] args) {
		Base base = new MyBase();
        // Base는 건들지 말고, MyBase만 구현,
		// 이 실행 파일도 건들지 말것!
		base.service("낮");
		base.service("밤");
		base.service("오후"); 
	}
}
