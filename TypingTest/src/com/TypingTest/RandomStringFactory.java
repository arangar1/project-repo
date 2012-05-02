package com.TypingTest;

import java.util.Random;

/**
 * Gives the sample text area random strings from the string pool in the class
 * @author Ajay
 *
 */
public class RandomStringFactory {
	
	private static int next = 0;
	private static int NUM_ELEMENTS = 10;
	private static Random generator;
	
	private static final String s1 = "The JTextArea is simply a large JTextField that can display or take in more than one line of text at a time.";
	private static final String s2 = "In the classic Swing architecture, events on the model fire to the view.";
	private static final String s3 = "The Mediator Design Pattern simply implies that the controller is going to buffer what's going on in both the model and in the view.";
	private static final String s4 = "The view renders the contents of a model.";
	private static final String s5 = "Welcome to the typing test.";
	private static final String s6 = "The test will stop when user types a string as long as me.";
	private static final String s7 = "After the test please see your evaluated results on the side panel.";
	private static final String s8 = "This is an easy string to type.";
	private static final String s9 = "This$ string& has* many^ special characters +.";
	private static final String s10 = "Shortest string ever.";
	private static final String defaultStr = "Type this default String in case the random numbers are out of range";
		
	/**
	 * Constructor
	 */
	RandomStringFactory(){
		generator = new Random();
	}
	
	/**
	 * Generates random number within the limits
	 * @return
	 */
	public int generateRandomInt(){
		return next = generator.nextInt(NUM_ELEMENTS) + 1;
	}
	
	/**
	 * Returns a Random string
	 * @return
	 */
	String generateString(){		
		
		int a = generateRandomInt();
		
		if(a == 1)
			return s1;
		if(a == 2)
			return s2;
		if(a == 3)
			return s3;
		if(a == 4)
			return s4;
		if(a == 5)
			return s5;
		if(a == 6)
			return s6;
		if(a == 7)
			return s7;
		if(a == 8)
			return s8;
		if(a == 9)
			return s9;
		if(a == 10)
			return s10;
		else
			return defaultStr;
	
	}
}
