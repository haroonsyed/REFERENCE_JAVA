package me.Sshawarma.DesignPatterns;

//Proxy acts as a middleman from application to another resource.
/*

Used for remote access, caching, and protection
For example you could lock out being able to use certain functions, access data, or cache expensive requests
Here we will use a bank example to check if an ATM is accessible by client
To the client there is little indication it is going through a proxy

 */

//Interface for Subject
interface IGetATMData {

    public int getCashInMachine();

}

//Real Subject
class ATMMachine implements IGetATMData{

    private int cash;

    public ATMMachine() {
        this.cash = 2000;
    }

    @Override
    public int getCashInMachine() {
        return cash;
    }

}

//Proxy
class ATMProxy implements IGetATMData {

    private IGetATMData atm;
    private String username;

    //Give credentials
    public ATMProxy(String username) {
        this.username = username;
    }


    @Override
    public int getCashInMachine() {

        //Init if it is not done so (reduce access overhead), and cache reference
        if(atm == null) {
            atm = new ATMMachine();
        }

        //Do verification here
        if(username.equals("BankOwner")){
            return atm.getCashInMachine();
        }
        else{
            System.out.println("You do not have access to that command!");
            return -1;
        }

    }

}


public class ProxyPattern {

    public ProxyPattern() {

        //Go through proxy to access information
        System.out.println("Username: Bob");
        IGetATMData proxy = new ATMProxy("Bob");
        System.out.println("Cash in Machine: " + proxy.getCashInMachine());

        //Valid username
        System.out.println("\nUsername: BankOwner");
        proxy = new ATMProxy("BankOwner");
        System.out.println("Cash in Machine: " + proxy.getCashInMachine());

    }

}
