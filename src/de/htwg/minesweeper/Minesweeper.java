package de.htwg.minesweeper;

import java.util.Scanner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.minesweeper.aview.gui.GUI;
import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.controller.IController;
import de.htwg.minesweeper.controller.impl.Controller;

public final class Minesweeper {
	
	private static Scanner scanner;
	private TUI tui;
	protected IController controller;
	private GUI gui;
	private static Minesweeper instance = null;

	private Minesweeper(){
		Injector inject = Guice.createInjector();
		controller = inject.getInstance(Controller.class);
		tui = new TUI(controller);
		gui = new GUI(controller);
		controller.setStatusCode(0);
		tui.printTui();
	}
	
	public static Minesweeper getInstance(){
		if(instance == null){
			instance = new Minesweeper();
		}
		return instance;
	}
	
	public TUI getTUI(){
		return tui;
	}
	
	public GUI getGUI(){
		return gui;
	}
	
	public IController getController(){
		return controller;
	}
	
	public static void main(final String[] args)
	{

		Minesweeper game = Minesweeper.getInstance();
		
		boolean continu = true;
		scanner = new Scanner(System.in);
		
		while(continu){
			continu = game.getTUI().answerOptions(scanner.next());
		}
	}

}
