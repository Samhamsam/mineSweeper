package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.controller.ICommand;

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
