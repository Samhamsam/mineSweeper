package de.htwg.minesweeper.aview.tui;

// Imported from htwg.se.sudoku (markoboger)
// Edits by Lycoris1305

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.minesweeper.controller.IMinesweeperController;
import de.htwg.minesweeper.util.observer.Event;
import de.htwg.minesweeper.util.observer.IObserver;

public class TextUI implements IObserver{
	
	private static final int DOTSIZE = 1;
	private static final int PLUSSIZE = 2;
	private static final int HASHSIZE = 3;
	private static final String NEWLINE = System.getProperty("line.separator");

	private IMinesweeperController controller;

	private Logger logger = Logger.getLogger("de.htwg.minesweeper.aview.tui");

	@Inject
	public TextUI(IMinesweeperController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	// @Override
	public void update(Event e) {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean continu = true;
		if (line == "q") {
			continu = false;
		} else if (line.matches("\\D")) { // a one character non-digit input
			handleSingleCharInput(line);
		} else {
			logger.info("Illegal command");
		}
		return continu;
	}

	private void handleSingleCharInput(String line) {
		switch (line) {
		case "r":
			controller.reset();
			break;
		case "n":
			controller.create();
			break;
		case "s":
			controller.show();
			break;
		case "c":
			controller.copy();
			break;
		case "p":
			controller.paste();
			break;
		case "-":
			controller.resetSize(DOTSIZE);
			break;
		case "+":
			controller.resetSize(PLUSSIZE);
			break;
		case "*":
			controller.resetSize(HASHSIZE);
			break;
		}
	}

	private int[] readToArray(String line) {
		int[] arg = new int[line.length()];
		for (int i = 0; i < arg.length; i++) {
			m.find();
			arg[i] = Integer.parseInt(m.group());
		}
		return arg;
	}

	public void printTUI() {
		logger.info(NEWLINE + controller.getGridString());
		logger.info(NEWLINE + controller.getStatus());
		logger.info(NEWLINE
				+ "Possible commands: q-quit, n-new, s-show, r-reset, c-copy, p-paste, -,+,*-size, x-highlight, xy-show (x,y), xyz-set (x,y) to z");
	}
}
