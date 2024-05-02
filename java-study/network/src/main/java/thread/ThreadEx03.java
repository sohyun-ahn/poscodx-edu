package thread;

public class ThreadEx03 {

	public static void main(String[] args) {
		// Thread 클래스로 만든 것들 - thread1,2
		Thread thread1 = new DigitThread();  
		Thread thread2 = new AlphabetThread();
		Thread thread3 = new Thread(new UpperCaseAlphabetRunnable()); // Thread 클래스 아니고 runnable로 구현한 것
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
