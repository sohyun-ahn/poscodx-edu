package paint;

public class Main {

	public static void main(String[] args) {
		Point point = new Point(10, 20);
//		point.setX(10);
//		point.setY(20);
//		
		drawPoint(point);
		point.show(false);
	}
	
	private static void drawPoint(Point point) {
		point.show();
	}

}
