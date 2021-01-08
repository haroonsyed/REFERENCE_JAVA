package me.Sshawarma.DesignPatterns.Behavioral;

//The goal of the command pattern is to encapsulate a request/command
//The invoker invokes a command to the receiver, which decouples the two

//This example involves turning on and off lights via a remote control
class Invoker {

    //Contains all possible command it would need to do. (Acts like remote)
    ICommand command;

    //Use mutators or a constructor to initialize with concrete implementations of commands
    public Invoker(ICommand cmd) {
        this.command = cmd;
    }

    //Functions to initiate one of the loaded commands
    public void clickDoButton() {
        command.execute();
    }

    public void clickUndoButton() {
        command.unexecute();
    }

    //Mutators for possibly changing commands during runtime
    public void setCommand(ICommand cmd) {
        this.command = cmd;
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
    IReceiver receiver;

    public On(IReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //Calls an action for the receiver to perform
        receiver.on();
    }

    @Override
    public void unexecute() {
        //Useful for when you have a list of items and want to rollback
        //Implementation CAN be more tricky if you are not using state, here info could be lost but it isn't important
        receiver.off();
    }
}

class Off implements ICommand {
    //Has an instance of a receiver it links with
    IReceiver receiver;

    public Off(IReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //Calls an action for the receiver to perform
        receiver.off();
    }

    @Override
    public void unexecute() {
        //Useful for when you have a list of items and want to rollback
        //Implementation CAN be more tricky if you are not using state, here info could be lost but it isn't important
        receiver.on();
    }
}

class Brighter implements ICommand {
    //Has an instance of a receiver it links with
    IReceiver receiver;

    public Brighter(IReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //Calls an action for the receiver to perform
        receiver.brighter();
    }

    @Override
    public void unexecute() {
        //Useful for when you have a list of items and want to rollback
        //Implementation CAN be more tricky if you are not using state, here info could be lost but it isn't important
        receiver.dimmer();
    }
}

class Dimmer implements ICommand {
    //Has an instance of a receiver it links with
    IReceiver receiver;

    public Dimmer(IReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //Calls an action for the receiver to perform
        receiver.dimmer();
    }

    @Override
    public void unexecute() {
        //Useful for when you have a list of items and want to rollback
        //Implementation CAN be more tricky if you are not using state, here info could be lost but it isn't important
        receiver.brighter();
    }
}

//The receiver class is an interface in case commands want to be sent to many receiver types that may behave differently
interface IReceiver {
    public void on();
    public void off();
    public void brighter();
    public void dimmer();
    public int getBrightness();
}

//Hue Lights
class HueLights implements IReceiver {

    int brightness;

    public HueLights() {
        brightness = 0;
    }

    public void on() {
        brightness = 100;
    }

    public void off() {
        brightness = 0;
    }

    public void brighter() {
        brightness += 20;
        if(brightness > 100) {
            brightness = 100;
        }
    }

    public void dimmer () {
        brightness -= 20;
        if (brightness < 0) {
            brightness = 0;
        }
    }

    public int getBrightness() {
        return brightness;
    }

}




public class CommandPattern {

    public CommandPattern() {

        //Create Receiver
        IReceiver bulb = new HueLights();

        //Create invoker
        Invoker remote = new Invoker(new On(bulb));

        //Print init state
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());

        //Call commands
        remote.clickDoButton();
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());
        remote.setCommand(new Off(bulb));
        remote.clickDoButton();
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());
        remote.setCommand(new Brighter(bulb));
        remote.clickDoButton();
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());
        remote.setCommand(new Dimmer(bulb));
        remote.clickDoButton();
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());

        //You can also call unexecute
        remote.clickUndoButton();
        System.out.println("BRIGHTNESS: " + bulb.getBrightness());

    }

}
