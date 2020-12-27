package me.Sshawarma.DesignPatterns;

//Prototype allows us to clone an object without using the new keyword
//Useful when initializing an object is expensive or you want to keep the configuration of an object as default.
//It can also be used to initialize an object by referring to its class

//Tells java it is okay to copy instances of this class. These copies will be copied to different places in memory
interface Animal extends Cloneable {

    public Animal makeCopy();
    public void print();

}

class Sheep implements Animal {

    private int data;

    public Sheep() {
        data = (int)(Math.random() * 100);
        System.out.println("Sheep has been made");
    }

    @Override
    public Animal makeCopy() {
        System.out.println("Sheep is being copied");

        Sheep sheepCopy = null;

        try {

            sheepCopy = (Sheep) super.clone();

        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return sheepCopy;
    }

    public void print() {
        System.out.println("Sheep at memory location: " + this + " with data: " + data);
    }

}

class Dog implements Animal {

    private int data;

    public Dog() {
        data = (int)(Math.random() * 100);
        System.out.println("Dog has been made");
    }

    @Override
    public Animal makeCopy() {
        System.out.println("Dog is being copied");

        Dog dogCopy = null;

        try {

            dogCopy = (Dog) super.clone();

        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return dogCopy;
    }

    public void print() {
        System.out.println("Dog at memory location: " + this + " vwith data: " + data);
    }

}

//Lets us send any animal into this factory (not necessary under the pattern)
class CloneFactory {

    public Animal getClone(Animal animalToCopy) {
        return animalToCopy.makeCopy();
    }

}


public class PrototypePattern {

    public PrototypePattern() {

        //Makes copies according to animal type
        CloneFactory cloneFactory = new CloneFactory();

        //Sheep to Clone
        Animal sally = new Sheep();
        sally.print();

        System.out.println();
        Animal clonedSheep = (Sheep) cloneFactory.getClone(sally);
        clonedSheep.print();
        //Totally fine to do this as well
        //Sheep clonedSheep = sally.makeCopy();

    }

}
