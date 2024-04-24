package chapter03;

import java.util.Arrays;

public class ArrayUtilTest {

	public static void main(String[] args) {
		int[] a = {10, 20, 30, 40};
		
		double[] d = ArrayUtil.intToDouble(a);
		System.out.println(d); // output : [D@251a69d7
		System.out.println(Arrays.toString(d)); // output : [10.0, 20.0, 30.0, 40.0]


	}

}
