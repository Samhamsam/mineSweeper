package de.htwg.minesweeper.controller;

import de.htwg.minesweeper.model.Model;
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
	
	public void hilfe();
	
	public String getHelpText();
	public void setHelpText(String helpText);

	public void setStatusCode(int i);

	void newGame(Model field);
}
