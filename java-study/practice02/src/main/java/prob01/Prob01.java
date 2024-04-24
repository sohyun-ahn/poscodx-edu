package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in  );

		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };

		System.out.println("금액: ");
		
		int money = scanner.nextInt();
		
		int[] moneysCount = new int[MONEYS.length];
		
		for (int m=0;m<MONEYS.length;m++) {
			int count = money / MONEYS[m];
			money -= MONEYS[m]*count;
			moneysCount[m] = count;
		}
		
		System.out.println("");
		
		for (int m=0;m<MONEYS.length;m++) {
			System.out.println(MONEYS[m] + "원 : " + moneysCount[m] + "개");
		}
		
		scanner.close();
 	}
}