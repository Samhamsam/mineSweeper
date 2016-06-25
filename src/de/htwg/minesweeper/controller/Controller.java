package de.htwg.minesweeper.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;

import de.htwg.minesweeper.model.Model;
import util.observer.Observable;

public class Controller extends Observable{

	private static final String FIRST_FIELD_POSITION = "";
	
	private String fieldPosition = FIRST_FIELD_POSITION;

	Model field;
	
	private int firstNumber;
	private int secondNumber;
	
	private boolean firstStart = true;
	
	private int statusCode;
	private String[][] feldText; 
	
	private long timestart;
	private long timeEnd;
	private long wonTime;
	
	private static final int ROW = 10; 
	private static final int COLUMN = 10; 
	private static final int NUMBER_MINES = 4;
	
	public Controller(){
		feldText = new String[10][10];
		field = new Model(ROW, COLUMN, NUMBER_MINES);
	}
	
	public void newGame(){
		field = new Model(ROW, COLUMN, NUMBER_MINES);
		firstStart = true;
		fieldPosition = "n";
		statusCode = 1;
		notifyObservers();
	}

	
	public void spielFeld() {
		setFeldText(field.getUserField());
	}
	
	
	
	public boolean startgame(String answer) {

		if(firstStart == true){
			setStartTime(System.nanoTime());
		}
		firstStart = false;
		fieldPosition = answer;
		int[] AnswerList = {};
		boolean gameNotlost = true;
		
		statusCode = 1;

		List<String> list = Arrays.asList(answer.split(","));
		
		if(list.size() == 2){
			AnswerList = stringToNumber(list);
			boolean ItsABomb = IsItaBomb(AnswerList[0],AnswerList[1]);
			
			if(ItsABomb){
				//iview.lostGame();
				statusCode = 2;
				gameNotlost = false;
			}
			
			else if(checkIfGameIsWon()){
				timeEnd = System.nanoTime();
				long elapsedTime = timeEnd - timestart;
				wonTime = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

				statusCode = 3;
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
		if (field.getsizeOfxAndfWithBomb() == field.getsizeOfxAndfWithoutBomb() ){
			gameWon = true;
		}
		field.resetSizeOFBoMB();
		return gameWon;
	}
	
	int[] stringToNumber(List<String> list){
		int[] i;
		i = new int[2];
		firstNumber = Integer.parseInt(list.get(0));
		secondNumber = Integer.parseInt(list.get(1));
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
			field.setUserFieldSimple(firstNumber, secondNumber, "b");
			return true;
		}
		field.setUserField(firstNumber,secondNumber);	
		return false;
	}
	
	public int getStatusText()
	{
		return statusCode;
	}
	
	private String getFieldPositionPrivat(){
		return fieldPosition;
	}
	
	public String getFieldPosition(){
		String text = "";
		if(!getFieldPositionPrivat().isEmpty()){
			text = "You typed: " + getFieldPositionPrivat();
		}
		return text;
	}

	private void setStartTime(long time){
		timestart = time;
	}
	
	public void exitGame(){
		Runtime.getRuntime().halt(0);
	}
	
	public int getRow(){
		return ROW;
	}

	public String[][] getFeldText(){
		return field.getUserField();
	}
	
	public void setStatusCode(int code){
		this.statusCode = code;
	}
	
	public void setFeldText(String feldText[][]) {
		this.feldText = feldText;
	}
	
	public String getTimeWon(){
		return String.valueOf(wonTime);
	}
}
