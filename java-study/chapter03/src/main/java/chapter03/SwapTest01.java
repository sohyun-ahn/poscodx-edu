package chapter03;

public class SwapTest01 {

	public static void main(String[] args) {
		int i = 10;
		int j = 20;
		
		System.out.println(i + "," + j);
		
        // i, j 바꾸기
		int temp = i;
		i = j;
		j = temp;
		
		System.out.println(i + "," + j);

	}
	
	

}
