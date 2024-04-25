package chapter03;

public class Student extends Person {
	public Student() {
		// 자식 생성자에서 부모 생성자를 명시적(explicit)으로 호출하지않으면,
		// 자동으로 부모의 기본 생성자를 호출하게 된다.
		// 부모가 기본 생성자가 없으면 불릴 수 없음. 
		// 늘 먼저 불러야함. super()를 넣지않더라도 자동으로 불림. (넣어주더라도 같은 결과)
		System.out.println("Student() called");
	}
}
