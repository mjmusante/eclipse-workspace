import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import BreezySwing.DoubleField;
import BreezySwing.GBFrame;

class Employee {
	
	String name;
	double q1, q2, q3, q4;
	
	public Employee(String name, double q1, double q2, double q3, double q4) {
		this.name = name;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
	}
	
	public String getName() { return this.name; }
	public double getq1() { return this.q1; }
	public double getq2() { return this.q2; }
	public double getq3() { return this.q3; }
	public double getq4() { return this.q4; }
	
	public double totalSales() { return q1 + q2 + q3 + q4; }
}

@SuppressWarnings("serial")
public class AceWidget extends GBFrame {

	JLabel nameLabel = addLabel("Name", 1, 1, 1, 1);
	JTextField nameText = addTextField("", 1, 2, 1, 1);
	
	JLabel q1Label = addLabel("Q1 Sales", 2, 1, 1, 1);
	DoubleField q1Num = addDoubleField(0, 2, 2, 1, 1);
	JLabel q2Label = addLabel("Q2 Sales", 3, 1, 1, 1);
	DoubleField q2Num = addDoubleField(0, 3, 2, 1, 1);
	JLabel q3Label = addLabel("Q3 Sales", 4, 1, 1, 1);
	DoubleField q3Num = addDoubleField(0, 4, 2, 1, 1);
	JLabel q4Label = addLabel("Q4 Sales", 5, 1, 1, 1);
	DoubleField q4Num = addDoubleField(0, 5, 2, 1, 1);
	
	JButton addButton = addButton("Add New Employee", 6, 1, 2, 1);
	
	public void buttonClicked(JButton button) {
		if (button == addButton) {
			messageBox("New Employee Added!");
		}
	}
	// Create and display the window when the app launches
	public static void main(String[] args) {
		JFrame frm = new AceWidget();
		frm.setTitle("Ace Widget Employee Database");
		frm.setSize (640, 480);
		frm.setVisible (true);
	}

}
