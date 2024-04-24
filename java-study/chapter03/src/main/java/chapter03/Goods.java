package chapter03;

public class Goods {
	public static int countOfGoods = 0; 
	
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public Goods() {
		// == Goods.countOfGoods++ (className생략가능)
		countOfGoods++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if(price < 0) {
			price = 0;
		}
		
		this.price = price;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	public void showInfo() {
		System.out.println(
				"상품이름: " + getName() + ", " +
				"가격: " + getPrice() + ", " +
				"재고 개수: " + getCountSold() + ", " +
				"팔린 개수: " + getCountSold() 
				);		
	}
	
	public int calcDiscountPrice(float percentage) {
		int result = price - (int) (price * percentage);
		return result;
	}
	
}
