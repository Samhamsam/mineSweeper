package de.htwg.se.minesweeper.controller.impl;

import de.htwg.se.minesweeper.controller.IController;
import de.htwg.se.minesweeper.model.Grid;
import observer.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Controller extends Observable implements IController {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int DEFAULT_NUMBER_ROWS = 15;
    private static final int DEFAULT_NUMBER_COLUMNS = 15;
    private static final int DEFAULT_NUMBER_MINES = 30;

    private Grid grid;
    private StatusCode statusCode;

    // for time measuring
    private long timeOfGameStart;
    private long timeOfGameEnd;

    public Controller() {
        startNewGame();
    }


    @Override
    public void startNewGame() {
        this.grid = new Grid(DEFAULT_NUMBER_ROWS, DEFAULT_NUMBER_COLUMNS, DEFAULT_NUMBER_MINES);
        // TODO status code instead of "context" and "runningstate"
    }

    @Override
    public void revealCell(int row, int col) {

    }

    @Override
    public void setFlag(int row, int col) {

    }
}
