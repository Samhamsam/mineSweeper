package de.htwg.minesweeper.aview;

public interface Iview {
	/**
	 * start the Game
	 */
	public void start();
	
	/**
	 * End the Game
	 * @param time: how much time the player needed
	 */
	public void wonGame(long time);
	
	/**
	 * If the Game is Lost
	 */
	public void lostGame();
	
	
	/**
	 * If the player wants to end the Game
	 */
	public void endGame();
}