package prob5;

import java.lang.reflect.Array;

public class MyStack03<T> {
	
	private T[] buffer;
	private int top;
	
	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		top = -1;
		this.buffer = (T[]) new Object[capacity];
		//this.buffer = (T[]) Array.newInstance(klass,  capacity); // 런타임에 실행되는 코드는 안됨. generic은 컴파일 시간에 하니깐, 클래스가 메모리에 올라가 있어야 가

	}
	
	public void push(T s) {
		if (top == buffer.length - 1) {
			resize();
		}

		buffer[++top] = s;		
	}

	public T pop() throws MyStackException {
		if (isEmpty()) {
			throw new MyStackException("stack is empty");
		}

		T result = buffer[top];
		buffer[top--] = null;

		return result;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	private void resize() {
		T[] temp = (T[]) new Object[buffer.length * 2];
		for (int i = 0; i <= top; i++) {
			temp[i] = buffer[i];
		}

		buffer = temp;
	}
}