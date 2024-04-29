package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E> {
	private E[] datas = null;
	
	public AggregateImpl(E[] datas) {
		this.datas = datas;
	}
	
	@Override
	public Iterator<E> createIterator() {
		return null;
	}
	
	private class IteratorImpl implements Iterator<E>{
		int index = 0;
		
		// 외부에 클래스 만들지 않고, 내부에 만들기!!
		@Override
		public E next() {
			return index < datas.length ? datas[index++] : null;
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
		
	}
}
