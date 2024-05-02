package thread;

public class ThreadEx01 {

	public static void main(String[] args) {
//		for(int i = 0; i < 10; i++) {
//			System.out.print(i);
//		}
		
		new DigitThread().start();
		
		for(char c = 'a'; c < 'j'; c++) {
			System.out.print(c);
			
			// main스레드가 끝날때까지 프로그램이 종료되지 않는다
			// 잠들기
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
