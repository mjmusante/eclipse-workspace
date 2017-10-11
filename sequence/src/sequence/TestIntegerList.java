package sequence;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestIntegerList {
	
	@Test
	public void testCreate() {
		IntegerList il = new IntegerList();
		assertTrue(il.size() == 0);
	}
	
	@Test
	public void testAddOne() {
		IntegerList il = new IntegerList();
		il.add(5);
		assertTrue(il.size() == 1);
		assertTrue(il.get(0) == 5);
	}

	@Test
	public void testLargeList() {
		
		IntegerList seq = new IntegerList();
		for (int i = 0; i < 100; i++) {
			seq.add(i);
		}
		assertTrue(seq.size() == 100);
		
		int count = 0;
		for (int i: seq) {
			assertTrue(i == seq.get(i));
			++count;
		}
		assertTrue(count == seq.size());
	}
	
	@Test
	public void testAddAllFromArrray() {
		
		int[] foo = {1, 2, 3, 4, 5};
		IntegerList il2 = new IntegerList();
		il2.addAll(foo);
		assertTrue(il2.size() == 5);
		for (int i = 0; i < foo.length; i++) {
			assertTrue(il2.get(i) == foo[i]);
		}
	}
	
	@Test
	public void testAddAllFromAnotherList() {
		
		IntegerList first = new IntegerList(new int[] {0, 1, 2, 3});
		IntegerList second = new IntegerList(new int[] {4, 5, 6});
		
		int s1 = first.size();
		int s2 = second.size();
		
		first.addAll(second);
		
		assertTrue(first.size() == s1 + s2);
		
		for (int i: first) {
			assertTrue(i == first.get(i));
		}
	}

}
