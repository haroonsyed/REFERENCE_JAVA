package me.Sshawarma.Main;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

//SWING (OUTDATED NOW AND CLUNKY, BUT HERE FOR REFERENCE)
//package me.Sshawarma.Main;
////First import the swing library
//import javax.swing.JOptionPane;
//
//public class Main {
//
//public static void main(String[] args) {
//	
//	
//	String fn = JOptionPane.showInputDialog("Enter first number");
//	String sn = JOptionPane.showInputDialog("Enter second number");
//	
//	int n1 = Integer.parseInt(fn);
//	int n2 = Integer.parseInt(sn);
//	
//	int sum = n1 + n2;
//	
//	//Now we want ot display the result)
//	JOptionPane.showMessageDialog(null, "The answer is " + sum, "SUM_CALCULATOR:", JOptionPane.PLAIN_MESSAGE);
//	
//	
//}
//
//}

//JFRAME IS ALSO OUTDATED. LEARN JAVAFX
public class Window extends JFrame {
	
	private JLabel item1;
	
	public Window() {
		super("The titlebar");
		setLayout(new FlowLayout());
		item1 = new JLabel("This is a sentense");
		
		//Now we can have tooltiptext
		item1.setToolTipText("I am an item");
		//Now add the item to the window
		add(item1);
		
		//Tell program how to close. Otherwise program won't close right.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Now set size
		setSize(275, 180);
		//Set visible
		setVisible(true);
		
	}
	
}
