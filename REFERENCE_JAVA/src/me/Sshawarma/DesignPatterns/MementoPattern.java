package me.Sshawarma.DesignPatterns;

//Allows for easy storing and retrieval of history state of an object
//The memento is what you want to store the state of
//The caretaker holds a list of all versions of said memento
//The originiator is able to make and get mementos

//This example will be simple with storing strings in an article

import java.util.ArrayList;
import java.util.List;

class Memento{

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}

class Originator {

    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    //Saves memento with the current state
    public Memento save() {
        return new Memento(state);
    }

    //Add restore method to set state of memento with current state
    public void restore(Memento m) {
        this.state = m.getState();
    }

}

class CareTaker {

    private List<Memento> history;
    //We want to be able to go back and forth in the history of the application, this can track that
    private int currentState = -1;

    public CareTaker() {
        history = new ArrayList<Memento>();
    }

    public void addMemento(Memento m) {
        history.add(m);
        //Most recent state is at the end of the list
        currentState = history.size()-1;
    }

    //Get memento at a given point in history
    public Memento getMemento(int index) {
        return history.get(index);
    }

    //Do note that the implementation of undo and redo can vary.
    //The important part is that they exist and work appropriately in their context to the originator.
    public Memento undo() {
        if(currentState == 0) {
            //Fully undid state
            try{
                return history.get(0);
            }
            catch (Exception e){
                //Out of bounds
                e.printStackTrace();
                return null;
            }
        }
        return history.get(--currentState);
    }

    public Memento redo() {
        if(currentState == history.size()-1) {
            //Fully redid state
            try{
                return history.get(history.size()-1);
            }
            catch (Exception e){
                //Out of bounds
                e.printStackTrace();
                return null;
            }
        }
        return history.get(++currentState);
    }

    //Prints all history, just for demo
    public void print() {

        System.out.println("\nAll States: ");
        for(Memento m : history) {
            System.out.println(m.getState());
        }

        //Print current state
        System.out.println("\nCurrent State: " + history.get(currentState).getState());

    }

}


public class MementoPattern {

    public MementoPattern() {

        /*
        DISCLAIMER:
        I have a strong belief that the tutorial that showed this pattern violated encapsulation
        I believe that the caretaker should have a reference to the originator it acts on, and simply calls save or undo
        directly.
        The originator should not be saving or restoring itself imo...
        Unless we are switching out the originator we use, there is no reason to change it.
         */

        //First create the originator and caretaker
        //The originiator is the object you are working with
        //The caretaker will take states from originator and treat them as mementos
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        //Now use the methods in the originator and pass the memento to the careTaker.
        //The originator acts like a memento, and the save method
        originator.setState("State 1");
        careTaker.addMemento(originator.save());

        originator.setState("State 2");
        careTaker.addMemento(originator.save());

        originator.restore(careTaker.undo());

        careTaker.print();

    }

}
