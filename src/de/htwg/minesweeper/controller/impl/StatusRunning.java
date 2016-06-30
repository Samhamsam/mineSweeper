package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.controller.IStatus;

public class StatusRunning implements IStatus {

	@Override
	public boolean endStatus() {
		return true;
	}

}
