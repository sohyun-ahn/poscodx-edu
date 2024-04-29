package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		String s1 = "Hello " + "World" + " java" + 17;
		String s2 = new StringBuffer("Hello ").append("World")
				         .append(" java").append(17).toString();
		String s3 = new StringBuilder("Hello ").append("World")
		         .append(" java").append(17).toString();
		
		String s4 = "";
		for (int i=0; i< 1000000; i++) {
//			s4 += "h";
//			s4 = new StringBuffer(s4).append("h").toString(); // cpu는 메모리에 로드할 때 제일 느림
		}
		
		StringBuffer sb = new StringBuffer("");
		for (int i=0; i< 1000000; i++) {
			sb.append("h");
		}
		String s5 = sb.toString();
		
		System.out.println(s1);
		System.out.println(s2); // s1은 사실 s2처럼 자동으로 해주는 것.
		System.out.println(s3);
		
		// String method들...
		String s6 = "aBcABCabcABC";
		System.out.println(s6.length()); // 12
		System.out.println(s6.charAt(2)); // c
		System.out.println(s6.indexOf("abc")); // 6
		System.out.println(s6.indexOf("abc", 7)); // -1
		System.out.println(s6.substring(3)); // ABCabcABC
		System.out.println(s6.substring(3,5)); // AB
		
		String s7 = "     ab         cd  ";
		String s8 = "efg,hij,klm,nop,qrs";
		
		String s9 = s7.concat(s8);
		System.out.println(s7);
		
		System.out.println("-------" + s7.trim() + "--------");
		System.out.println("-------" + s7.replaceAll(" ","") + "--------");
	
		String[] tokens = s8.split(",");
		for (String s: tokens) {
//			Output: 
//			efg
//			hij
//			klm
//			nop
//			qrs
			System.out.println(s);
		}
		
		String[] tokens2 = s8.split(" ");
		for (String s: tokens2) { 
			System.out.println(s); // efg,hij,klm,nop,qrs 한번에 출력됨. 길이 1
		}
		
		
	}
}
