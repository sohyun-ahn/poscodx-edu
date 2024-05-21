package emaillist;

import java.util.List;
import java.util.Scanner;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistApp {

	private static Scanner scanner = new Scanner(System.in);
	private static EmaillistDao emaillistDao = new EmaillistDao();
	
	public static void main(String[] args) {
		// console용 어플리케이션 만드는 중
		
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
		
		if(scanner != null) {			
			scanner.close();
		}

	}

	private static void doInsert() {
		// name, email, passwd 같은 것들을 vo에 담아서 보내기
//		System.out.println("doInsert");	
		System.out.print("성: ");
		String lastName = scanner.nextLine();
		
		System.out.print("이름: ");
		String firstName = scanner.nextLine();
		
		System.out.print("이메일: ");
		String email = scanner.nextLine();
		
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		emaillistDao.insert(vo);
		
		doList();
	}

	private static void doDelete() {
		System.out.println("doDelete");
		
		String email = scanner.nextLine();
		emaillistDao.deleteByEmail(email);

		doList();
	}

	private static void doList() {
		// DAO에서 list~!
		// 데이터 교환을 할때 객체로 함
		// 하나의 row를 vo로 담음. 
		// vo들을 보이면 됨
		System.out.println("doList");
		
		List<EmaillistVo> list = emaillistDao.findAll();
		for(EmaillistVo vo : list) {
			System.out.println(vo.getLastName() + " " + vo.getFirstName() + ": " + vo.getEmail());
		}
		
		
	}

}
