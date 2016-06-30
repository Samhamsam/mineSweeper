package de.htwg.minesweeper.controller;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.minesweeper.controller.impl.*;
import de.htwg.minesweeper.model.Model;
import util.observer.Observable;

public class Controller extends Observable implements IController{
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String FIRST_FIELD_POSITION = "";
	
	private static final int ROW = 15; 
	private static final int COLUMN = 15;
	private static final int NUMBER_OF_MINES = 10;
	
	private static final int STATUS_CODE_INFO_TEXT = 1;
	private static final int STATUS_CODE_WON_GAME = 3;
	private static final int STATUS_CODE_GAME_LOST = 2;
	private static final int STATUS_CODE_HELP_TEXT = 4;
	private static final int STATUS_CODE_ERROR = 5;
	
	private static final String HELP_COMMAND = "h";
	private static final String NEW_GAME_COMMAND = "n";

	
	private int row = ROW;
	private int column = COLUMN;
	private int numberOfMines = NUMBER_OF_MINES;
	
	private String fieldPosition = FIRST_FIELD_POSITION;
	
	Context context;
	Model field;
	
	private int firstNumber;
	private int secondNumber;
	
	private boolean firstStart = true;
	
	
	private int statusCode;
	
	
	private long timestart;
	private long timeEnd;
	private long wonTime;
	

	
	private ICommand command;
	private String helpText;

	public Controller(Model field){
		this.field = field; 
		context = new Context();
		setStatusRunning();
	}
	
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
		fieldPosition = HELP_COMMAND;
		setStatusCode(STATUS_CODE_HELP_TEXT);
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
	public void newGame(Model field){
		this.field = field;
		firstStart = true;
		fieldPosition = NEW_GAME_COMMAND;
		setStatusCode(STATUS_CODE_INFO_TEXT);
		notifyObservers();
		setStatusRunning();
	}
	
	@Override
	public void newGame(){
		field = new Model(row, column, numberOfMines);
		firstStart = true;
		fieldPosition = NEW_GAME_COMMAND;
		setStatusCode(STATUS_CODE_INFO_TEXT);
		notifyObservers();
		setStatusRunning();
	}

	
	@Override
	public void startGame(String answer) {
		int[] answerList = {};
		List<String> list = Arrays.asList(answer.split(","));
		
		if(context.endStatus() && (list.size()>1) && (list.size()<4)){
			
			if(firstStart){
				setStartTime(System.nanoTime());
			}
			firstStart = false;
			
			fieldPosition = answer;
			
			setStatusCode(STATUS_CODE_INFO_TEXT);
			
			
			if(list.size() == 2){
				
				answerList = stringToNumber(list);
				boolean itsABomb = isItaBomb(answerList[0],answerList[1]);
				
				if(itsABomb){
					setStatusCode(STATUS_CODE_GAME_LOST);
					setStatusPressedBomb();
				}			
				else if(checkIfGameIsWon()){
					timeEnd = System.nanoTime();
					long elapsedTime = timeEnd - timestart;
					wonTime = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
					setStatusWonGame();
					setStatusCode(STATUS_CODE_WON_GAME);
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
		try {
			firstNumber = Integer.parseInt(list.get(0));
			secondNumber = Integer.parseInt(list.get(1));
		} catch (Exception e) {
			setStatusCode(STATUS_CODE_ERROR);
			notifyObservers();
			LOGGER.error(e);
		}

		i[0] = firstNumber;
		i[1] = secondNumber;
		return i;
	}
	
	void setFlag(List<String> flag){
		try{
			int numberi = Integer.parseInt(flag.get(0));
			int numberj = Integer.parseInt(flag.get(1));
			
			String testField = field.getUserFieldSimple(numberi, numberj);
			if(field.getUserHiddenField().equals(testField)){
				field.setFlag(numberi, numberj);
			}
			else if(field.getFlagString().equals(testField)){
				field.resetFlag(numberi,numberj);
			}
		}
		catch (Exception e) {
			setStatusCode(STATUS_CODE_ERROR);
			notifyObservers();
			LOGGER.error(e);
		}
	}
	
	boolean isItaBomb(int i, int j){
		String[][] fi = field.getfilledField();
		if(field.getBombString().equals(fi[i][j])){
			field.setUserFieldSimple(i, j, field.getBombString());
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
	
	@Override
	public void setStatusCode(int code){
		this.statusCode = code;
	}
	
	String getFieldPositionPrivat(){
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
