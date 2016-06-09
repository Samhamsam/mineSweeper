package de.htwg.minesweeper;

import de.htwg.minesweeper.aview.gui.GUI;
import de.htwg.minesweeper.controller.MineSweeperController;

public class Main {
	
	public static void main (String args[]) {
		
		MineSweeperController control = new MineSweeperController();
		control.run();
		
		}
}
