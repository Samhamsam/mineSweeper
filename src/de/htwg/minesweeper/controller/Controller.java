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
	private int numberOfMines = 20;



	public Controller(){
		feldText = new String[10][10];
		field = new Model(ROW, COLUMN, numberOfMines);
	}
	
	public void newGame(){
		field = new Model(ROW, COLUMN, numberOfMines);
		firstStart = true;
		fieldPosition = "n";
		setStatusCode(1);
		notifyObservers();
	}

	
	public void spielFeld() {
		setFeldText(field.getUserField());
	}
	
	
	
	public boolean startgame(String answer) {
		int[] AnswerList = {};
		
		if(firstStart == true){
			setStartTime(System.nanoTime());
		}
		firstStart = false;
		
		fieldPosition = answer;
		
		boolean gameNotlost = true;
		
		setStatusCode(1);

		List<String> list = Arrays.asList(answer.split(","));
		
		if(list.size() == 2){
			AnswerList = stringToNumber(list);
			boolean ItsABomb = IsItaBomb(AnswerList[0],AnswerList[1]);
			
			if(ItsABomb){
				setStatusCode(2);
				gameNotlost = false;
			}
			
			else if(checkIfGameIsWon()){
				timeEnd = System.nanoTime();
				long elapsedTime = timeEnd - timestart;
				wonTime = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

				setStatusCode(3);
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
		if("x".equals(testField))
			field.setFlag(numberi, numberj);
	}
	
	boolean IsItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if("b".equals(fi[firstNumber][secondNumber])){
			field.setUserFieldSimple(firstNumber, secondNumber, "b");
			return true;
		}
		field.setUserField(firstNumber,secondNumber);	
		return false;
	}
	
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public void setStatusCode(int code){
		this.statusCode = code;
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

	public String[][] getFeldText(){
		return field.getUserField();
	}
	
	public void setFeldText(String feldText[][]) {
		this.feldText = feldText;
	}
	
	public String getTimeWon(){
		return String.valueOf(wonTime);
	}
	
	public boolean isFirstStart() {
		return firstStart;
	}

	public void setFirstStart(boolean firstStart) {
		this.firstStart = firstStart;
	}
	
	public int getNumberOfMines() {
		return numberOfMines;
	}

	public void setNumberOfMines(int numberOfMines) {
		this.numberOfMines = numberOfMines;
	}
	
}
