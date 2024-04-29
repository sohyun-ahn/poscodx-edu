package prob01;

public class PrinterTest {

	public static void main(String[] args) {
		Printer printer = new Printer();
		// printer.<Integer>println( 10 ); // 이게 맞지만, 숨은 기능(파라미터 타입으로 컴파일러가 추론하는 기능)이 있어서 안써도 가능. 
		printer.println( 10 );
		printer.println( true );
		printer.println( 5.7 );
		printer.println( "홍길동" );

		// 가변 파라미터 실습
		System.out.println(printer.sum(1));
		System.out.println(printer.sum(1,2,3));
		System.out.println(printer.sum(1,2,3,4,5));
		
		printer.println( 10, "홍길동" );
		printer.println( 10, true, "홍길동" );
	}
	
}