package prob04;
public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		char [] cs  = str.toCharArray(); // 사용하기
		
		for (int i=0;i<cs.length;i++) {
			if(i == cs.length/2) {
				break;
			}
			
			char temp = cs[cs.length-i-1];
			cs[cs.length-i-1] = cs[i];
			cs[i] = temp;
		}
		
		return cs;
	}

	public static void printCharArray(char[] array){
		System.out.println( array );
	}
}