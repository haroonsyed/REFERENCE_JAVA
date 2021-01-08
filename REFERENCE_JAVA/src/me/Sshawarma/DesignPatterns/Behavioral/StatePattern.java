package me.Sshawarma.DesignPatterns.Behavioral;

//Uses the same concept as statemachines to better layout all the possible combinations of a system.
//Decisions are based on the current state the program is in.

//In this example we use an atm machine to show the pattern

//This class is called the context. It is the object holding our state machine's state.
//In this case that would be an actual ATM machine
class Context_ATMMachine {

    private I_ATMState state;
    private int cashInMachine = 2000;

    //Give default state, inject state if you need testing capability
    public Context_ATMMachine() {
        state = new noCard(this);
    }

    public int getCashInMachine() {
        return cashInMachine;
    }

    public void setCashInMachine(int balance) {
        cashInMachine = balance;
    }

    public void setState(I_ATMState state) {
        this.state = state;
    }

    //Now just have a call to all the methods in the state, and return the state accordingly
    public void insertCard() {
        state.insertCard();
    }
    public void ejectCard() {
        state.ejectCard();
    }
    public void insertPin(int pin) {
        state.insertPin(pin);
    }
    public void request(int cashRequested) {
        state.request(cashRequested);
    }

}

//The interface defines the actions/inputs to the machine
//These methods should set the state of the context, and are called handles
interface I_ATMState {

    void insertCard();
    void ejectCard();
    void insertPin(int pinEntered);
    void request(int cashRequested);

}

//Concrete implementations of state
class hasCard implements I_ATMState {

    private Context_ATMMachine atm;

    public hasCard(Context_ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("ERROR: There is already a card in this machine!");
    }

    @Override
    public void ejectCard() {
        System.out.println("Here is your card back!");
        atm.setState(new noCard(atm));
    }

    @Override
    //Assume any pin is correct for example sake
    public void insertPin(int pinEntered) {
        System.out.println("Thank you for inputting your pin. You can now check in/out cash.");
        atm.setState(new hasCorrectPin(atm));
    }

    @Override
    public void request(int cashRequested) {
        System.out.println("ERROR: You must put in your pin first!");
    }

}

class noCard implements I_ATMState {

    private Context_ATMMachine atm;

    public noCard(Context_ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Please input your pin next to check in/out cash.");
        atm.setState(new hasCard(atm));
    }

    @Override
    public void ejectCard() {
        System.out.println("ERROR: No card in machine!");
    }

    @Override
    public void insertPin(int pinEntered) {
        System.out.println("ERROR: Please input a card first!");
    }

    @Override
    public void request(int cashRequested) {
        System.out.println("ERROR: Please input a card first!");
    }

}

class hasCorrectPin implements I_ATMState {

    private Context_ATMMachine atm;

    public hasCorrectPin(Context_ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("ERROR: There is already a card in the machine!");
    }

    @Override
    public void ejectCard() {
        System.out.println("Here is your card back!");
        atm.setState(new noCard(atm));
    }

    @Override
    public void insertPin(int pinEntered) {
        System.out.println("Pin already entered!");
    }

    @Override
    public void request(int cashRequested) {
        System.out.println("Here is your cash!");
        if(cashRequested <= atm.getCashInMachine()) {
            atm.setCashInMachine(atm.getCashInMachine() - cashRequested);
        }
        else{
            System.out.println("ERROR: Cash requested is too high for atm!");
        }

        //Why would we tell a customer this? No reason, just an example. Maybe it is bank operator
        System.out.println("ATM Balance: " + atm.getCashInMachine());

        //Move state to no money if there is none...
        if(atm.getCashInMachine() == 0) {
            //Set state to no money
            atm.ejectCard();
            atm.setState(new hasNoMoney(atm));
        }

    }

}

class hasNoMoney implements I_ATMState {

    private Context_ATMMachine atm;

    public hasNoMoney(Context_ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Machine is unavailable, please inform bank.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Machine is unavailable, please inform bank.");
    }

    @Override
    public void insertPin(int pinEntered) {
        System.out.println("Machine is unavailable, please inform bank.");
    }

    @Override
    public void request(int cashRequested) {
        System.out.println("Machine is unavailable, please inform bank.");
    }

}

public class StatePattern {

    public StatePattern() {

        //Just a scenario of removing money from bank until empty...
        Context_ATMMachine atm = new Context_ATMMachine();
        atm.insertCard();
        atm.insertCard();
        atm.insertPin(12341);
        atm.insertPin(12324);
        atm.request(1999);
        atm.ejectCard();

        //Now taking all money out of machine
        atm.insertCard();
        atm.insertPin(12341);
        atm.request(1);

        //ATM LOCKED DOWN
        atm.insertCard();

        //Side note, this is a perfect scenario to combine with a proxy
        //Since we dont want to give access to the getCash or setCash methods on the atm

    }

}
