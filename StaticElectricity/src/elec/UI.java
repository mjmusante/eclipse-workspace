package elec;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BreezySwing.GBFrame;

@SuppressWarnings("serial")
public class UI extends GBFrame {
	
	JTextArea mainWin = addTextArea("", 1, 1, 5, 6);
	
	JTextField employeeNameInputField = addTextField("", 9, 1, 1, 1);
	JTextField employeeAgeInputField = addTextField("", 9, 2, 1, 1);
	JTextField studentNameInputField = addTextField("", 9, 3, 1, 1);
	JTextField studentAgeInputField = addTextField("", 9, 4, 1, 1);
	JTextField studentGradeInputField = addTextField("", 9, 5, 1, 1);
	
	JLabel l[] = {
			addLabel("Employee Name", 8, 1, 1, 1),
			addLabel("Employee Age", 8, 2, 1, 1),
			addLabel("Student Name", 8, 3, 1, 1),
			addLabel("Student Age", 8, 4, 1, 1),
			addLabel("Student Grade", 8, 5, 1, 1)
	};
	
	JButton employeeButton = addButton("Add Employee", 10, 1, 1, 1);
	JButton studentButton = addButton("Add Student", 10, 3,1, 1);
	
	JButton allEmployees = addButton("All Employees", 11, 1, 1, 1);
	JButton allStudents = addButton("All Students", 11, 2, 1, 1);
	JButton showCounts = addButton("Show Counts", 11, 3, 1, 1);
	
	JTextField showByAgeInputField = addTextField("", 12, 1, 1, 1);
	JButton showByAgeButton = addButton("Show by age", 12, 2, 1, 1);

	JButton exitButton = addButton("Exit", 12, 5, 1, 1);

	private void insertEmployee() {
		String name = employeeNameInputField.getText();
		String ageString = employeeAgeInputField.getText();
		int age;
		
		try {
			age = Integer.parseInt(ageString);
			Employee e = new Employee(name, age);
			mainWin.setText("Employee " + e + " entered");
			employeeNameInputField.setText("");
			employeeAgeInputField.setText("");
		} catch (EmployeeTooYoungException e) {
			mainWin.setText("Employee age of " + ageString + " is too young");
		} catch (Exception e) {
			mainWin.setText("Invalid age specified");
		}
	}

	private void insertStudent() {
		String name = studentNameInputField.getText();
		String ageString = studentAgeInputField.getText();
		String gradeString = studentGradeInputField.getText();
		int age, grade;
		
		try {
			age = Integer.parseInt(ageString);
			grade = Integer.parseInt(gradeString);
			Student s = new Student(name, age, grade);
			mainWin.setText("Student  " + s + " entered");
			studentNameInputField.setText("");
			studentAgeInputField.setText("");
			studentGradeInputField.setText("");
		} catch (StudentTooOldException e) {
			mainWin.setText("Student age of " + ageString + " is too old");
		} catch (Exception e) {
			mainWin.setText("Invalid age or grade");
		}
	}

	private void showEmployees() {
		String result = "";
		List<Employee> list = Employee.getAll();
		
		if (list.size() == 0) {
			mainWin.setText("There are no employees");
			return;
		}
		
		for (Employee e : list) {
			result = result + e + "\n";
		}
		
		mainWin.setText(result);
	}
	
	private void showStudents() {
		String result = "";
		List<Student> list = Student.getAll();
		
		if (list.size() == 0) {
			mainWin.setText("There are no students");
			return;
		}
		
		for (Student s : list) {
			result = result + s + "\n";
		}
		
		mainWin.setText(result);
	}
	
	private void showCounts() {
		mainWin.setText("Employee count: " + Employee.count() + "\n" +
				"Student count: " + Student.count());
	}
	
	private void showByAge() {
		try {
			int age = Integer.parseInt(showByAgeInputField.getText());
			String result = "Search results:\n";
			boolean found;
			
			List<Employee> elist = Employee.byAge(age);
			found = elist.size() > 0;
			for (Employee e : elist) {
				result += "\t" + e + "\n";
			}
			List<Student> slist = Student.byAge(age);
			found = found || slist.size() > 0;
			for (Student s : slist) {
				result += "\t" + s + "\n";
			}
			
			if (!found) {
				result += "\t<No results found>";
			}
			
			mainWin.setText(result);
		} catch (Exception e) {
			mainWin.setText("Invalid search age");
		}
	}
	
	public void buttonClicked(JButton b) {
		if (b == employeeButton) {
			insertEmployee();
		} else if (b == studentButton) {
			insertStudent();
		} else if (b == allEmployees) {
			showEmployees();
		} else if (b == allStudents) {
			showStudents();
		} else if (b == showCounts) {
			showCounts();
		} else if (b == showByAgeButton) {
			showByAge();
		} else if (b == exitButton) {
			System.exit(0);
		}
	}
	
	public static void main(String[] argv) {
		UI ui = new UI();
		
		ui.setTitle("Employees and Students");
		ui.setSize(800, 600);
		ui.setVisible(true);
		ui.employeeNameInputField.requestFocus();

	}
}
