package de.htwg.minesweeper;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TUI {
	
	private static final Logger logger = Logger.getLogger(TUI.class.getName());
	
	private static String[][] filledField;
	boolean gameover = true;
	
	public static void main (String args[]) {
	
	Handler consoleHandler = null;
	try{
		//creates consoleHandler
		consoleHandler = new ConsoleHandler();
		
		//assign handlers to logger object
		 logger.addHandler(consoleHandler);
		
		//sets levels to handlers and logger
		consoleHandler.setLevel(Level.ALL);
		logger.setLevel(Level.ALL);
	} finally{	
	}
	
	logger.info("Welcome to Minesweeper!"
			+ "To begin a new game, type 'new'."
			+ "To quit, simply type 'quit'");
	
	String answer = "";
	
	Scanner ScanIn = new Scanner(System.in);  //Scanner setup
	answer = ScanIn.nextLine();	
	
	if (answer.equals("new")) {   // Checks that the input from user is equal exactly to the first option
		Field testfield = new Field();
		TUI testTUI = new TUI();
		filledField = testfield.setupField();
		String testString = testTUI.printField(filledField);
		System.out.println(testString);
		while(testTUI.gameover){
			logger.info("Type the size of the field you want to open. Example: 2,2");
			answer = ScanIn.nextLine();
			if(answer.equals("quit")){
				logger.info("Thank you for playing this game!");
				System.exit(0);
			}else if(answer.length() != 3){
				logger.info("False!");
			} else{
				List<String> list = Arrays.asList(answer.split(","));
				int first = Integer.parseInt(list.get(0));
				int second = Integer.parseInt(list.get(1));
				
			}
		}
		
	} else if (answer.equals("quit")) {  //Checks that input from user is equal exactly to second option
		logger.info("Goodbye"); //Print to console
	} else {
		logger.info("That is not a possible choice. Try again");  //If anything else, user must try again
		answer = ScanIn.nextLine() ; // reads new input from user and stores it as a new answer to be compared
	}
	}
	
	/**
	 * Build String Field
	 * @param filledField
	 * @return
	 */

	private String printField(String[][] filledField){
		
		StringBuilder result = new StringBuilder();
		
		String changThisField[][] = filledField;
		
		for(int j = 0; j < 10; j++){
			
			for(int i= 0; i < 10; i++){
				
				if(changThisField[j][i] == "noMine"){
					result.append("0").append(" ");
				}
				else if(changThisField[j][i] == "Mine"){
					//Game over
					//Print the whole Field filledField
					//result.append("1").append(" ");
					OriginalField(filledField);
				}
				
				else{
					result.append("x").append(" ");
				}
				
			}
			result.append("\n");
		}
		
		
		return result.toString();
	}
	
	/**
	 * Prints the Field that was created on Field.java
	 * @param filledField
	 * @return String Field
	 */
	private String OriginalField(String[][] filledField){
		StringBuilder result = new StringBuilder();
		

		for(int j = 0; j < 10; j++){
			
			for(int i= 0; i < 10; i++){
				result.append(filledField[j][i]).append(" ");
			}
			result.append("\n");
		}
		
		return result.toString();
	}
}	
