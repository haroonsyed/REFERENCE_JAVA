package me.Sshawarma.Main;

import java.util.Random;

//Runnable interface contains the run() method
public class ThreadObj implements Runnable {
	
	//Just the properties about these threads
	String name;
	int time;
	Random r = new Random();
	
	ThreadObj(String s){
		
		name = s;
		time = r.nextInt(999);	//Random time btwn now and 0 and 999 ms
		
	}

	//Here is where the thread behavior is defined
	@Override
	public void run() {
		
		try {
			
			System.out.printf("%s is sleeping for %d\n", name, time);
			Thread.sleep(time);
			System.out.printf("%s has woken up\n", name);
			
		}
		//Catch all
		catch(Exception e) {
			
		}
		
	}
	
	
}
