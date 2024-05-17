package emaillist;

import java.util.Scanner;

public class EmaillistApp {

	public static void main(String[] args) {
		// console용 어플리케이션 만드는 중
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("(l)ist (d)elete (i)nsert (q)uit > ");
			String command = scanner.nextLine();
			
			if("l".equals(command)) {
				doList();
			} else if("d".equals(command)) {
				doDelete();
			} else if("i".equals(command)) {
				doInsert();
			} else if("q".equals(command)) {
				break;
			} 
		}
		scanner.close();

	}

	private static void doInsert() {
		// name, email, passwd 같은 것들을 vo에 담아서 보내기
		System.out.println("doInsert");	
	}

	private static void doDelete() {
		System.out.println("doDelete");
	}

	private static void doList() {
		// DAO에서 list~!
		// 데이터 교환을 할때 객체로 함
		// 하나의 row를 vo로 담음. 
		// vo들을 보이면 됨
		System.out.println("doList");
		
	}

}
