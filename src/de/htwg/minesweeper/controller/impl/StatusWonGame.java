package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.model.Model;

public class StatusWonGame implements IStatus{

	@Override
	public Model newGame(Model field) {
		field = new Model(10, 10, 10);
		return field;
	}


}
