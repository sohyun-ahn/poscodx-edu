package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		// 기본 생성자용
//		Goods camera = new Goods(); // new로 메모리에 로드, count=1
//		camera.setName("nikon");
//		camera.setPrice(400000);
//		camera.setCountSold(50);
//		camera.setCountStock(30);
		Goods camera = new Goods("nikon", 400000, 50, 30);
		
		// 정보은닉(데이터보호)
		camera.setPrice(-40000); // set price = 0
		
		// static 변수(클래스 변수)
		Goods goods2 = new Goods();  // Goods()는 이미 로드했으니 참고해서 사용하고, count=2
		Goods goods3 = new Goods();  // count=3
		
		System.out.println(Goods.countOfGoods);
		
		camera.setPrice(400000);
		System.out.println(camera.calcDiscountPrice(0.5f));
		
		System.out.println(camera); // 8byte주소를 hashing된 값, output : chapter03.Goods@251a69d7
		
		camera.showInfo();	
	}

}
