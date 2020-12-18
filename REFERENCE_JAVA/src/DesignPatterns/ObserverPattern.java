package DesignPatterns;

import java.util.LinkedList;
import java.util.List;

//Subject, also known as the observable
interface ISubject {
	
	public void add(IObserver o);
	public void remove(IObserver o);
	public void notifyObservers();
	
}

interface IObserver {
	
	public void update();
	
}

class WeatherStation implements ISubject{
	
	private List<IObserver> observers = new LinkedList<IObserver>();

	@Override
	public void add(IObserver o) {
		observers.add(o);
	}

	@Override
	public void remove(IObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {

		for(IObserver o : ) {
			
		}
		
	}
	
	
	
}

//A weather station is going to notify observers that data updated.
public class ObserverPattern {

}
