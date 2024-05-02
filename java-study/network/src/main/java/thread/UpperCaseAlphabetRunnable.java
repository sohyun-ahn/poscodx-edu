package thread;

public class UpperCaseAlphabetRunnable extends UpperCaseAlphabet implements Runnable{
	// Runnable 인터페이스를 구현하고 있는 클래스를 객체로 만든다면, Thread에 태울 수 있음.

	@Override
	public void run() {
		super.print();	
	}
	
}
