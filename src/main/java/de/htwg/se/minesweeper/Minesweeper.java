package de.htwg.se.minesweeper;

import de.htwg.se.minesweeper.aview.tui.TUI;
import de.htwg.se.minesweeper.controller.IController;
import de.htwg.se.minesweeper.controller.impl.Controller;

import java.util.Scanner;

//import de.htwg.se.minesweeper.aview.gui.GUI;

public final class Minesweeper {

    private static Scanner scanner;
    private TUI tui;
    protected IController controller;
    // TODO Mark private GUI gui;
    private static Minesweeper instance = null;

    private Minesweeper() {
        // Injector inject = Guice.createInjector();
        //controller = inject.getInstance(IController.class);
        // TODO Mark: juice
        controller = new Controller();
        System.out.println("creating tui");
        tui = new TUI(controller);
        System.out.println("creating tui done");

        // TODO Mark
        // GUI auf neuen Controller umstellen
        // gui = new GUI(controller);
        tui.printTUI();
    }

    public static Minesweeper getInstance() {
        if (instance == null) {
            instance = new Minesweeper();
        }
        return instance;
    }

    public TUI getTUI() {
        return tui;
    }

    public IController getController() {
        return controller;
    }

    public static void main(final String[] args) {

        Minesweeper game = Minesweeper.getInstance();

        boolean loop = true;
        scanner = new Scanner(System.in);

        while (loop) {
            loop = game.getTUI().processInput(scanner.next());
        }
    }

}
