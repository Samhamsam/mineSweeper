package de.htwg.minesweeper.aview.tui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.util.Observable;
import java.util.Observer;


//Using Command Prompt, allows user to start new game or to quit
public class TUI implements Observer {
	private static final Logger log = LogManager.getLogger();

	 
	public void printConsole(){
		log.info("What would you like to do?");
		log.info("What would you like to do?"); //Prints to console
		log.info("1. Start New Game");		//Prints to console
		log.info("2. Quit");					//Prints to console
		log.info("Type 1 or 2 below.");	//Prints to console
	}
	
	public void printGoodby(){
		log.info("Thanks and Goodby!");
	}
	
	public void printTheAnswer(){
		log.info("Type: x,x | x is a number between 0 and 9(column, row)");
	}
	public void gameLost(){
		log.info("You lost the Game!");
	}

	public void printTheField(String filledField[][]){

		for(String[] row: filledField){
			for(String value: row){
				
				log.info("%-20s",value);
			}
		}
	}

	
	public void playAgain() {
		log.info("Would you like to play again?");
		log.info("Input 1, if you would like to start a new game.");
		log.info("Input 2, if you would like to quit.");
	}
	
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	
}	
