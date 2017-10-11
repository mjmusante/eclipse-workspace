package sequence;

import java.util.Iterator;

class IntegerListIterator implements Iterator<Integer> {

	int[] ary;
	int len;
	int pos = 0;
	
	public IntegerListIterator(int[] ary, int len) {
		this.ary = ary;
		this.len = len;
	}
	
	@Override
	public boolean hasNext() {
		return (pos < len);
	}
	
	@Override
	public Integer next() {
		return ary[pos++];
	}
}

public class IntegerList implements Iterable<Integer> {
	
	private static final int INCR = 16;
	
	private int[] ary = new int[INCR];
	private int count = 0;
	private int max = INCR;
	
	public IntegerList() {}
	
	public IntegerList(int[] list) {
		for (int i: list) {
			this.add(i);
		}
	}

	public int size() {
		return count;
	}
	
	public void add(int num) {
		if (count == max) {
			// need to grow array
			int[] newary = new int[max + INCR];
			System.arraycopy(ary, 0, newary, 0, max);
			ary = newary;
			max += INCR;
		}
		ary[count++] = num;
	}
	
	public void addAll(IntegerList list) {
		for (int i: list) {
			this.add(i);
		}
	}
	
	public void addAll(int[] list) {
		for (int i: list) {
			this.add(i);
		}
	}

	public int get(int pos) {
		return ary[pos];
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new IntegerListIterator(ary, count);
	}

}
