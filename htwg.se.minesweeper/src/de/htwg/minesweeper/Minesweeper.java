package de.htwg.minesweeper;

// Imported from htwg.se.sudoku (markoboger)
// Any Edits by Lycoris1305

import java.util.Scanner;
import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.minesweeper.aview.gui.MinesweeperFrame;
import de.htwg.minesweeper.aview.tui.TextUI;
import de.htwg.minesweeper.controller.IMinesweeperController;

public final class Minesweeper {

	private static Scanner scanner;
	private static TextUI tui;
	private IMinesweeperController controller;
	private static Minesweeper instance = null;
	
	public static Minesweeper getInstance(){
		if (instance = null){
			instance = new Minesweeper();
		} return instance;
	}
	
	private Minesweeper(){
		//set up logging
		PropertyConfigurator.configure("log4j.properties");
		
		//set up google guice
		Injector injector = Guice.createInjector(new MinesweeperModule());
		
		//Build application, resolve dependencies with Guice
		controller = injector.getInstance(IMinesweeperController.class);
		@SuppressWarnings("unused")
			MinesweeperFrame gui = injector.getInstance(MinesweeperFrame.class);
			tui = injector.getInstance(TextUI.class);
			tui.printTUI();
		
			//create a game
			controller.create();
	}
	
	public IMinesweeperController getController(){
		return controller;
		
	}
	
	public TextUI getTUI(){
		return tui;
		
	}
	
	public static void main(String[] args){
		Minesweeper.getInstance();
		
		//continue to read tui input until user quits game
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu){
			continu = tui.processInputLine(scanner.next());
		}
	}
}
