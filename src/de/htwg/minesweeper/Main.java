package src.de.htwg.minesweeper;

import src.de.htwg.minesweeper.aview.tui.TUI;
import src.de.htwg.minesweeper.controller.MineSweeperController;
import src.de.htwg.minesweeper.model.Field;

//import tester.*;

public class Main {
	//static Examples E = new Examples ();
	
	public static void main (String args[]) {
		
		MineSweeperController control = new MineSweeperController();
		control.run();

		}
}
