package me.Sshawarma.Main;

public class StringMethods {
	//Just instantiate the class to see a demo
	StringMethods(){
		
		String[] words = {"funk", "chunk", "furry", "baconator"};
		
		for(String s : words) {
		
			if(s.startsWith("fu")) {
				System.out.println(s + " starts with \"fu\"");
				System.out.println(s.indexOf('u'));
			}
			
			if(s.equals("funk")) {
				System.out.println(s.indexOf("nk"));
			}
			
		}
		//Alternate way of concatenating strings:
		String combined = words[0].concat(words[1]);
		System.out.println("First two words in array concatenated: " + combined);
		
		//Replacing parts of a string
		System.out.println(combined.replace('f', 'h'));
		
		//Remove spaces
		String hi = "  hi     ";
		System.out.println("before:" + hi);
		//Remmeber strings ar eimmutable, meaning they cannot be modified and ,ust be returned as a new object adn assigned
		hi = hi.trim();
		System.out.println("After:" + hi);
		
	}
	
}
