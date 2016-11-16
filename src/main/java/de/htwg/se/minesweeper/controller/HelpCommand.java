package de.htwg.se.minesweeper.controller;

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
