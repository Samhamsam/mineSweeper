package de.htwg.minesweeper;

import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.controller.MineSweeperController;
import de.htwg.minesweeper.model.Field;

//import tester.*;

public class Main {
	//static Examples E = new Examples ();
	
	public static void main (String args[]) {
		
		MineSweeperController control = new MineSweeperController();
		control.run();

		}
}
