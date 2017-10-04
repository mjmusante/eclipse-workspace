package sequence;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BreezySwing.GBFrame;


@SuppressWarnings("serial")
public class UI extends GBFrame {
	
	JTextArea mainWin = addTextArea("", 1, 1, 5, 6);
	JLabel lab1 = addLabel("Enter numbers separated by spaces or commas", 7, 2, 3, 1);
	JLabel lab2 = addLabel("", 7, 1, 1, 1);
	JTextField inputField = addTextField("", 8, 2, 2, 1);
	JButton inputButton = addButton("Input", 8, 4, 1, 1);
	JButton outputButton = addButton("Output", 9, 2, 1, 1);
	JButton resetButton = addButton("Reset", 9, 3, 1, 1);
	JButton exitButton = addButton("Exit", 9, 4, 1, 1);
	
	Sequencer curSeq = new Sequencer();
	List<Sequencer> curResult = null;
	String errMsg = null;
	
	public UI() {
		mainWin.setEditable(false);
		
		inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					getSequence();
					outputResult();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent ke) {}
			
			@Override
			public void keyTyped(KeyEvent ke) {}
		});
	}
	
	private void outputResult() {
		String output = "";
		
		if (errMsg != null) {
			output = output + errMsg + "\n";
			errMsg = null;
		}
		
		output = output + "Current input: ";
		if (curSeq.size() == 0) {
			output = output + "<empty>";
		} else {
			output = output + curSeq.prettyPrint();
		}
		
		if (curResult != null) {
			output = output + "\n\nLongest sequence";
			if (curResult.size() > 1) {
				output = output + "s";
			}
			output = output + ": ";
			
			if (curResult.size() == 0) {
				output = output + "<none>";
			} else {
				for (int i = 0; i < curResult.size(); i++) {
					Sequencer seq = curResult.get(i);
					output = output + seq.prettyPrint() + " ";
				}
			}
		}
		mainWin.setText(output);
		inputField.requestFocus();
	}
	
	public void reset() {
		curSeq = new Sequencer();
		curResult = null;
		
		outputResult();		
	}
	
	private void scanSequence(String input) {
		String[] a = input.split("[ ,]");
		for (String s : a) {
			try {
				if (s.length() > 0)
					curSeq.add(Integer.parseInt(s));
			}
			catch (Exception e) {
				errMsg = "ERROR: input '" + s + "' is not an integer";
				break;
			}
		}
		curResult = null;
	}
	
	public void buttonClicked(JButton b) {
		if (b == exitButton) {
			System.exit(0);
		} else if (b == inputButton) {
			getSequence();
		} else if (b == resetButton) {
			reset();
		} else if (b == outputButton) {
			curResult = curSeq.longest();
		}
		outputResult();
	}

	private void getSequence() {
		scanSequence(inputField.getText());
		inputField.setText("");
	}
	
	public static void main(String[] argv) {
		
		UI frm = new UI();
		
		frm.reset();
		
		frm.setTitle("Longest Sequence");
		frm.setSize(800, 600);
		frm.setVisible(true);
		frm.inputField.requestFocus();
	}
}
