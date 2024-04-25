package paint;

public class ColorPoint extends Point {
	// 자동으로 생성
//	public ColorPoint() {
//		super(); // 자동으로 생성
//	}
	private String color;
	
	public ColorPoint(int x, int y, String color) {
		// 부모의 기본 생성자가 불러짐
//		setX(x);
//		setY(y);
		super(x, y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.println("점(x=" + getX() + ", y=" + getY() + " , color=" + this.color + "를 그렸습니다.");
		super.show();
	}
	
	
	
}
