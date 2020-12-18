package DesignPatterns;

import java.util.LinkedList;
import java.util.List;

//Subject, also known as the observable
interface ISubject {
	
	public void register(IObserver o);
	public void unregister(IObserver o);
	public void notifyObservers();
	
}

//All the IObserver has to have an update function called by IObserver
interface IObserver {
	
	public void update();
	
}

class WeatherStation implements ISubject {
	
	//Subject has reference to all observers to notify them
	private List<IObserver> observers = new LinkedList<IObserver>();
	
	//State Fields
	private int tmp;
	
	public WeatherStation() {
		this.tmp = 0;
	}

	@Override
	public void register (IObserver o) {
		observers.add(o);
	}

	@Override
	public void unregister (IObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {

		for(IObserver o : observers) {
			o.update();
		}
		
	}
	
	public int getTemperature() {
		return this.tmp;
	}
	
	public void setTemperature(int tmp) {
		this.tmp = tmp;
		//Don't forget to notify observers on a state change!
		notifyObservers();
	}
	
}

class Observer_Website implements IObserver{
	
	//The observer has a reference to CONCRETE subject to unsubscribe or pull data
	//This means that observers are dependent on concrete subjects
	//However, remember that subjects do not care what is observing them
	private WeatherStation concreteSubject;
	
	//Stored Data from Subject
	private int temperature;
	
	public Observer_Website() {
		temperature = 0;
	}
	
	public void subscribe(WeatherStation subject) {
		this.concreteSubject = subject;
		subject.register(this);
		temperature = subject.getTemperature();
	}
	
	public void unsubscribe() {
		concreteSubject.unregister(this);
		concreteSubject = null;
	}
	
	@Override
	public void update() {
		
		System.out.println("Observer " + this + " has been notified of a state change!");
		temperature = concreteSubject.getTemperature();
		
	}
	
	public void display() {
		System.out.println("The observed temperature is: " + temperature);
	}
	
}

//A weather station is going to notify observers that data updated.
public class ObserverPattern {
	
	public ObserverPattern() {
		
		WeatherStation wS = new WeatherStation();
		Observer_Website oW = new Observer_Website();
		
		//Example display on observer
		oW.display();
		
		//Now register your observer with subject
		oW.subscribe(wS);
		
		//Now update the temperature
		wS.setTemperature(35);
		
		//Now display
		oW.display();
		
		//Unsubscribe, (only possible with reference to subject)
		oW.unsubscribe();
		
		//Note display does NOT change value, since it is unsubbed and not notified
		wS.setTemperature(100);
		oW.display();
		
		
	}
	
}
