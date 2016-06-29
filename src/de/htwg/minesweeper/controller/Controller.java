package de.htwg.minesweeper.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;


import de.htwg.minesweeper.controller.impl.Context;
import de.htwg.minesweeper.controller.impl.StatusPressedBomb;
import de.htwg.minesweeper.controller.impl.StatusRunning;
import de.htwg.minesweeper.controller.impl.StatusWonGame;
import de.htwg.minesweeper.model.Model;
import util.observer.Observable;

public class Controller extends Observable implements IController{

	private static final String FIRST_FIELD_POSITION = "";
	
	Context context;

	private String fieldPosition = FIRST_FIELD_POSITION;

	Model field;
	
	private int firstNumber;
	private int secondNumber;
	
	private boolean firstStart = true;
	
	
	private int statusCode;
	
	
	private long timestart;
	private long timeEnd;
	private long wonTime;
	
	private static final int ROW = 15; 
	private static final int COLUMN = 15;
	private static final int NUMBER_OF_MINES = 10;
	
	private int row = ROW;

	private int column = COLUMN;

	private int numberOfMines = NUMBER_OF_MINES;
	private ICommand command;
	private String helpText;

	public Controller(){
		field = new Model(row, column, numberOfMines);
		context = new Context();
		setStatusRunning();
	}
	
	public void setCommand(ICommand command){
		this.command = command;
	}
	
	@Override
	public void hilfe() {
		Help help = new Help();
		ICommand commandHelp = new HelpCommand(help);
		setCommand(commandHelp);
		command.execute();
		setHelpText(help.getHelpText());
		fieldPosition = "h";
		setStatusCode(4);
		notifyObservers();
	}
	
	@Override
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}
	
	@Override
	public String getHelpText() {
		return helpText;
	}
	
	
	
	
	@Override
	public void newGame(){
		field = new Model(row, column, numberOfMines);
		firstStart = true;
		fieldPosition = "n";
		setStatusCode(1);
		notifyObservers();
		setStatusRunning();
	}

	
	@Override
	public void startGame(String answer) {
		if(context.endStatus()){
			
			int[] answerList = {};
			
			if(firstStart){
				setStartTime(System.nanoTime());
			}
			firstStart = false;
			
			fieldPosition = answer;
			
			setStatusCode(1);
			
	
			List<String> list = Arrays.asList(answer.split(","));
			
			if(list.size() == 2){
				
				answerList = stringToNumber(list);
				boolean itsABomb = isItaBomb(answerList[0],answerList[1]);
				
				if(itsABomb){
					setStatusCode(2);
					setStatusPressedBomb();
				}			
				else if(checkIfGameIsWon()){
					timeEnd = System.nanoTime();
					long elapsedTime = timeEnd - timestart;
					wonTime = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
					setStatusWonGame();
					setStatusCode(3);
				}
				else{
					setStatusRunning();
				}
				
	
			}
			else if(list.size() == 3){
				setFlag(list);
			}
			notifyObservers();
		}

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
		if("x".equals(testField)){
			field.setFlag(numberi, numberj);
		}
		else if("f".equals(testField)){
			field.resetFlag(numberi,numberj);
		}
	}
	
	boolean isItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if("b".equals(fi[i][j])){
			field.setUserFieldSimple(i, j, "b");
			return true;
		}
		field.setUserField(i,j);	
		return false;
	}
	
	@Override
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
	
	@Override
	public String getFieldPosition(){
		String text = "";
		if(!getFieldPositionPrivat().isEmpty()){
			text = getFieldPositionPrivat();
		}
		return text;
	}

	private void setStartTime(long time){
		timestart = time;
	}
	
	@Override
	public void exitGame(){
		Quit quit = new Quit();
		ICommand commandQuit = new QuitCommand(quit);
		setCommand(commandQuit);
		command.execute();
	}
	
	@Override
	public String[][] getFeldText(){
		return field.getUserField();
	}
	
	@Override
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
	private void setStatusRunning(){
		context.setStatus(new StatusRunning());
	}
	private void setStatusPressedBomb(){
		context.setStatus(new StatusWonGame());
	}
	private void setStatusWonGame(){
		context.setStatus(new StatusPressedBomb());
	}
	
	@Override
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	
}
