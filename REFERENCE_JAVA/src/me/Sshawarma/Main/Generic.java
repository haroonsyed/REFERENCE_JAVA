package me.Sshawarma.Main;

public class Generic {
	
	Generic(){
		
		Integer[] iArr = {1, 2, 3, 4, 5, 6};
		Character[] cArr = {'a', 'u', '3', '.'};
		
		printMe(iArr);
		printMe(cArr);
		
	}
	
	//<T> indicated generic function, stands for Type
	//No need to overload a function for  abunch of different types.
	public <T> void printMe(T[] arr) {
		
		for(T o : arr) {
			System.out.printf("%s ", o);
		}
		
		System.out.println();
		
	}
	
}
