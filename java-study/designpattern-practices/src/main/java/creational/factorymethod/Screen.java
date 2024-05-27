package creational.factorymethod;

// Creator
public abstract class Screen {
	// factoryMethod : 서브 공장 클래스에서 재정의할 객체 생성 추상 메서드
	public abstract Drawable getShape(int shapeNo); // getShape() : factory method 가 된다.
	
	// operation
	public void display(int shapeNo) {
		Drawable drawable = getShape(shapeNo);

		if(drawable == null) {
			System.out.println("도형이 없습니다.");
		}
		
		drawable.draw();
	}
}