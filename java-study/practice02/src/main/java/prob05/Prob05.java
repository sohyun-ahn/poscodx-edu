package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );

		// n(no)을 입력받을 때까지 반복
		while( true ) {

			// 정답 랜덤하게 만들기
			Random random = new Random();
			
			int correctNumber = random.nextInt( 100 ) + 1; // 1 <= number < 101
			System.out.println("정답: " + correctNumber); 
			System.out.println("수를 결정하였습니다. 맞추어보세요");
			
			// 정답 추측 게임
			int gameCount = 1;
			int rangeStart = 1;
			int rangeLast = 100;
			while(true) {
				System.out.println(rangeStart + "-" + rangeLast);
				System.out.print(gameCount + "회>>");				
				int guessNumber = scanner.nextInt();
				
				if(guessNumber == correctNumber) {
					System.out.println("맞았습니다.");
					break;
				}else if (guessNumber > correctNumber) {
					System.out.println("더 낮게");
					rangeLast = guessNumber;
				}else {
					System.out.println("더 높게");
					rangeStart = guessNumber;
				}		
				gameCount++;		
			}
			
			//새 게임 여부 확인하기
			System.out.print( "다시 하겠습니까(y/n)>>" );
			String answer = scanner.next();
			if( "y".equals( answer ) == false ) {
				break;
			}
			System.out.println();
		}
		
		scanner.close();
	}

}