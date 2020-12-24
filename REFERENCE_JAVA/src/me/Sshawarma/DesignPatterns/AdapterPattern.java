package me.Sshawarma.DesignPatterns;

//Makes two interfaces compatible by mapping functions with shared context
//In this example we create an adapter from EnemyAttacker which drives to a robot EnemyAttacker which walks

//ITarget
interface IVehicleEnemy{

    public void fireWeapon();
    public void driveForward();
    public void assignDriver(String name);

}

//Concrete implementations of the target interface
class EnemyTank implements IVehicleEnemy {

    String name;

    @Override
    public void fireWeapon() {
        System.out.println("Enemy Tank shoots its cannon!");
    }

    @Override
    public void driveForward() {
        System.out.println("Enemy Tank drives forward!");
    }

    @Override
    public void assignDriver(String name) {
        System.out.println("Enemy Tank now has driver: " + name);
    }

}

//Adaptee, uses a completely different interface
interface IRobotEnemy {

    public void smashWithLimbs();
    public void walkForward();
    public void reactToHuman();

}

//Concrete implementation of adaptee
class SimpleRobot implements IRobotEnemy {

    @Override
    public void smashWithLimbs() {
        System.out.println("Robot causes damage by smashing with hands.");
    }

    @Override
    public void walkForward() {
        System.out.println("Robot walks forward.");
    }

    @Override
    public void reactToHuman() {
        System.out.println("Robot tramps on human.");
    }

}

//Adapter implements the TARGET
class VehicleToRobotAdapter implements IVehicleEnemy {

    //Has an instance of a Robot
    IRobotEnemy robotEnemy;

    public VehicleToRobotAdapter(IRobotEnemy robotEnemy) {
        this.robotEnemy = robotEnemy;
    }

    @Override
    public void fireWeapon() {
        robotEnemy.smashWithLimbs();
    }

    @Override
    public void driveForward() {
        robotEnemy.walkForward();
    }

    @Override
    public void assignDriver(String name) {
        //No need to do anything here, since it does not apply to robot!
    }

}


public class AdapterPattern {

    public AdapterPattern() {

        //Create Target
        System.out.println("Target output:");
        IVehicleEnemy vehicleEnemy = new EnemyTank();
        vehicleEnemy.fireWeapon();

        //Create Adaptee
        System.out.println("\nAdaptee output without adapter, but using specific method:");
        IRobotEnemy robotEnemy = new SimpleRobot();
        //We have to call the method directly unless we use adapter...
        robotEnemy.smashWithLimbs();

        //Create adapter using the adaptee
        IVehicleEnemy adapter = new VehicleToRobotAdapter(robotEnemy);

        //Now you can call methods on the adapter as if it were a VehicleEnemy.
        System.out.println("\nUsing an adapter we can treat the robot as if it were a vehicle:");
        adapter.fireWeapon();

    }

}
