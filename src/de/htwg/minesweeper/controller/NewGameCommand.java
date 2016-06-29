package de.htwg.minesweeper.controller;

public class NewGameCommand implements ICommand{
	NewGame newGame;
	
	public NewGameCommand(NewGame newGame) {
		this.newGame = newGame;
	}
	@Override
	public void execute() {
		newGame.run();
		
	}

}
