package paint;

public class Rectangle extends Shape {
	private int x1, x2, x3, x4;
	private int y1, y2, y3, y4;
	
	private String lineColor;
	private String fillColor;
	
	@Override
	public void draw() {
		System.out.println("직사각형을 그렸습니다.");
	}
}
