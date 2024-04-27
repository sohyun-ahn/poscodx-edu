package prob5;

@SuppressWarnings("serial")
public class MyStackException extends Exception {
	public MyStackException(String message) {
		// 사용자 메시지를 받아서 쓰는 것
		super(message);
	}
}
