package me.Sshawarma.DesignPatterns;

//Encapsulates Instantiation and allows switching of the instantiation method.
//The client is not exposed to object creation

//EnemyShip types (products) to be created
interface EnemyShip {

    public String getName();
    public int getDamage();
    public int getSpeed();

}

class smallUFO implements EnemyShip {

    @Override
    public String getName() {
        return "Small UFO";
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

}

class mediumUFO implements EnemyShip {

    @Override
    public String getName() {
        return "Medium UFO";
    }

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public int getSpeed() {
        return 5;
    }

}

class bigUFO implements EnemyShip {

    @Override
    public String getName() {
        return "Big UFO";
    }

    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

}

//Abstracted the factory so it can have different implementations, for example it could call appropriate groupings of products
abstract class ShipFactory {

    enum ShipType {
        SMALL, MEDIUM, BIG;
    }

    public abstract EnemyShip makeEnemyShip(ShipType shipType);

}

class basicShipFactory extends ShipFactory {


    @Override
    public EnemyShip makeEnemyShip(ShipType shipType) {

        switch (shipType){
            case SMALL:
                return new smallUFO();
            case MEDIUM:
                return new mediumUFO();
            case BIG:
                return new bigUFO();
            default:

        }

        //INVALID
        return null;

    }

}

public class FactoryPattern {

    public FactoryPattern() {
        //Note how no object creation takes place here, it is done by the factory class

        //Factory
        ShipFactory factory = new basicShipFactory();

        //Use factory to implicitly create ship
        EnemyShip enemyShip = factory.makeEnemyShip(ShipFactory.ShipType.SMALL);

        System.out.println(enemyShip.getName() + ", SPEED: " + enemyShip.getSpeed() + " ATTACK_POWER: " + enemyShip.getDamage());

    }

}
