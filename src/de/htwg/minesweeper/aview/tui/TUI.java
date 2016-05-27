package de.htwg.minesweeper.aview.tui;


import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

//Using Command Prompt, allows user to start new game or to quit
public class TUI implements Observer {
	
	
	public void printConsole(){
		System.out.println("What would you like to do?"); //Prints to console
		System.out.println("1. Start New Game");		//Prints to console
		System.out.println("2. Quit");					//Prints to console
		System.out.println("Type 1 or 2 below.");	//Prints to console
	}
	
	public void printGoodby(){
		System.out.println("Thanks and Goodby!");
	}
	
	public void printTheAnswer(){
		System.out.println("Type: x,x | x is a number between 0 and 9(column, row)");
	}
	public void gameLost(){
		System.out.println("You lost the Game!");
	}

	public void printTheField(String filledField[][]){

		for(String[] row: filledField){
			for(String value: row){
				
				System.out.format("%-20s",value);
			}
		}
	}

	
	public void playAgain() {
		System.out.println("Would you like to play again?");
		System.out.println("Input 1, if you would like to start a new game.");
		System.out.println("Input 2, if you would like to quit.");
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	
}	
