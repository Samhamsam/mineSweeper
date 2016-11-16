package de.htwg.se.minesweeper.model.commands;

public class StatusPressedBomb implements IStatus{

	@Override
	public boolean endStatus() {
		return false;
	}

}
