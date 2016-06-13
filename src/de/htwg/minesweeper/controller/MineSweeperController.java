package de.htwg.minesweeper.controller;

import java.util.*;

//import de.htwg.minesweeper.aview.gui.GUI;
import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.model.Field;

public class MineSweeperController{
	
	private String answer;
	private TUI tui;
	private Field field;
	private int firstNumber;
	private int secondNumber;
	private static final String startgame ="1";
	private static final String exitgame = "2";
	
	private int row = 10, column = 10, numberOfMines = 10;
	
	public MineSweeperController(){
		this.tui = new TUI();
		this.field = new Field(row, column,numberOfMines);

	}
	private String setanswer(){
		@SuppressWarnings("resource")
		Scanner ScanIn = new Scanner(System.in);  //Scanner setup
		answer = ScanIn.nextLine();
		if(answer.equals(exitgame)){
			tui.printGoodby();
			exitGame();
		}
		return answer;
	}
	
	public void run(){
		tui.printConsole();
		answer = setanswer();
		answerOptions(answer);
	}
	
	private void answerOptions(String answer){
		switch(answer){
		case startgame:
			startgame();
		break;
		
		case exitgame:
			tui.printGoodby();
			exitGame();
		break;
		
		
		default:
			System.out.println("Not a valid answer. ");
			System.out.println("\"start\" starts the Game.");
			System.out.println("\"exit\" quits this application.");
			System.out.println("Please select 1 or 2.");			
			setanswer();   //creates a cycle allowing user multiple chances to input acceptable answer
			answerOptions(answer);
		}
	}
	
	
	private void startgame(){
		field.setupField();
		int[] i;
		boolean isitaNumber = false;
		StringBuilder tt = field.printField(field.getfilledField());
		System.out.println(tt.toString());
		
		while(!isitaNumber){
			StringBuilder t = field.printField(field.getUserField());
			System.out.println(t.toString());
			tui.printTheAnswer();
			answer = setanswer();
			i = stringToNumber(answer);
			isitaNumber = IsItaBomb(i[0],i[1]);
		}
		
		//Ends the game when a mine is chosen.
		//Gives the option to start a new game
		//And input the players decision
		tui.gameLost();
		tui.playAgain();
		setanswer();
		answerOptions(answer);
	}
	
	private int[] stringToNumber(String answer){
		int[] i;
		i = new int[2];
		try{
			List<String> list = Arrays.asList(answer.split(","));
			 firstNumber = Integer.parseInt(list.get(0));
			 secondNumber = Integer.parseInt(list.get(1));
		}
		catch(NumberFormatException er){
			System.out.println(answer + " is not a Number" + er.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException ah ){
			System.out.println("You forgot to input the second coordinate!" + ah.getMessage());
		}
		i[0] = firstNumber;
		i[1] = secondNumber;
		return i;
	}
	
	private boolean IsItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if(fi[firstNumber][secondNumber].equals("b")){
			return true;
		}
		field.setUserField(firstNumber,secondNumber);	
		return false;
	}
	private void exitGame(){
		Runtime.getRuntime().halt(0);
	}
}
