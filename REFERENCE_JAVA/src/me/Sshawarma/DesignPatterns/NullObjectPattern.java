package me.Sshawarma.DesignPatterns;

//Avoid creating branches because of the use of null
//Or handle an object representing null better

interface Ball {

    public void kickBall();

}

class SoccerBall implements Ball {

    @Override
    public void kickBall() {
        System.out.println("GOOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAL!");
    }

}

//Null Object
class NoBall implements Ball {

    @Override
    public void kickBall() {
        //Nothing, maybe a message to say that null was safely handled
        System.out.println("Null object safely handled.");
    }

}

public class NullObjectPattern {

    public NullObjectPattern() {

        //Always initialize as NoBall instead of null
        Ball ball = new NoBall();
        ball.kickBall();

        //Now say you want to "initialize" the variable somewhere along the line
        ball = new SoccerBall();
        ball.kickBall();

    }

}
