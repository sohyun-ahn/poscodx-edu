package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];
		
		// 상품 입력
		System.out.println("상품 입력: ");
		for (int i=0; i<goods.length;i++) {
			// 상품 출력
			String line = scanner.nextLine();
			String[] data = line.split(" ");
			
			String name = data[0];
			int price = Integer.parseInt(data[1]);
			int count = Integer.parseInt(data[2]);
			
			goods[i] = new Goods();
			goods[i].setName(name);
			goods[i].setPrice(price);
			goods[i].setCount(count);
		}
			
		for (Goods g : goods) {
			System.out.println(g.getName() + "(가격:"+ g.getPrice() + "원)이 " +
								g.getCount() + "개 입고 되었습니다.");
		}
		
		// 자원정리
		scanner.close();
	}
}
