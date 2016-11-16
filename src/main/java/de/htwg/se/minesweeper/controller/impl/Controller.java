package de.htwg.se.minesweeper.controller.impl;

import de.htwg.se.minesweeper.controller.IController;
import de.htwg.se.minesweeper.model.Cell;
import de.htwg.se.minesweeper.model.Grid;
import observer.Observable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Controller extends Observable implements IController {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int DEFAULT_NUMBER_ROWS_AND_COLS = 15;
    private static final int DEFAULT_NUMBER_MINES = 30;

    private Grid grid;
    private StatusCode statusCode;
    private int currentRound;

    // for time measuring
    private long timeOfGameStart;
    private long timeOfGameEnd;

    public Controller() {
        startNewGame();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    @Override
    public void startNewGame() {
        startNewGame(DEFAULT_NUMBER_ROWS_AND_COLS, DEFAULT_NUMBER_MINES);
        // TODO status code instead of "context" and "runningstate"
    }

    @Override
    public void startNewGame(int numberOfRowsAndCols, int numberOfMines) {
        this.grid = new Grid(numberOfRowsAndCols, numberOfRowsAndCols, numberOfMines);
        this.currentRound = 1;
        this.statusCode = StatusCode.INFO_TEXT;
        this.timeOfGameStart = System.currentTimeMillis();
        notifyObservers();
        // TODO status code instead of context?!
        // fieldPosition = NEW_GAME_COMMAND; ???
    }

    @Override
    public void commitNewSettingsAndRestart(int numberOfRowsAndCols, int numberOfMines) {
        statusCode = StatusCode.CHANGE_SETTINGS;

    }

    @Override
    public void revealCell(int row, int col) {

        final Cell cell = this.grid.getCellAt(row, col);
        cell.setRevealed(true);

        if (cell.hasMine()) {
            this.statusCode = StatusCode.GAME_LOST;
            // TODO setstatuspressedbomb???
        }

        else if (allCellsAreRevealed()) {
            long elapsedTimeNanos = System.nanoTime() - timeOfGameStart;
            long elapsedTimeSeconds = TimeUnit.SECONDS.convert(elapsedTimeNanos, TimeUnit.NANOSECONDS);
            statusCode = StatusCode.GAME_WON;
        }

        else {
            // TODO setstatusrunning?!
        }

        currentRound++;
        notifyObservers();
    }

    @Override
    public void toggleFlag(int row, int col) {
        final Cell cell = this.grid.getCellAt(row, col);
        cell.setFlagged(!cell.isFlagged());
    }

    @Override
    public boolean allCellsAreRevealed() {
        return grid.getTotalNumberOfCells() == grid.getNumberOfRevealedCells();
    }

    @Override
    public boolean checkIfCellHasMine(int row, int col) {
        final Cell cell = this.grid.getCellAt(row, col);
        return cell.hasMine();
    }

    @Override
    public boolean checkIfCellIsRevealed(int row, int col) {
        final Cell cell = this.grid.getCellAt(row, col);
        return cell.isRevealed();
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public void setTimeOfGameStart(long timeOfGameStart) {
        this.timeOfGameStart = timeOfGameStart;
    }

    @Override
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        notifyObservers();
    }

    @Override
    public String getHelpText() {
        return "(TUI:n) GUI:	Menu	->	New Game: 	This command starts a new game. (reset)\n"
                + "(TUI:q) GUI:	Menu	->	Quit:		This command ends the Game and close it\n"
                + "(TUI:c) GUI: Menu	->	Settings:	This command sets the number for column/row and mines\n"
                + "(TUI:h) GUI:	?	->	Help:		This command shows the help text";
    }
}
