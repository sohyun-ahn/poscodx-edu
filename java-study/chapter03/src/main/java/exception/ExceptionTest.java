package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;
		
		System.out.println("Some Code1...");
		
		try {
			// 0으로 나누기 때문에 ArithmeticException 예외 발생
			System.out.println("Some Code2...");
			System.out.println("Some Code3...");
			
			int result = (1 + 2 + 3) / b;
			System.out.println(result);
			
			System.out.println("Some Code4...");
			System.out.println("Some Code5...");
		} catch(ArithmeticException e) {
			// 예외 처리
			// catch문을 비워두면 마치 잘 실행 되는 것처럼 보임 => 빌런
			// 회사마다 정해진 예외 처리 로그나 오류코드 있음
			e.printStackTrace(); // console에 출력됨
			
			// 예외 처리시, 꼭 해야할 것!!
			// 1. loging 로깅 (파일로 꼭 남겨놔야함) 
			System.out.println("error: " + e );
		
			// 2. 사과를 해야함
			System.out.println("미안합니다...");
			
			// 3. 정상 종료
			// = 에러 발생 이후 코드를 실행되지않고 그냥 종료시키는 것
			// System.exit(0);
			return;
		} finally {
			System.out.println("자원 정리: ex) close file, socket, db connection");
		}
		
		// finally를 쓰면, 이쪽에 코드를 적지 않는 게 좋음
		// 자원 정리는 맨 마지막에 넣어야하기 때문
		System.out.println("Some Code6...");
		System.out.println("Some Code7...");

	}

}
