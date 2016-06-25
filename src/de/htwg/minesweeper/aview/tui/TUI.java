package de.htwg.minesweeper.aview.tui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.minesweeper.controller.Controller;
import util.observer.Event;
import util.observer.IObserver;

import java.io.InputStream;
import java.util.Scanner;



public class TUI implements IObserver {
	private static final Logger log = LogManager.getLogger();
	
	
	private Controller controller;
	
	
	public TUI(Controller controller){
		this.controller = controller;
		controller.addObserver(this);
	}


	public void printTheField(String filledField[][]){
		for(String[] row: filledField){
			for(String value: row){
				log.info("%-20s",value);
			}
		}
	}
	
	
	
	public boolean answerOptions(String answer){
		boolean continu = true;
		switch(answer){
			case "q":
				continu = false;
				controller.exitGame();
			break;
			
			case "n":
				controller.newGame();
			break;
			
			
			default:
				continu = controller.startgame(answer);
		}
		return continu;
	}
	

	
	public String scanner(InputStream in){
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(in);
		String input = keyboard.nextLine();
	    return input;
	}


	@Override
	public void update(Event e) {
		printTui();
	}

	
	public StringBuilder printField(String[][] field){
		StringBuilder result = new StringBuilder();
		for(int j = 0; j < 10; j++){
			for(int i= 0; i < 10; i++){
				result.append(field[i][j]).append(" ");
			}
			result.append("\n");
		}
		return result;
	}


	public void printTui() {
		StringBuilder stringBuilder;
		stringBuilder = printField(controller.getFeldText());
		
		log.info(controller.getFieldPosition() + "\n");
		
		log.info(stringBuilder.toString());
		
		if(controller.getStatusCode() == 1 || controller.getStatusCode() == 0)
			log.info("Type: x,x | x is a number between 0 and 9(column, row):\n");
		if(controller.getStatusCode() == 2)
			log.info("You Lost! New Game? Type: n");
		if(controller.getStatusCode() == 3){
			log.info("You Won! " + controller.getTimeWon() + " Points!");
			log.info("New Game? Type: n");
		}
	}
}	
