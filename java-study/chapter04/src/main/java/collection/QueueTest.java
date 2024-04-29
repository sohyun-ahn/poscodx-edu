package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("마이콜");
		q.offer("둘리");
		q.offer("또치");
		
		// FIFO
		while(!q.isEmpty()) {
			String s = q.poll();
			System.out.println(s);
		}
		
		// 비어있는 경우 null을 반환한다
		System.out.println(q.poll()); // null
		

		q.offer("마이콜");
		q.offer("둘리");
		q.offer("또치");
		
		System.out.println(q.poll());  // 마이콜
		System.out.println(q.peek());  // 둘리
		System.out.println(q.poll());  // 둘리
	
	}

}
