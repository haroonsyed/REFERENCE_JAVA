package me.Sshawarma.DesignPatterns;

//Handles communication between related objects (colleagues). Communication is handled with a mediator object
//which  loose coupling.

//In this example we will use a chatroom and visitors to demonstrate the pattern
//The problem we solve is that every user does not send a message to every other user, they simply go through the
//mediator who then sends the message to everyone.
//Another example is an air traffic controller and a bunch of planes (the solution is rather natural in fact)


import java.util.ArrayList;
import java.util.List;

//This is the mediator interface
abstract class AbstractChatroom {

    //Mediator contains reference to all colleagues
    private List<AbstractVisitor> users = new ArrayList<AbstractVisitor>();

    //Add user to chatroom
    public void register(AbstractVisitor user) {
        users.add(user);
        user.setChatroom(this);
        user.receive("Thank you " + user.getName() + " for registering with this chatroom!");
    }

    //Forwards message from one user to the whole group, with appropriate names
    public void send(String raw_msg, AbstractVisitor sender) {

        for(AbstractVisitor user : users) {
            if(sender != user) {
                user.receive("From " + sender.getName() + " to " + user.getName() + ": " + raw_msg);
            }
        }

    }

}

//Concrete implementation (can be a family of different chatrooms)
class BasicChatroom extends AbstractChatroom {

    public BasicChatroom() {
        super();
    }

}

//This is the colleague interface
abstract class AbstractVisitor {

    private AbstractChatroom chatroom = null;

    private String name;

    public AbstractVisitor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void send(String msg) {

        chatroom.send(msg, this);

    }

    public void receive(String msg) {

        System.out.println(msg);

    }

    public void setChatroom(AbstractChatroom chatroom) {
        this.chatroom = chatroom;
    }

}

//Can have multiple types of users, say an admin/host and basic participant
class BasicUser extends AbstractVisitor {

    public BasicUser(String name) {

        super(name);

    }

}

public class MediatorPattern {

    public MediatorPattern() {

        //Create users and register with chatroom
        AbstractChatroom chatroom = new BasicChatroom();

        AbstractVisitor user1 = new BasicUser("Bob");
        AbstractVisitor user2 = new BasicUser("Bill");
        AbstractVisitor user3 = new BasicUser("Joe");
        AbstractVisitor user4 = new BasicUser("Jill");

        //In retrospect the users should register to the chatroom, but the idea is the same
        chatroom.register(user1);
        chatroom.register(user2);
        chatroom.register(user3);
        chatroom.register(user4);

        //Test sending message through mediator, the colleagues have no direct knowledge of one another!
        user1.send("Hi all!");
        user2.send("Hey, how's it going Bob?");
        user3.send("Does anyone want me to bring some coffee from downstairs?");
        user4.send("Good morning Bob, any progress on last week's work?");
        user1.send("It's going great Bill, just sending the report Jill just asked for :)");

    }

}
