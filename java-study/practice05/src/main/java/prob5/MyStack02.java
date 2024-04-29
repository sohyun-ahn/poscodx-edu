package prob5;

public class MyStack02 {
	
	private Object[] stack;
	private int lastIndex;
	
	public MyStack02(int num) {
		this.stack = new String[num];
		lastIndex = 0;
	}
	
	public void push(Object item) {
		if (lastIndex >= stack.length) {
			resize();
			this.stack[lastIndex] = item;
			lastIndex++;
		} else {
			this.stack[lastIndex] = item;
			lastIndex++;
		}
	}
	
	public Object pop() throws MyStackException {
		Object result = null;	
		if (lastIndex > 0) {
			result =  this.stack[lastIndex-1];	
			this.stack[lastIndex-1] = null;
			lastIndex--;
		} else {
			throw new MyStackException("stack is empty");
		}
		return result;
	}
	
	public boolean isEmpty() {
		if(this.stack[0] == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void resize() {
		// stack 이 꽉 찰 경우, 2배로 resize
		Object[] tempStack = new String[stack.length*2];
		for(int i=0; i<stack.length;i++) {
			tempStack[i] = stack[i];
		}
		this.stack = tempStack;
	}
}