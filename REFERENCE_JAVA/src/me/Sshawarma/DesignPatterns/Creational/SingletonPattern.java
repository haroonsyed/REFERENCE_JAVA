package me.Sshawarma.DesignPatterns.Creational;

//Used when we want only one instance of a class to exist.
//Provides a global point of access to the singleton (MOST TIMES NOT A GOOD IDEA!)
class Singleton {

    private static Singleton instance;

    //Must be private so this class cannot be instantiated elsewhere!
    private Singleton() {}

    //For multithreading add "synchronized" keyword to method force both threads to go one at a time (SLOW)
    //Or use synchronized(Singleton.class) inside the method before creating object. (FAST)
    public static Singleton getInstance() {

        if(instance == null){
            instance = new Singleton();
        }
        return instance;

    }

}


public class SingletonPattern {

    public SingletonPattern() {
        Singleton instance = Singleton.getInstance();
        System.out.println("I will always return this same instance during runtime." + instance);
        //Get instance again
        instance = Singleton.getInstance();
        System.out.println("After getting instance again I get reference: " + instance);
    }

}
