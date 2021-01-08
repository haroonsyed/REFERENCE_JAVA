package me.Sshawarma.DesignPatterns.Structural;

//Decorators augment functionality during runtime. Multiple addons keep compounding.

//You have decorators instead of adding all combinations in subclasses
//"decorations" are added during runtime


/*

1. Make abstract class for lowest level object with functions needed
2. Make concrete implementations for those base objects
3. Make a decorator class that has a and is a base abstract class
4. Now make decorations that extent the decorator and modif object in layers

 */

//As always part of the magic is not using conrete implementations
abstract class Pizza {

    public abstract String getDescription();
    public abstract int getCost();

}

//Base implementations. Toppings are built on the foundations here.
class ThinPizza extends Pizza {

    @Override
    public String getDescription() {
        return "Thin Dough";
    }

    @Override
    public int getCost() {
        return 2;
    }

}

class ThickPizza extends Pizza {

    @Override
    public String getDescription() {
        return "Thick Dough";
    }

    @Override
    public int getCost() {
        return 3;
    }

}

//Both has-a and is=a pizza
abstract class AddonDecorator extends Pizza {

    //Needs to be accessable to subsequent layers
    protected Pizza pizza;

    public AddonDecorator(Pizza pizza){
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public int getCost() {
        return pizza.getCost();
    }

}

//Here are the "decorations" (END AT LINE 192)
class Mozzarella extends AddonDecorator {

    public Mozzarella(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mozzarella";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 2;
    }


}

class Swiss extends AddonDecorator {

    public Swiss(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Swiss";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 1;
    }

}

class TomatoSauce extends AddonDecorator {

    public TomatoSauce(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Tomato Sauce";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 1;
    }

}

class BBQSauce extends AddonDecorator {

    public BBQSauce(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", BBQ Sauce";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 7;
    }

}

class Shrimp extends AddonDecorator {

    public Shrimp(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Shrimp";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 5;
    }

}

class BlackOlives extends AddonDecorator {

    public BlackOlives(Pizza pizza) {

        super(pizza);

    }

    //Now override methods from parent as needed
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", BlackOlives";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 3;
    }

}

public class DecoratorPattern {

    public DecoratorPattern() {

        //Make a basic pizza
        Pizza basePizza = new ThinPizza();
        //Now pass that into constructor of toppings to add them
        Pizza withCheese = new Mozzarella(basePizza);
        //With toppings
        Pizza withToppings = new BlackOlives(withCheese);

        //Alternatively
        Pizza singleLine = new BlackOlives(new Mozzarella(new ThinPizza()));

        //Should print price as 2+2+3 = 7
        System.out.println("Ordered Pizza is: " + withToppings.getDescription());
        System.out.println("Price: " + withToppings.getCost());

    }

}