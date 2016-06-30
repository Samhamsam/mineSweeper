package de.htwg.minesweeper.controller;

public class Help {
	
	private String helpText;
	
	public void run()
	{
		String text = "(TUI:n) GUI:	Menu	->	New Game: 	This command starts a new game. (reset)\n"
					+ "(TUI:q) GUI:	Menu	->	Quit:		This command ends the Game and close it\n"
					+ "(TUI:h) GUI:	?	->	Help:		This command shows the help text";
		setHelpText(text);
	}
	
	private void setHelpText(String helpText)
	{
		this.helpText = helpText;
	}
	
	public String getHelpText()
	{
		return helpText;
	}
}
