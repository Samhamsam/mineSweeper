package de.htwg.minesweeper;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Using Command Prompt, allows user to start new game or to quit
public class TUI {
	
	private static String[][] filledField;
	boolean gameover = true;
	
	public static void main (String args[]) {
	
	System.out.println("What would you like to do?"); //Prints to console
	System.out.println("1. Start New Game");		//Prints to console
	System.out.println ("2. Quit");					//Prints to console
	System.out.println ("Type 1 or 2 below.");	//Prints to console
	
	String answer = "";		//Initialized variable for user's inputed string
	String newAnswer = "";	// Initialized variable for user's nth inputed string
	
	Scanner ScanIn = new Scanner(System.in);  //Scanner setup
	answer = ScanIn.nextLine();					// Scanner reads the inputed String and stores it as answer
	
	Scanner ScanIn2 = new Scanner(System.in); // Scanner setup for repeated inputs in case of error
	
	
	if (answer.equals("1") || newAnswer.equals("1") ) {   // Checks that the input from user is equal exactly to the first option
		Field testfield = new Field();
		TUI testTUI = new TUI();
		filledField = testfield.setupField();
		String testString = testTUI.printField(filledField);
		System.out.println(testString);
		while(testTUI.gameover){
			System.out.println("Which field do you want to open? Type Example: 2,2");
			answer = ScanIn.nextLine();
			System.out.println(answer);
			if(answer.equals("2")){
				System.out.println("Thank you for playing this game!");
				System.exit(0);
			}else if(answer.length() != 3){
				System.out.println("False!");
			} else{
				List<String> list = Arrays.asList(answer.split(","));
				int first = Integer.parseInt(list.get(0));
				int second = Integer.parseInt(list.get(1));
				
			}
		}
		
	} else if (answer.equals("2") || newAnswer.equals("2")) {  //Checks that input from user is equal exactly to second option
		System.out.println("Finished"); //Print to console
	} else {
		System.out.println("That is not a possible choice. Try again");  //If anything else, user must try again
		newAnswer = ScanIn2.nextLine() ; // reads new input from user and stores it as a new answer to be compared
	}
	ScanIn.close();
	ScanIn2.close();
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
