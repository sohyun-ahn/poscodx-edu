package chapter03;

import mypackage.Goods2;

public class DiscountGoods2 extends Goods2 {
	private float discountRate = 0.5f;
	
	public float getDiscountPrice() {
		// Goods2에서 protected int price;로 선언되어 있음
		// protected는 자식에서 접근 가능
		return discountRate * price;
	}
}
