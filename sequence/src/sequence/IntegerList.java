package sequence;

import java.util.Iterator;


class IntegerListIterator implements Iterator<Integer> {

	private int[] ary;
	private int len;
	private int pos = 0;
	
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
	
	/* tells us how much to increase the array size each time we hit the limit */
	private static final int INCR = 16;
	
	/* the array storage */
	private int[] ary;
	
	/* the number of valid elements in the array */
	private int count;
	
	/* the total number of elements in the array */
	private int max;
	
	/* construct an empty array */
	public IntegerList() {
		this.ary = new int[INCR];
		this.count = 0;
		this.max = INCR;
	}
	
	/* construct an array and populate it with an existing array */
	public IntegerList(int[] list) {
		this();
		for (int i: list) {
			this.add(i);
		}
	}

	/* return the number of valid entries in the array */
	public int size() {
		return count;
	}
	
	/* add one number to the end of the array */
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
	
	/* append one IntegerList to another */
	public void addAll(IntegerList list) {
		for (int i: list) {
			this.add(i);
		}
	}
	
	/* append an array of numbers to the array */
	public void addAll(int[] list) {
		for (int i: list) {
			this.add(i);
		}
	}

	/* get the value of the entry at a specific position in the array */
	public int get(int pos) {
		return ary[pos];
	}

	/* generate an iterator for the array */
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new IntegerListIterator(ary, count);
	}

}
