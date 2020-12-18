package me.Sshawarma.Main;

public class Threads {
	
	Threads(){
		
		//Create 4 threads
		Thread t1 = new Thread(new ThreadObj("One"));
		Thread t2 = new Thread(new ThreadObj("Two"));
		Thread t3 = new Thread(new ThreadObj("Three"));
		Thread t4 = new Thread(new ThreadObj("Four"));
		
		//Now start the threads
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		//Note that they all run concurrently, and there is no particular order to the code.
		
		
		//Doing this, however, is incorrect and will simply run sequentially, since no thread is created.
		//Start is necessary because it initiates a new thread and allows the main thread to continue.
		//Here all you have done is told the main thread to do into run, which does not run async.
		//However, this may be useful for debugging purposes.
//		ThreadObj t1 = new ThreadObj("One");
//		ThreadObj t2 = new ThreadObj("Two");
//		ThreadObj t3 = new ThreadObj("Three");
//		ThreadObj t4 = new ThreadObj("Four");
//		
//		t1.run();
//		t2.run();
//		t3.run();
//		t4.run();
		
	}
	
}
