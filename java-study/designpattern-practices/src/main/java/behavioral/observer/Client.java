package behavioral.observer;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Subject<Integer> intSubject = new Subject<>();
		

		// observer는 등록하면서 anonymous로 만듦
		// observer1
		intSubject.registerObserver(new Observer<Integer> () {
			@Override
			public void update(Integer val) {
				System.out.println("Observer01: " + val);
				
			}
			
		});
		
		// observer2
		// functional interface
		// anonymous로 가져다가 컴파일러가 추론기능을 통해 해줌.
		intSubject.registerObserver( val -> {
				System.out.println("Observer02: " + val);	
			}
		);
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print(">> ");
			int val = scanner.nextInt();
			intSubject.changeSubject(val);
		}
		
	}

}
