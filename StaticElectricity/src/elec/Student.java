package elec;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private static List<Student> list = new ArrayList<Student>();
	private static final int MAX_AGE = 21;
	
	private String name;
	private int age;
	private int grade;

	public Student(String name, int age, int grade) throws StudentTooOldException {
		
		validateAge(age);
		
		this.name = name;
		this.age = age;
		this.grade = grade;
		
		list.add(this);
	}
	
	public static void validateAge(int age) throws StudentTooOldException {
		if (age > MAX_AGE)
			throw new StudentTooOldException();
	}

	public static int count() {
		return list.size();
	}
	
	public static List<Student> getAll() {
		return list;
	}
	
	public static List<Student> byAge(int age) {
		List<Student> ret = new ArrayList<Student>();
		for (Student s : list) {
			if (s.age == age) {
				ret.add(s);
			}
		}
		
		return ret;
	}

	
	public void transfer() {
		list.remove(this);
	}
	
	public String toString() {
		return name + ", age " + age + ", grade " + grade;
	}
}
