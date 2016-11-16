package de.htwg.se.minesweeper.model.commands;

public class StatusRunning implements IStatus {

	@Override
	public boolean endStatus() {
		return true;
	}

}
