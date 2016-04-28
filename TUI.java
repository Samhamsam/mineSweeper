package minesweeper;
import java.util.Scanner;

//Using Command Prompt, allows user to start new game or to quit
public class TUI {
	
	public static void main (String args[]) {
	
	System.out.println("What would you like to do?"); //Prints to console
	System.out.println("1. Start New Game");		//Prints to console
	System.out.println ("2. Quit");					//Prints to console
	System.out.println ("Type 1. or 2. below.");	//Prints to console
	
	String answer = "";		//Initialized variable for user's inputed string
	String newAnswer = "";	// Initialized variable for user's nth inputed string
	
	Scanner ScanIn = new Scanner(System.in);  //Scanner setup
	answer = ScanIn.nextLine();					// Scanner reads the inputed String and stores it as answer
	
	Scanner ScanIn2 = new Scanner(System.in); // Scanner setup for repeated inputs in case of error
	
	
	if (answer.equals("1.") || newAnswer.equals("1.") ) {   // Checks that the input from user is equal exactly to the first option
		System.out.println("Begin Game"); // Prints to console
	}else if (answer.equals("2.") || newAnswer.equals("2.")) {  //Checks that input from user is equal exactly to second option
		System.out.println("Finished"); //Print to console
	} else {
		System.out.println("That is not a possible choice. Try again");  //If anything else, user must try again
		newAnswer = ScanIn2.nextLine() ; // reads new input from user and stores it as a new answer to be compared
	}
	ScanIn.close();
	ScanIn2.close();
	}
}	