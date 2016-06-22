package de.htwg.minesweeper.controller;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.minesweeper.model.FieldTest;
import de.htwg.minesweeper.model.Model;
import util.observer.Observable;

public class Controller extends Observable{
	
	private static final Logger log = LogManager.getLogger();

	Model field;
	
	private int firstNumber;
	private int secondNumber;
	
	private String statusText;
	private String FeldText[][]; 
	
	private int row = 10, column = 10, numberOfMines = 100;
	
	public Controller(){
		field = new Model(row, column, numberOfMines);
	}

	
	public void spielFeld() {
		FeldText = field.getUserField();
	}
	
	
	
	public boolean startgame(String answer) {
		long timestart = System.nanoTime();
		int[] AnswerList = {};
		boolean isItABomb = false;
		boolean gameNotlost = true;
		
		statusText = "Type: x,x | x is a number between 0 and 9(column, row):";

		List<String> list = Arrays.asList(answer.split(","));
		
		if(list.size() == 2){
			AnswerList = stringToNumber(list);
			isItABomb = IsItaBomb(AnswerList[0],AnswerList[1]);
			/*
			if(checkIfGameIsWon()){
				long timeEnd = System.nanoTime();
				long elapsedTime = timeEnd-timestart;
				long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
				statusText = "Game Won";
				notifyObservers();
				//iview.wonGame(time);
				return;
			}
			*/
			if(isItABomb){
				//iview.lostGame();
				statusText = "Game lost";
				gameNotlost = false;
			}
		}
		else if(list.size() == 3){
			setFlag(list);
		}

		spielFeld();
		
		notifyObservers();

		return gameNotlost;
	}
	
	boolean checkIfGameIsWon(){
		boolean gameWon = false;
		System.out.println(field.getsizeOfxAndfWithBomb());
		System.out.println(field.getsizeOfxAndfWithoutBomb());
		if (field.getsizeOfxAndfWithBomb() == field.getsizeOfxAndfWithoutBomb() ){
			gameWon = true;
		}
		field.resetSizeOFBoMB();
		return gameWon;
	}
	
	int[] stringToNumber(List<String> list){
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
	
	void setFlag(List<String> flag){
		int numberi = Integer.parseInt(flag.get(0));
		int numberj = Integer.parseInt(flag.get(1));
		String testField = field.getUserFieldSimple(numberi, numberj);
		if(testField.equals("x"))
			field.setFlag(numberi, numberj);
	}
	
	boolean IsItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if(fi[firstNumber][secondNumber].equals("b")){
			return true;
		}
		field.setUserField(firstNumber,secondNumber);	
		return false;
	}
	
	
	
	
	
	
	public String getStatusText()
	{
		return statusText;
	}
	public String[][] getFeldText(){
		return FeldText;
	}
	
	
	public void exitGame(){
		Runtime.getRuntime().halt(0);
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int i){
		row = i;
	}
}
