package me.Sshawarma.DesignPatterns;

//The goal of the command pattern is to encapsulate a request/command
//The invoker invokes a command to the receiver

//This example involves turning on and off lights via a remote control
class Invoker {

    //Contains all possible command it would need to do. (Acts like remote)
    ICommand on;
    ICommand off;
    ICommand brighter;
    ICommand dimmer;

    //Use mutators or a constructor to initialize with concrete implementations of commands
    public Invoker(ICommand on, ICommand off, ICommand brighter, ICommand dimmer) {
        this.on = on;
        this.off = off;
        this.brighter = brighter;
        this.dimmer = dimmer;
    }

    //Functions to initiate one of the loaded commands
    public void clickOn() {
        on.execute();
    }
    public void clickOff() {
        off.execute();
    }
    public void clickBrighter() {
        brighter.execute();
    }
    public void clickDimmer() {
        dimmer.execute();
    }

}

//Commands will have an interface so that they can be generalized
interface ICommand{

    public void execute();
    public void unexecute();

}

//Concrete implementations of the command interface
class On implements ICommand {
    //Has an instance of a receiver it links with
    LightReceiver receiver;

    public On(LightReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //Calls an action for the receiver to perform

    }

    @Override
    public void unexecute() {
        //Useful for when you have a list of items and want to rollback

    }
}

//The receiver class is concrete
class LightReceiver {

    int brightness;

    public LightReceiver() {
        brightness = 0;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}




public class CommandPattern {



}
