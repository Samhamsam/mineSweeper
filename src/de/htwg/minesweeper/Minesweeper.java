package de.htwg.minesweeper;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.controller.Controller;

public class Minesweeper {
	
	private static Scanner scanner;
	private static TUI tui;
	private static Controller controller;
	
	
	public static void main(final String[] args)
	{
		controller = new Controller();
		
		tui = new TUI(controller);
		
		//controller.create();
		
		boolean continu = true;
		scanner = new Scanner(System.in);
		while(continu){
			continu = tui.answerOptions(scanner.next());
		}
	}

}
