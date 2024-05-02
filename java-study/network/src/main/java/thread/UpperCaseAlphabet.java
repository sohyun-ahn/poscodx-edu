package thread;

public class UpperCaseAlphabet {
	// Thread가 아닌데 print를 스레드에 태우고 싶다면?
	// -> 하위에서 (상속시켜서) runnable interface를 구현하면 가능!
	public void print() {
		for(char c = 'A'; c <= 'J'; c++) {
			System.out.print(c);
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}
