package DesignPatterns;

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
	
	public enum SPECIE {
		WILD, CITY, RUBBER
	}
	
	public Duck(SPECIE specie) {
		switch(specie) {
			
			//Here just select the algorithms you want to assign to each type of DUCK. More flexible than horizontal code between species.
			case WILD:
			case CITY:
				this.flyType = new SimpleFly();
				break;
			case RUBBER:
				this.flyType = new NoFly();
				break;
			default:
				//If a new enum is defined but no definition yet, this stops code from breaking
				this.flyType = new SimpleFly();
		}
	}
	
	public void fly() {
		flyType.fly();
	}
	
}

public class StrategyPattern {
	
	public StrategyPattern() {
		
		Duck rubberDuck = new Duck(Duck.SPECIE.RUBBER);
		Duck normalDuck = new Duck(Duck.SPECIE.WILD);
		
		rubberDuck.fly();
		normalDuck.fly();
		
	}
	
}
