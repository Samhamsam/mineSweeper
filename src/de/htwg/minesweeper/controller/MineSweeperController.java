package de.htwg.minesweeper.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.minesweeper.aview.Iview;
//import de.htwg.minesweeper.aview.gui.GUI;
import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.model.Field;

public class MineSweeperController{
	
	private static final Logger log = LogManager.getLogger();
	
	private TUI tui;
	private Field field;
	private Iview iview;
	
	private int firstNumber;
	private int secondNumber;
	
	private static final String startgame ="1";
	private static final String exitgame = "2";
	
	private int row = 10, column = 10, numberOfMines = 2;
	
	public MineSweeperController(){
		this.tui = new TUI();
		this.iview = new TUI();
		this.field = new Field(row, column, numberOfMines);
	}
	
	public void run(){
		iview.start();
		answerOptions(tui.scanner());
	}
	
	private void answerOptions(String answer){
		switch(answer){
			case startgame:
				startgame();
			break;
			
			case exitgame:
				iview.endGame();
				exitGame();
			break;
			
			
			default:
				log.info("Not a valid answer. ");
				log.info("\"start\" starts the Game.");
				log.info("\"exit\" quits this application.");
				log.info("Please select 1 or 2.");			
				answerOptions(tui.scanner());   //creates a cycle allowing user multiple chances to input acceptable answer
				answerOptions(answer);
		}
	}
	
	
	private void startgame() {
		long timestart = System.nanoTime();
		field.setupField();
		int[] AnswerList = {};
		boolean isItABomb = false;
		StringBuilder tt = field.printField(field.getfilledField());
		System.out.println(tt.toString());
		while(!isItABomb){
			tui.printTheAnswer();
			List<String> list = Arrays.asList(tui.scanner().split(","));
			if(list.size() == 2){
				AnswerList = stringToNumber(list);
			}
			else if(list.size() == 3){
				setFlag(list);
			}
			else{
				tui.notCorrectInput();
			}
			if(list.size() == 2)
				isItABomb = IsItaBomb(AnswerList[0],AnswerList[1]);
			StringBuilder t = field.printField(field.getUserField());
			log.info(t.toString());
			if(checkIfGameIsWon()){
				long timeEnd = System.nanoTime();
				long elapsedTime = timeEnd-timestart;
				long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
				iview.wonGame(time);
				return;
			}
		}
		if(isItABomb){
			iview.lostGame();
		}
		tui.playAgain();
		answerOptions(tui.scanner());
	}
	
	private boolean checkIfGameIsWon(){
		boolean gameWon = false;
		if (field.getsizeOfxAndfWithBomb() == field.getsizeOfxAndfWithoutBomb() ){
			gameWon = true;
		}
		field.resetSizeOFBoMB();
		return gameWon;
	}
	
	private int[] stringToNumber(List<String> list){
		int[] i;
		i = new int[2];
		try{
			firstNumber = Integer.parseInt(list.get(0));
			secondNumber = Integer.parseInt(list.get(1));
		}
		catch (ArrayIndexOutOfBoundsException ah ){
			log.error("You forgot to input the second coordinate!" + ah.getMessage());
		}
		i[0] = firstNumber;
		i[1] = secondNumber;
		return i;
	}
	
	private void setFlag(List<String> flag){
		int numberi = Integer.parseInt(flag.get(0));
		int numberj = Integer.parseInt(flag.get(1));
		String testField = field.getUserFieldSimple(numberi, numberj);
		if(testField.equals("x"))
			field.setFlag(numberi, numberj);
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
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int i){
		row = i;
	}
}
