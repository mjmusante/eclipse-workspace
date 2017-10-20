package elec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testNoStudents() {
		assertEquals(Student.count(), 0);
	}

	@Test
	public void testOneStudent() throws Exception {
		Student s = new Student("Adam Adamson", 12, 7);
		assertEquals(Student.count(), 1);
		s.transfer();
	}
	
	@Test
	public void testStudentCleanup() throws Exception {
		Student s = new Student("Brian O'Brien", 6, 1);
		s.transfer();
		assertEquals(Student.count(), 0);
	}
	
	@Test(expected = StudentTooOldException.class)
	public void testStudentTooOld() throws Exception {
		Student s = new Student("Christopher Colombus", 22, 15);
		s.transfer();
	}
	
	@Test
	public void testStudentCountIsZeroIfTooOld() {
		Student s = null;
		
		try {
			s = new Student("Donald Duck", 31, 99);
			s.transfer();
		} catch (StudentTooOldException ex) {
			assertEquals(Student.count(), 0);
		}
		
		assertNull(s);
	}
	
	@Test
	public void testStudentAsString() throws Exception {
		Student s = new Student("bar", 12, 12);
		assertEquals("show " + s, "show bar, age 12, grade 12");
		s.transfer();
	}
	
	@Test
	public void testGetAllOfNone() {
		List<Student> list = Student.getAll();
		
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testGetAllOfOne() throws Exception {
		Student e = new Student("foo", 12, 1);
		assertEquals(Student.getAll().size(), 1);
		e.transfer();
	}

	@Test
	public void testSearchForStudentsByAge() throws Exception {
		Student[] s = {
				new Student("Adam", 8, 1),
				new Student("Barbara", 9, 1),
				new Student("Charlie", 10, 1),
				new Student("Donna", 10, 1),
				new Student("Eddie", 11, 1)
		};
		
		assertEquals(Student.byAge(8).size(), 1);
		assertEquals(Student.byAge(9).size(), 1);
		assertEquals(Student.byAge(10).size(), 2);
		assertEquals(Student.byAge(11).size(), 1);
		
		for (Student x : s) {
			x.transfer();
		}
	}

}
