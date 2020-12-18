package me.Sshawarma.Main;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;


public class EventHandling extends JFrame{

	private JTextField TF1;
	private JTextField TF2;
	private JTextField TF3;
	private JPasswordField pF;
	
	//Create window
	public EventHandling() {
		super("EventHandling Tutorial");
		setLayout(new FlowLayout());
		
		//Now that the window is setup, create the text fields
		TF1 = new JTextField(10);
		add(TF1);
		TF2 = new JTextField("enter test here");
		add(TF2);
		TF3 = new JTextField("uneditable", 20);
		TF3.setEditable(false);
		add(TF3);
		
		pF = new JPasswordField("mypass");
		add(pF);
		
		//Add listener
		handler handler = new handler();
		TF1.addActionListener(handler);
		TF2.addActionListener(handler);
		TF3.addActionListener(handler);
		pF.addActionListener(handler);
		
	}
	
	//Nested class
	private class handler implements ActionListener {
		//Implementing action listener is very important
		public void actionPerformed(ActionEvent event) {
			
			String s = "";
			
			//Test if even has fired
			if(event.getSource() == TF1) {
				s = String.format("field 1: %s", event.getActionCommand());//get action command grabs the text in the field
			}
			else if(event.getSource() == TF2) {
				s = String.format("field 2: %s", event.getActionCommand());
			}
			else if(event.getSource() == TF3) {
				s = String.format("field 3: %s", event.getActionCommand());
			}
			else if(event.getSource() == pF) {
				s = String.format("password field: %s", event.getActionCommand());
			}
			
			//Now set string
			JOptionPane.showMessageDialog(null, s);
			
		}
		
	}
	
	
}
