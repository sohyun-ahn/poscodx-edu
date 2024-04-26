package exception;

@SuppressWarnings("serial")
public class MyException extends Exception {
	public MyException(String message) {
		// 사용자 메시지를 받아서 쓰는 것
		super(message);
	}
	
	public MyException() {
		// 내가 정한 기본 메시지를 쓰는 것
		super("MyException Throw");
	}
}
