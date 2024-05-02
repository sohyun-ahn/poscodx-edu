package thread;

public class DigitThread extends Thread {

	@Override
	public void run() {
			for(int i = 0; i < 20; i++) {
			System.out.print(i);
			
			// 잠들기
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
