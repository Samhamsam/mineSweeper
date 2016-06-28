package de.htwg.minesweeper.controller;

public class Help {
	
	private String helpText;
	
	public void run()
	{
		String text = "HILFE HILFE HILFE HILFE";
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
