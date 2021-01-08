package me.Sshawarma.DesignPatterns.Behavioral;

//Basically uses an interface to abstract the iteration process between many different data structures/iteration behaviors.
//Pretty intuitive from the iterator interface found in c++/java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This is what iterates
interface IFruitContainerIterator {

    public boolean hasNext();
    public Fruit next();

}

//This is what is iterable
interface IFruitContainer {

    public IFruitContainerIterator getIterator();

}

//Concrete implementation(s) of iterator
class ListFruitsContainerIterator implements IFruitContainerIterator {

    private List<Fruit> container;
    private int pos;

    public ListFruitsContainerIterator(List<Fruit> container) {
        this.container = container;
        pos = 0;
    }

    @Override
    public boolean hasNext() {

        if(pos < container.size()) {
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Fruit next() {

        if(hasNext()) {
            //Remember this is a post decrement
            return container.get(pos++);
        }
        else{
            return null;
        }

    }

}

//Another iterator implementation for another container type
class MapFruitsContainerIterator implements IFruitContainerIterator {

    private List<Map.Entry<Fruit, Integer>> container;
    private int pos;

    public MapFruitsContainerIterator(Map<Fruit, Integer> container) {
        this.container = new ArrayList<Map.Entry<Fruit, Integer>>(container.entrySet());
        pos = 0;
    }

    @Override
    public boolean hasNext() {

        if(pos < container.size()) {
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Fruit next() {

        if(hasNext()) {
            //Remember this is a post decrement, the map is just showing a different implementation
            return container.get(pos++).getKey();
        }
        else{
            return null;
        }

    }

}

//Item to go in iterable
class Fruit {

    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

//Concrete implementation of iterable
class ListFruitsContainer implements IFruitContainer {

    private List<Fruit> fruits;

    public ListFruitsContainer() {
        fruits = new ArrayList<Fruit>();
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    @Override
    public IFruitContainerIterator getIterator() {
        //Pass the data collection to your iterator class
        return new ListFruitsContainerIterator(this.fruits);
    }

}

class MapFruitsContainer implements IFruitContainer {

    private Map<Fruit, Integer> fruits;

    public MapFruitsContainer() {
        fruits = new HashMap<Fruit, Integer>();
    }

    //Rather contrived way of adding data to map, but this is just for demo purposes
    public void addFruit(Fruit fruit, Integer quantity) {
        fruits.put(fruit, quantity);
    }

    @Override
    public IFruitContainerIterator getIterator() {
        //Pass the data collection to your iterator class
        return new MapFruitsContainerIterator(this.fruits);
    }

}


public class IteratorPattern {

    public IteratorPattern() {

        System.out.println("Using a list container for fruits: ");

        //Use iterator like any other
        ListFruitsContainer listFruits = new ListFruitsContainer();
        listFruits.addFruit(new Fruit("Apple"));
        listFruits.addFruit(new Fruit("Orange"));
        listFruits.addFruit(new Fruit("Banana"));

        //Now iterate through them and print
        //Note we could have a different fruit container now, and the same client code would work... (see below)
        IFruitContainerIterator itr = listFruits.getIterator();

        while(itr.hasNext()) {
            Fruit f = itr.next();
            System.out.println(f.getName());
        }



        //Same code to iterate as above but with a different fruit container:

        System.out.println("\nUsing a map container for fruits: ");

        MapFruitsContainer mapFruits = new MapFruitsContainer();
        mapFruits.addFruit(new Fruit("Apple"), 2);
        mapFruits.addFruit(new Fruit("Orange"), 5);
        mapFruits.addFruit(new Fruit("Banana"), 3);

        //Now iterate through them and print
        //Note we could have a different fruit container now, and the same client code would work... (see below)
        IFruitContainerIterator itr2 = mapFruits.getIterator();

        while(itr2.hasNext()) {
            Fruit f = itr2.next();
            System.out.println(f.getName());
        }

        //Lastly note how the order is not guaranteed because the implementations of the iterator are not the same.
        //But the important part is that we can simply ask for an iterator and use it with the interface,
        // the client does not care about the implementation details of iteration.

    }

}
