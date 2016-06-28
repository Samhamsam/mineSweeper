package de.htwg.minesweeper.controller;

import util.observer.IObservable;

public interface IController extends IObservable{
	
	public void exitGame();
	
	public void newGame();
	
	public void startGame(String answer);
	
	public String[][] getFeldText();
	
	public String getFieldPosition();
	
	public int getStatusCode();
	
	public String getTimeWon();
	
	public int getRow();
	
	public int getColumn();
}
