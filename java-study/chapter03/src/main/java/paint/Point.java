package paint;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void show() {
		// 실제론 graphic library로 구현하면 됨
		System.out.println("점(" + x + ", " + y + ")을 그렸습니다.");
	}
	
	// 오버로딩
	public void show(boolean visible) {
		if(visible) {
			// 코드 중복 하지말고 코드 재사용
//			System.out.println("점(" + x + ", " + y + ")을 그렸습니다.");
			show();
		} else {
			System.out.println("점(\" + x + \", \" + y + \")을 지웠습니다.");
		}
	}
	
	// 굳이 새로운 함수를 만들지 않고 오버로딩으로 구현
//	public void disappear() {
//		System.out.println("점(" + x + ", " + y + ")을 지웠습니다.");
//	}
}
