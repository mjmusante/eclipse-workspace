package elec;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private static final int MIN_AGE = 16;
	private static List<Employee> list = new ArrayList<Employee>();
	
	private String name;
	private int age;
	
	public Employee(String name, int age) throws EmployeeTooYoungException {
		validateAge(age);
		
		this.name = name;
		this.age = age;
		
		list.add(this);
	}
	
	public static int count() {
		return list.size();
	}
	
	public static List<Employee> getAll() {
		return list;
	}
	
	public static List<Employee> byAge(int age) {
		List<Employee> ret = new ArrayList<Employee>();
		for (Employee e : list) {
			if (e.age == age) {
				ret.add(e);
			}
		}
		
		return ret;
	}
	
	public static void validateAge(int age) throws EmployeeTooYoungException {
		if (age < MIN_AGE)
			throw new EmployeeTooYoungException();
	}

	public void terminate() {
		list.remove(this);
	}

	public String toString() {
		return name + ", age " + age;
	}
}
