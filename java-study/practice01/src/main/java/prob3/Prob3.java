package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("숫자를 입력하시오 : ");
		
		while(scanner.hasNextLine()) {
			int last_num = scanner.nextInt();
			int sum = 0;
			int start_num = (last_num%2==1) ? 1 : 0;
			for(int i=start_num;i<=last_num;i+=2) {
				sum += i;
			}
			System.out.println("결과 값 : " + sum);
			System.out.print("숫자를 입력하시오 : ");
		}
		
	}
}
