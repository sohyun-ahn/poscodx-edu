package chapter03;

import mypackage.Value;

public class SwapTest03 {

	public static void main(String[] args) {
		Value i = new Value(10);
		Value j = new Value(20);
		
		
		System.out.println(i.val + "," + j.val);
		
        // i, j 바꾸는 메소드
		swap(i, j);
		
		System.out.println(i.val + "," + j.val);

	}

	private static void swap(Value m, Value n) {
		int temp = m.val;
		m.val = n.val;
		n.val = temp;
	}
	
}
