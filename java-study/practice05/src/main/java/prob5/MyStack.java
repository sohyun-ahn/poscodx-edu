package prob5;

public class MyStack {
	
	private String[] stack;
	private int lastIndex;
	
	public MyStack(int num) {
		this.stack = new String[num];
		lastIndex = 0;
	}
	
	public void push(String item) {
		if (lastIndex >= stack.length) {
			resize();
			this.stack[lastIndex] = item;
			lastIndex++;
		} else {
			this.stack[lastIndex] = item;
			lastIndex++;
		}
	}
	
	public String pop() throws MyStackException {
		String result = null;	
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
		String[] tempStack = new String[stack.length*2];
		for(int i=0; i<stack.length;i++) {
			tempStack[i] = stack[i];
		}
		this.stack = tempStack;
	}
}