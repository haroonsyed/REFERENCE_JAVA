package me.Sshawarma.DesignPatterns;

//Create a group of subclasses with similar methods.
//Use an abstract class that tells what general methods might be performed
//Hooks cause certain methods to become disabled

//In this example, we use a burger hut to make various burgers
abstract class T_Burger {

    //final to stop subclasses from changing "general" burger algorithm
    final void makeBurger() {

        cutBun();

        if(customerWantsMeat()) {
            addMeat();
            System.out.println();
        }

        if(customerWantsCheese()) {
            addCheese();
            System.out.println();
        }

        if(customerWantsVeggies()) {
            addVeggies();
            System.out.println();
        }

        if(customerWantsCondiments()) {
            addCondiments();
            System.out.println();
        }

        wrapTheBurger();
        System.out.println();

    }

    public void cutBun() {
        System.out.println("The Burger Bun is Cut");
    }
    public void wrapTheBurger() {
        System.out.println("The burger is wrapped.");
    }

    abstract void addMeat();
    abstract void addCheese();
    abstract void addVeggies();
    abstract void addCondiments();

    //Default, override if necessary! These are hooks!
    boolean customerWantsMeat() { return true; }
    boolean customerWantsCheese() { return true; }
    boolean customerWantsVeggies() { return true; }
    boolean customerWantsCondiments() { return true; }

}

//Now anything using the template simply inherits the template
class ItalianBurger extends T_Burger {

    //This is just for this implementation, not necessary for the pattern
    String[] meatUsed = { "Salami", "Turkey", "Beef-Strips" };
    String[] cheeseUsed = { "Provolone" };
    String[] veggiesUsed = { "Lettuce", "Tomatoes", "Onions", "Sweet-Peppers" };
    String[] condimentsUsed = { "Oil", "Vinegar" };


    @Override
    void addMeat() {

        System.out.println("Adding the meat: ");

        for(String meat : meatUsed) {
            System.out.print(meat + " ");
        }

    }

    @Override
    void addCheese() {

        System.out.println("Adding the cheese: ");

        for(String cheese : cheeseUsed) {
            System.out.print(cheese + " ");
        }

    }

    @Override
    void addVeggies() {

        System.out.println("Adding the veggies: ");

        for(String veggie : veggiesUsed) {
            System.out.print(veggie + " ");
        }

    }

    @Override
    void addCondiments() {

        System.out.println("Adding the condiments: ");

        for(String condiment : condimentsUsed) {
            System.out.print(condiment + " ");
        }

    }

}

class VeggieBurger extends T_Burger {

    //This is just for this implementation, not necessary for the pattern
    String[] veggiesUsed = { "Lettuce", "Tomatoes", "Onions", "Sweet Peppers" };
    String[] condimentsUsed = { "Oil", "Vinegar" };

    //WE DISABLE THE CHEESE AND MEAT HOOKS
    @Override
    boolean customerWantsMeat() {
        return false;
    }

    @Override
    boolean customerWantsCheese() {
        return false;
    }

    @Override
    void addMeat() {
        //Leave blank
    }

    @Override
    void addCheese() {
        //Leave blank
    }

    @Override
    void addVeggies() {

        System.out.println("Adding the veggies: ");

        for(String veggie : veggiesUsed) {
            System.out.print(veggie + " ");
        }

    }

    @Override
    void addCondiments() {

        System.out.println("Adding the condiments: ");

        for(String condiment : condimentsUsed) {
            System.out.print(condiment + " ");
        }

    }

}

public class TemplateMethodPattern {

    public TemplateMethodPattern() {

        T_Burger customItalian = new ItalianBurger();
        //Now call make
        customItalian.makeBurger();

        //Same with veggie
        T_Burger customVeggie = new VeggieBurger();
        customVeggie.makeBurger();

    }

}
