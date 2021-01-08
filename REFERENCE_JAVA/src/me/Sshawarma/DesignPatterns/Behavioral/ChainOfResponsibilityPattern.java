package me.Sshawarma.DesignPatterns.Behavioral;

//Allow objects to handle executions of themselves by passing data to the next implementation if they cannot handle
//the request.
//This is useful because you can decide to break the chain at any point necessary or define a specific chain to
//execute a certain instruction type, or have multiple chains act/receive on a request.


//This class is not part of the pattern, just an object of data to be passed
class Numbers {

    private int n1;
    private int n2;

    private String calcType;

    public Numbers(int n1, int n2, String calcType) {
        this.n1 = n1;
        this.n2 = n2;
        this.calcType = calcType;
    }

    //getters
    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }
    public String getCalcType() {
        return calcType;
    }

}

//Interface/Abstract class that concrete handlers must follow.
interface NumbersHandler {

    public void setNextHandler(NumbersHandler nextHandler);
    public void calculate(Numbers request);

}

//Concrete implementations of request handler. Pass request to next in chain if this cannot handle the request.
class AddNumbers implements NumbersHandler {

    //Store next object in chain to handle calculations
    private NumbersHandler nextHandler;

    @Override
    public void setNextHandler(NumbersHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {

        //If request matches, execute. Otherwise pass request to next in chain.
        if(request.getCalcType().equals("add")) {
            System.out.println("Result of " + request.getN1() + " + " + request.getN2() + " is " + (request.getN1() + request.getN2()));
        }
        else{
            if(nextHandler == null) {
                //I should probably use null object pattern here, but I digress
                System.out.println("ERROR: Unsupported or Invalid operation type!");
            }
            else{
                nextHandler.calculate(request);
            }
        }

    }

}

class SubtractNumbers implements NumbersHandler {

    //Store next object in chain to handle calculations
    private NumbersHandler nextHandler;

    @Override
    public void setNextHandler(NumbersHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {

        //If request matches, execute. Otherwise pass request to next in chain.
        if(request.getCalcType().equals("subtract")) {
            System.out.println("Result of " + request.getN1() + " - " + request.getN2() + " is " + (request.getN1() - request.getN2()));
        }
        else{
            if(nextHandler == null) {
                System.out.println("ERROR: Unsupported or Invalid operation type!");
            }
            else{
                nextHandler.calculate(request);
            }
        }

    }

}

class MultNumbers implements NumbersHandler {

    //Store next object in chain to handle calculations
    private NumbersHandler nextHandler;

    @Override
    public void setNextHandler(NumbersHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {

        //If request matches, execute. Otherwise pass request to next in chain.
        if(request.getCalcType().equals("multiply")) {
            System.out.println("Result of " + request.getN1() + " * " + request.getN2() + " is " + (request.getN1() * request.getN2()));
        }
        else{
            if(nextHandler == null) {
                System.out.println("ERROR: Unsupported or Invalid operation type!");
            }
            else{
                nextHandler.calculate(request);
            }
        }

    }

}

class DivNumbers implements NumbersHandler {

    //Store next object in chain to handle calculations
    private NumbersHandler nextHandler;

    @Override
    public void setNextHandler(NumbersHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {

        //If request matches, execute. Otherwise pass request to next in chain.
        if(request.getCalcType().equals("divide")) {
            System.out.println("Result of " + request.getN1() + " / " + request.getN2() + " is " + (request.getN1() / request.getN2()));
        }
        else{
            if(nextHandler == null) {
                System.out.println("ERROR: Unsupported or Invalid operation type!");
            }
            else{
                nextHandler.calculate(request);
            }
        }

    }

}

public class ChainOfResponsibilityPattern {

    public ChainOfResponsibilityPattern() {

        //Create a chain of the objects
        NumbersHandler chainCalc1 = new AddNumbers();
        NumbersHandler chainCalc2 = new SubtractNumbers();
        NumbersHandler chainCalc3 = new MultNumbers();
        NumbersHandler chainCalc4 = new DivNumbers();

        chainCalc1.setNextHandler(chainCalc2);
        chainCalc2.setNextHandler(chainCalc3);
        chainCalc3.setNextHandler(chainCalc4);
        //The division chain will have the null nextHandler, indicating an invalid operation.

        Numbers request = new Numbers(4, 2, "add");
        Numbers request2 = new Numbers(4, 2, "subtract");
        Numbers request3 = new Numbers(4, 2, "multiply");
        Numbers request4 = new Numbers(4, 2, "divide");
        Numbers request5 = new Numbers(4, 2, "pow");

        //Now start execution from the first chain and let the objects handle the rest
        chainCalc1.calculate(request);
        chainCalc1.calculate(request2);
        chainCalc1.calculate(request3);
        chainCalc1.calculate(request4);
        chainCalc1.calculate(request5);

    }

}
