package elec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void testNoEmployees() {
		assertEquals(Employee.count(), 0);
	}
	
	@Test
	public void testOneEmployee() throws Exception {
		Employee e = new Employee("Joe Schmoe", 25);
		
		assertEquals(Employee.count(), 1);
		e.terminate();
	}

	@Test
	public void testEmployeeCleanup() throws Exception {
		Employee e = new Employee("Joe Schmoe", 25);
		e.terminate();
		assertEquals(Employee.count(), 0);
	}
	
	@Test(expected = EmployeeTooYoungException.class)
	public void testEmployeeAgeException() throws Exception {
		Employee e = new Employee("Harry Potter", 11);
		e.terminate();
	}
	
	@Test(expected = EmployeeTooYoungException.class)
	public void testEmployeeJustUnderAge() throws Exception {
		Employee e = new Employee("Percy Weasley", 15);
		e.terminate();
	}
	
	@Test
	public void testEmployeeCountIsZeroIfTooYoung() {
		Employee e = null;
		
		try {
			e = new Employee("Draco Malfoy", 11);
			e.terminate();
		} catch (EmployeeTooYoungException ex) {
			assertEquals(Employee.count(), 0);
		}
		
		assertNull(e);
	}
	
	@Test
	public void testEmployeeAsString() throws Exception {
		Employee e = new Employee("foo", 42);
		
		assertEquals("show " + e, "show foo, age 42");
		e.terminate();
	}
	
	@Test
	public void testGetAllOfNone() {
		List<Employee> list = Employee.getAll();
		
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testGetAllOfOne() throws Exception {
		Employee e = new Employee("foo", 21);
		assertEquals(Employee.getAll().size(), 1);
		e.terminate();
	}

	@Test
	public void testSearchForEmployeesByAge() throws Exception {
		Employee[] e = {
				new Employee("Adam", 18),
				new Employee("Barbara", 19),
				new Employee("Charlie", 20),
				new Employee("Donna", 20),
				new Employee("Eddie", 21)
		};
		
		assertEquals(Employee.byAge(18).size(), 1);
		assertEquals(Employee.byAge(19).size(), 1);
		assertEquals(Employee.byAge(20).size(), 2);
		assertEquals(Employee.byAge(21).size(), 1);
		
		for (Employee x : e) {
			x.terminate();
		}
	}
}
