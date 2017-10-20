package sequence;

import java.util.StringJoiner;

public class Sequencer {

	IntegerList array;

	public Sequencer() {
		array = new IntegerList();
	}

	public Sequencer(IntegerList list) {
		array = new IntegerList();
		array.addAll(list);
	}

	public void add(IntegerList ary) {
		array.addAll(ary);
	}
	
	public void add(int[] list) {
		array.addAll(list);
	}

	public void add(int x) {
		array.add(x);
	}

	public IntegerList get() {
		return array;
	}

	public void reset() {
		array = new IntegerList();
	}

	public int size() {
		return array.size();
	}
	
	public int longest() {
		int result = 0;

		if (array.size() == 0)
			return result;
		
		int last = array.get(0) + 1;
		int len = 0;
		
		for (int cur: array) {
			if (last > cur) {
				if (len > 1 && len > result) {
					result = len;
				}
				len = 0;
			}
			last = cur;
			++len;
		}
		
		if (len > 1 && len > result)
			result = len;
		
		return result;
	}
	
	public int numOfLength(int findlen) {
		int result = 0;
		
		if (findlen < 2 || array.size() == 0)
			return result;
		
		int last = array.get(0) + 1;
		int len = 0;
		for (int cur: array) {
			if (last > cur) {
				if (len == findlen)
					++result;
				len = 0;
			}
			last = cur;
			++len;
		}
		
		if (len == findlen)
			++result;
		
		return result;
	}
	
	public Sequencer[] findLongestList() {
		int longest = longest();
		Sequencer[] result = new Sequencer[numOfLength(longest)];
		int pos = 0;
		
		if (longest < 2)
			return result;
		
		int last = array.get(0) + 1;
		int start = 0;
		int len = 0;
		
		for (int loc = 0; loc < array.size(); loc++) {
			int cur = array.get(loc);
			if (last > cur) {
				if (len == longest) {
					result[pos] = new Sequencer();
					for (int i = start; i < start + len; i++) {
						result[pos].add(array.get(i));
					}
					++pos;
				}
				len = 0;
				start = loc;
			}
			last = cur;
			++len;
		}
		if (len == longest) {
			result[pos] = new Sequencer();
			for (int i = start; i < start + len; i++) {
				result[pos].add(array.get(i));
			}
		}
		return result;
	}
	
	public String prettyPrint() {
		StringJoiner join = new StringJoiner(", ", "{", "}");

		for (int i : array) {
			join.add("" + i);
		}

		return join.toString();
	}
}
