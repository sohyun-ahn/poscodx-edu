package prob05;

public class Account {
	
	private String accountNo;
	private int balance; 
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		System.out.println(getAccountNo() + "계좌가 개설되었습니다.");
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void save(int money) {
		this.balance += money;
		printStatus(money, "입금");
	}
	
	public void deposit(int money) {
		this.balance -= money;
		printStatus(money, "출금");
	}
	
	public void printStatus(int money, String str) {
		System.out.println(getAccountNo() + "계좌에 " + money + "만원이 " + str + "되었습니다.");
	}
	
	
	
}
