package me.Sshawarma.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Collections {
	
	Collections(){
		
		String[] arr = {"eggs", "lasers", "hats", "pie"};
		//Use List as the interface to keep it more general!
		//Lists are of dynamic size
		List<String> list = new ArrayList<String>();
		
		//Add array to the list
		for(String s : arr) {
			list.add(s);
		}
		
		System.out.println(list.toString());
		
		//Iterating through a list, in this case we will remove laser and hats
		//Trying to modify with a loop will result in a concurrent modification error
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			//iter.next() increments the iterator
			String next = it.next();
			if(next.equals("lasers") || next.equals("hats")) {
				it.remove();
			}
		}
		
		System.out.println(list.toString());
		
		//Now showing off linked list
		List<String> LL = new LinkedList<String>();
		for(String s : arr) {
			LL.add(s);
		}
		//See how using List interface allows you to do things like this!
		//Remember LL is good for fast queue/dequeue. A list is more like an array, with faster lookup.
		LL.addAll(list);
		
		//If you want to init a List from an Array
		List<String> lFromArr = new ArrayList<String>(Arrays.asList(arr));
		System.out.println(lFromArr);
		//There is also a .toArray(new String[list.size()]) method
		
		System.out.println("Sorting List:");
		//This is a java 8 feature, otherwise use Collections.sort()
		lFromArr.sort(Comparator.naturalOrder());
		System.out.println(lFromArr);
		
		//Deep copy, just use constructor with new, or addall if you want to append
		//.frequency() tells the number of times an object appears in a list
		//.disjoint() checks if two lists have no elements in common
		
		//The other collections like stacks, queues, hashsets are self explanatory
		
		
	}
	
}
