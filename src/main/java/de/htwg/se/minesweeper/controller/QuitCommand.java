package de.htwg.se.minesweeper.controller;

public class QuitCommand implements ICommand{
	private Quit quit;
	
	public QuitCommand(Quit quit) {
		this.quit = quit;
	}
	
	@Override
	public void execute() {
		quit.run();
	}

}
