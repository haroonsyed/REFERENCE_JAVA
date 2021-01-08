package me.Sshawarma.DesignPatterns.Behavioral;

//Strategy pattern aims to change functionality during runtime. (Swap out strategy)

//Here we will separate the fly method of ducks into implementations of an internface and then instantiate ducks with those attributes

interface IFly{
	
	public void fly();
	
}

//These are called strategies
class SimpleFly implements IFly{

	@Override
	public void fly() {
		System.out.println("Quack Quack i am flying in the air");
	}
	
}

class NoFly implements IFly{
	
	@Override
	public void fly() {
		System.out.println("I cannot fly.");
	}
	
}

//Now the base class just has to implement one of these strategies
class Duck{
	
	private IFly flyType;
	
	public Duck(IFly flyType) {

		this.flyType = flyType;

	}
	
	public void fly() {
		flyType.fly();
	}
	
}

public class StrategyPattern {
	
	public StrategyPattern() {
		
		Duck rubberDuck = new Duck(new NoFly());
		Duck normalDuck = new Duck(new SimpleFly());
		
		rubberDuck.fly();
		normalDuck.fly();
		
	}
	
}
