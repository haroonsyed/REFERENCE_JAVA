package me.Sshawarma.IM_SERVER;

//Imports
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {
	
	/*
	 * THE CLIENT APPLICATION IS IN A SEPARATE JAVA PROJECT
	 * 
	 * 
	 */
	
	//GUI STUFF
	private JTextField userText;
	private JTextArea chatWindow;
	//STREAMS
	private ObjectOutputStream output;
	private ObjectInputStream input;
	//Socket
	private ServerSocket server;
	private Socket connection;
	
	public Server(){
		
		//Make the GUI
		super("Instant Messager");
		userText = new JTextField();
		//Unless connected don't allow messages to send(into void)
		userText.setEditable(false);
		//Add listener
		userText.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						sendMessage(event.getActionCommand());
						//Sets the user text back to "" after a message is sent
						userText.setText("");
						
					}
				}
		);
		
		//Add to display and setup gui space
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow));
		setSize(300, 150);
		setVisible(true);
		
	}
	
	public void start() {
		
		try {
			
			//Setup the server socket
			//Only want a certain amount of people to connect(100). Called backlog/queuelength
			server = new ServerSocket(7689, 100);
			boolean keepRunning = true;
			while(keepRunning) {
				
				//Main Server loop
				try {
					
					waitForConnection();
					setupStreams();
					whileChatting();
					
				}catch(EOFException eofException) {
					//Inidicated end of a stream
					showMessage(" \n Server ended connection! ");
				}finally {
					//Finally always executes after a try block
					close();
				}
				
				
			}
			
			
		}catch(IOException ioException) {ioException.printStackTrace();}
		
		
		
	}
	
	//Wait for connection, then show who connected
	private void waitForConnection() throws IOException{
		
		showMessage(" Waiting for someone to connect... ");
		//Returns socket when someone connects, else blocks thread and waits
		connection = server.accept();
		showMessage(" Now connected to " + connection.getInetAddress().getHostName());
		
	}
	
	//sets up the streams
	private void setupStreams() throws IOException{
		
		//Pathway to connect to other computer
		output = new ObjectOutputStream(connection.getOutputStream());
		//Remove anything remaining in the buffer
		output.flush();
		
		//Same process, no flush because flush is on output stream
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
		
	}
	
	private void whileChatting() throws IOException {
		
		String message = " You are now connected! ";
		sendMessage(message);
		ableToType(true);
		do {
			//have a conversation
			try {
				
				//Get what the user is sending, **Figure otu why it wouldnt print a  bunch of empty lines
				message = (String) input.readObject();
				showMessage("\n" + message);
				
			}catch(ClassNotFoundException classNotFoundException) {showMessage("\n Message type could not be understood! ");};
			
		}while(!message.equals("CLIENT: END"));
		
	}
	
	private void close() {
		
		showMessage("\n Closing connections... \n");
		ableToType(false);
		try {
			
			output.close();
			input.close();
			connection.close();
			
		}catch(IOException ioException) {
			ioException.printStackTrace();
		}
		
	}
	
	//Sends a message to client
	private void sendMessage(String msg) {
		
		try {
			
			output.writeObject("SERVER: " + msg);
			//Just in case stuff is still left in the buffer
			output.flush();
			showMessage("\nSERVER: " + msg);
			
		}catch(IOException ioException) {
			chatWindow.append("\n ERROR: Coudln't send the message!");
		}
		
	}
	
	//Displays a message to server
	//msg needs to be final because anonymous classes can only see final
	private void showMessage(final String msg) {
		
		//Use separate thread to update the GUI
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatWindow.append(msg);
				}
			}
		);
		
	}
	
	private void ableToType(final boolean aTT) {
		
		//Use separate thread to update the GUI
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					userText.setEditable(aTT);
				}
			}
		);
		
	}
	
	
}
