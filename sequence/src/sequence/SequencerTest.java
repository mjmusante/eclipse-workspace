package sequence;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequencerTest {

	@Test
	public void testEmptySequencer() {
		Sequencer seq = new Sequencer();
		assertEquals(seq.longest(), 0);
	}

	@Test
	public void testOneElement() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1});
		assertEquals(seq.longest(), 0);
		assertEquals(seq.prettyPrint(), "{1}");
	}

	@Test
	public void testTwoElements() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1, 2});
		assertEquals(seq.longest(), 2);
		assertEquals(seq.prettyPrint(), "{1, 2}");
	}
	
	@Test
	public void testAddingElements() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1});
		seq.add(new int[] {2,3});
		assertEquals(seq.longest(), 3);
		assertEquals(seq.prettyPrint(), "{1, 2, 3}");
	}
	
	@Test
	public void testFindingOneSequence() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1, 2});
		assertEquals(seq.numOfLength(1), 0);
		assertEquals(seq.numOfLength(2), 1);
	}
	
	@Test
	public void testNoSingleDigitSequence() {
		Sequencer seq = new Sequencer();
		
		seq.add(1);
		assertEquals(seq.numOfLength(1), 0);
	}
	
	@Test
	public void testGenerateZeroLength() {
		Sequencer seq = new Sequencer();
		
		Sequencer[] found = seq.findLongestList();
		assertEquals(found.length, 0);
	}
	
	@Test
	public void testGenerateSingleSequencer() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1, 2});
		Sequencer[] found = seq.findLongestList();
		
		assertEquals(found.length, 1);
		assertEquals(found[0].prettyPrint(), seq.prettyPrint());
	}
	
	@Test
	public void testGenerateDualSequencer() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1, 2, 3, 2, 3, 4});
		Sequencer[] found = seq.findLongestList();
		
		assertEquals(found.length, 2);
		assertEquals(found[0].prettyPrint(), "{1, 2, 3}");
		assertEquals(found[1].prettyPrint(), "{2, 3, 4}");
	}
	
	@Test
	public void testLongerSequencer() {
		Sequencer seq = new Sequencer();
		
		seq.add(new int[] {1, 2, 3, 2, 3, 4});;
		Sequencer[] found = seq.findLongestList();
		assertEquals(found.length, 2);
		
		seq.add(new int[] {0, 1, 2, 3});
		found = seq.findLongestList();
		assertEquals(found.length, 1);
		assertEquals(found[0].prettyPrint(), "{0, 1, 2, 3}");
	}
}
