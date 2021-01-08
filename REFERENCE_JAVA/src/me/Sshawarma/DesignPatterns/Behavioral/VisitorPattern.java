package me.Sshawarma.DesignPatterns.Behavioral;

//Add methods to differing classes with minimal change in said classes using overloading
//The methods can vary in implementation between said classes.
//For UML, see paper notes

//This is the interface that has differing methods in the same family.
//Classes passed into these functions will/can receive different behavior.
interface ITaxVisitor {

    public double visit(Liquor product);
    public double visit(Groceries product);
    public double visit(Property product);

}

//Concrete visitors. Provides price with tax.
class TaxVisitorUS implements ITaxVisitor {

    //Referred to as "visit" function in UMl
    @Override
    public double visit(Liquor product) {
        //%13 percent
        return product.getPrice() * 1.13;
    }

    @Override
    public double visit(Groceries product) {
        //Necessity is 0%
        return product.getPrice() * 1;
    }

    @Override
    public double visit(Property product) {
        //1% annual tax
        return product.getPrice() * .01;
    }

}

//I don't know UK taxes, but say they are double the US...
//Note how easy it was to implement a different tax system.
class TaxVisitorUK implements ITaxVisitor {

    //Referred to as "visit" function in UMl
    @Override
    public double visit(Liquor product) {
        //%13 percent
        return product.getPrice() * 1.26;
    }

    @Override
    public double visit(Groceries product) {
        //Necessity is 0%
        return product.getPrice() * 1;
    }

    @Override
    public double visit(Property product) {
        //1% annual tax
        return product.getPrice() * .02;
    }

}

//This is called the element interface. Passes instance of Element into visitor.
interface ITaxable {

    //Referred to as "accept" function in UML
    //Takes the given element and passes them to the visitor for using those overloaded functions.
    //You can use many different visitors now and use them as pertaining to the object!
    public double accept(ITaxVisitor visitor);

}

//Concrete Elements
class Liquor implements ITaxable {

    private double price;

    Liquor(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double accept(ITaxVisitor visitor) {
        return visitor.visit(this);
    }


}

class Groceries implements ITaxable {

    private double price;

    Groceries(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double accept(ITaxVisitor visitor) {
        return visitor.visit(this);
    }

}

class Property implements ITaxable {

    private double price;

    Property(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double accept(ITaxVisitor visitor) {
        return visitor.visit(this);
    }

}


public class VisitorPattern {

    public VisitorPattern() {

        //Lets do a tax calculation with the US and UK
        TaxVisitorUS usTax = new TaxVisitorUS();
        TaxVisitorUK ukTax = new TaxVisitorUK();

        Liquor wine = new Liquor(20.00);
        Groceries cheese = new Groceries(5.99);
        Property house = new Property(150399.99);

        System.out.println("The price of the wine with US tax is: $" + usTax.visit(wine));
        System.out.println("The price of the wine with UK tax is: $" + ukTax.visit(wine));

        System.out.println("\nThe price of the cheese with US tax is: $" + usTax.visit(cheese));
        System.out.println("The price of the cheese with UK tax is: $" + ukTax.visit(cheese));

        System.out.println("\nThe annual property tax of the house with US tax is: $" + usTax.visit(house));
        System.out.println("The annual property tax of the house with UK tax is: $" + ukTax.visit(house));

    }

}
