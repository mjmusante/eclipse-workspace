package sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Sequencer {
	
	ArrayList<Integer> array;
	
	public Sequencer() {
		array = new ArrayList<Integer>();
	}
	
	public Sequencer(List<Integer> list) {
		array = new ArrayList<Integer>();
		array.addAll(list);
	}
	
	public void add(List<Integer> ary) {
		array.addAll(ary);
	}
	
	public void add(int x) {
		array.add(x);
	}
	
	public List<Integer> get() {
		return array;
	}
	
	public void reset() {
		array = new ArrayList<Integer>();
	}
	
	public int size() {
		return array.size();
	}
	
	private void checkAndAdd(List<Sequencer> result, List<Integer> latest) {
		if (result.size() == 0 || result.get(0).size() == latest.size()) {
			result.add(new Sequencer(latest));
		} else if (result.get(0).size() < latest.size()) {
			result.clear();
			result.add(new Sequencer(latest));
		}
	}
	
	public List<Sequencer> longest() {
		List<Sequencer> result = new ArrayList<Sequencer>();
		
		if (array.size() == 0)
			return result;
	
		// prep the loop with the first entry in the array
		int last = array.get(0);
		List<Integer> intlist = new ArrayList<Integer>();
		intlist.add(last);
		
		// loop over the remaining entries and find the longest lists
		for (int cur : array) {
			if (last >= cur) {
				if (intlist.size() > 1) {
					checkAndAdd(result, intlist);
				}
				intlist = new ArrayList<Integer>();
			}
			last = cur;
			intlist.add(cur);
		}
		if (intlist.size() > 1) {
			checkAndAdd(result, intlist);
		}
		return result;
	}
	
	public String prettyPrint() {
		StringJoiner join = new StringJoiner(", ");
		
		for (int i : array) {
			join.add("" + i);
		}
		
		return "{" + join.toString() + "}";
	}
	
	public static void main(String[] argv) {
		Sequencer foo = new Sequencer();
		
		// test 1: empty
		assert(foo.get().size() == 0);
		assert(foo.longest().size() == 0);
		assert(foo.prettyPrint().equals("{}"));
		System.out.println("a");	
		
		// test 1a: one element
		foo.add(Arrays.asList(1));
		assert(foo.longest().size() == 0);
		assert(foo.prettyPrint().equals("{1}"));
		System.out.println("a1");	
		
		// test 2: add some entries and ensure they're there
		foo.add(Arrays.asList(2, 3));
		List<Integer> bar = foo.get();
		assert(bar.get(0) == 1);
		assert(bar.get(1) == 2);
		assert(bar.get(2) == 3);
		assert(foo.longest().size() == 1);
		assert(foo.prettyPrint().equals("{1, 2, 3}"));
		List<Sequencer> baz = foo.longest();
		for (Sequencer seq : baz) {
			assert(seq.get().get(0) == 1);
			assert(seq.get().get(1) == 2);
			assert(seq.get().get(2) == 3);
		}
		System.out.println("b");
		
		// test 3: add some more entries and ensure they're both returned
		foo.add(Arrays.asList(2, 3, 4));
		bar = foo.get();
		assert(bar.get(0) == 1);
		assert(bar.get(1) == 2);
		assert(bar.get(2) == 3);
		assert(bar.get(3) == 2);
		assert(bar.get(4) == 3);
		assert(bar.get(5) == 4);
		assert(foo.prettyPrint().equals("{1, 2, 3, 2, 3, 4}"));
		assert(foo.longest().size() == 2);
		baz = foo.longest();
		assert(baz.get(0).get().get(0) == 1);
		assert(baz.get(0).get().get(1) == 2);
		assert(baz.get(0).get().get(2) == 3);
		assert(baz.get(1).get().get(0) == 2);
		assert(baz.get(1).get().get(1) == 3);
		assert(baz.get(1).get().get(2) == 4);
		System.out.println("c");
		
		// test 4: add a longer sequence and ensure only it is returned
		foo.add(Arrays.asList(3, 11, 12, 13));
		assert(foo.prettyPrint().equals("{1, 2, 3, 2, 3, 4, 3, 11, 12, 13}"));
		baz = foo.longest();
		assert(baz.size() == 1);
		assert(baz.get(0).size() == 4);
		assert(baz.get(0).get().get(0) == 3);
		assert(baz.get(0).get().get(3) == 13);
		System.out.println("d");
	}
}
