package chapter04;

import java.util.Objects;

public class Point {
	private int x;
	private int y;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Point [x=" + x + ", y="+ y + "]";
	}
	
	public Point() {
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y); // hashing알고리즘을 숨겨둔 것.
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
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
	
//	public void show() {
//		// 실제론 graphic library로 구현하면 됨
//		System.out.println("점(" + x + ", " + y + ")을 그렸습니다.");
//	}
//	
//	// 오버로딩
//	public void show(boolean visible) {
//		if(visible) {
//			// 코드 중복 하지말고 코드 재사용
////			System.out.println("점(" + x + ", " + y + ")을 그렸습니다.");
//			show();
//		} else {
//			System.out.println("점(\" + x + \", \" + y + \")을 지웠습니다.");
//		}
//	}
	
	// 굳이 새로운 함수를 만들지 않고 오버로딩으로 구현
//	public void disappear() {
//		System.out.println("점(" + x + ", " + y + ")을 지웠습니다.");
//	}
}
