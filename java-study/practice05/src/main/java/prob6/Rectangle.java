package prob6;

public class Rectangle extends Shape implements Resizable {
	
	double width;
	double height;
	
	public Rectangle(double w, double h) {
		this.width = w;
		this.height = h;
	}
	
	double getArea(){
		return width * height;
	} 
	
	public void resize(double arg) {
		width *= arg;
		height *= arg;
	}
	
	public double getPerimeter() {
		return(width + height) * 2;
	}
}
