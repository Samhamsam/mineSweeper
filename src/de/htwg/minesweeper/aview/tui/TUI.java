package de.htwg.minesweeper.aview.tui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.minesweeper.aview.Iview;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


//Using Command Prompt, allows user to start new game or to quit
public class TUI implements Observer,Iview {
	private static final Logger log = LogManager.getLogger();

	public void notCorrectInput(){
		log.info("Not correct input!");
		printTheAnswer();
	}
	
	public void printTheAnswer(){
		log.info("Type: x,x | x is a number between 0 and 9(column, row)");
		log.info("For flag type: x,x,f");
	}	

	public void printTheField(String filledField[][]){
		for(String[] row: filledField){
			for(String value: row){
				log.info("%-20s",value);
			}
		}
	}
	
	public String scanner(){
		@SuppressWarnings("resource")
		Scanner ScanIn = new Scanner(System.in);  //Scanner setup
		return ScanIn.nextLine();
	}

	public void playAgain() {
		log.info("Would you like to play again?");
		log.info("Input 1, if you would like to start a new game.");
		log.info("Input 2, if you would like to quit.");
	}
	
	@Override
	public void start() {
		log.info("What would you like to do?");
		log.info("What would you like to do?"); //Prints to console
		log.info("1. Start New Game");		//Prints to console
		log.info("2. Quit");					//Prints to console
		log.info("Type 1 or 2 below.");	//Prints to console	
	}

	@Override
	public void wonGame(long time) {
		log.info("You won the Game!");
		log.info("You have a rank of "+ time);
	}

	@Override
	public void lostGame() {
		log.info("You lost the Game!");
	}

	@Override
	public void endGame() {
		log.info("Thanks and Goodby!");
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

}	
