package de.htwg.minesweeper.controller;

import java.util.*;


import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.model.Field;

public class MineSweeperController{
	
	private String answer;
	private TUI tui;
	private Field field;
	private int firstNumber;
	private int secondNumber;
	
	public MineSweeperController(){
		this.tui = new TUI();
		this.field = new Field();

	}
	private String setanswer(){
		@SuppressWarnings("resource")
		Scanner ScanIn = new Scanner(System.in);  //Scanner setup
		answer = ScanIn.nextLine();
		if(answer.equals("2")){
			tui.printGoodby();
			System.exit(0);
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
		case "1":
			startgame();
		break;
		
		case "2":
			tui.printGoodby();
			System.exit(0);
		break;
		
		default:
			System.out.println("Not a valid answer. ");
			System.out.println("1 starts the Game.");
			System.out.println("2 quits this application.");
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
		tui.gameLost();
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
			System.out.println(answer + " is not a Number");
		}
		i[0] = firstNumber;
		i[1] = secondNumber;
		return i;
	}
	
	private boolean IsItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if(fi[firstNumber][secondNumber].equals("baad")){
			return true;
		}
		field.setUserField(firstNumber,secondNumber);	
		return false;
	}
}
