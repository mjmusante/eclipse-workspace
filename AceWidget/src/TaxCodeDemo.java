import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import BreezySwing.DoubleField;
import BreezySwing.GBFrame;
import BreezySwing.IntegerField;

public class TaxCodeDemo extends GBFrame {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Set up the widgets by adding them to rows and columns in the window's grid
    JLabel incomeLabel           = addLabel ("Income",           1,1,1,1);
    DoubleField incomeField      = addDoubleField (0.0,          1,2,1,1);
    JLabel dependentsLabel       = addLabel ("Dependents",       2,1,1,1);
    IntegerField dependentsField = addIntegerField (0,           2,2,1,1);
    JLabel exemptionLabel        = addLabel ("Exemption amount", 3,1,1,1);
    DoubleField exemptionField   = addDoubleField (0.0,          3,2,1,1);
    JButton convertButton        = addButton ("Compute",         4,1,2,1);
    JLabel taxLabel              = addLabel ("Total tax",        5,1,1,1);
    DoubleField taxField         = addDoubleField (0.0,          5,2,1,1);
 
   // The event handler method for the button to compute the tax     
   public void buttonClicked(JButton buttonObj){
        double income = incomeField.getNumber();
        int numDependents = dependentsField.getNumber();
        double exemptionAmount = exemptionField.getNumber();
        double tax = (income - numDependents * exemptionAmount) * .15;
        taxField.setPrecision(2);
        taxField.setNumber(tax);
    }
 
   // Create and display the window when the app launches
   public static void main(String[] args){
      JFrame frm = new TaxCodeDemo();
      frm.setTitle("Tax Calculator");
      frm.setSize (300, 200);
      frm.setVisible (true);
   }
}