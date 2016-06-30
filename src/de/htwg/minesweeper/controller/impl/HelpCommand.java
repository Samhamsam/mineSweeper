package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.controller.ICommand;

public class HelpCommand implements ICommand {

	private Help help;
	public HelpCommand(Help help) {
		this.help = help;
	}
	@Override
	public void execute() {
		help.run();		
	}

}
