package prob6;

public class RectTriangle extends Shape {
	
	double width;
	double height;
	
	public RectTriangle(double w, double h) {
		this.width = w;
		this.height = h;
	}
	
	public double getArea() {
		 return width * height / 2;
	}
	
	public double getPerimeter() {
		return(width + height + Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));
	}
}
