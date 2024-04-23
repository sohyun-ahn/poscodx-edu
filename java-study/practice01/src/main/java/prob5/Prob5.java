package prob5;

public class Prob5 {

	public static void main(String[] args) {
		for (int i=0;i<100;i++) {
			int a = i/10;
			int b = i%10;
			int count = 0;
			boolean clap = false;
			int[] condition = {3,6,9};
			
			// a,b에 3,6,9가 있는지 확인
			for (int e : condition) {
				// "짝"개수를 위한 조건문
				if(e == a || e == b) {
					clap = true;
					count++;
					if(e == a && e == b) {
						count++;
					}
				}
			}
			
			if(clap) {
				System.out.print(i+" ");
				System.out.println("짝".repeat(count));
			}
		}		
	}
}
