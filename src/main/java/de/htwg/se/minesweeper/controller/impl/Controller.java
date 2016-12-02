package de.htwg.se.minesweeper.controller.impl;

import de.htwg.se.minesweeper.controller.IController;
import de.htwg.se.minesweeper.model.Cell;
import de.htwg.se.minesweeper.model.Grid;
import observer.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Controller extends Observable implements IController {

    private static final int DEFAULT_NUMBER_ROWS_AND_COLS = 15;
    private static final int DEFAULT_NUMBER_MINES = 30;

    private Grid grid;
    private State state;

    // for time measuring
    private long timeOfGameStartMills;
    private long elapsedTimeSeconds;

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
    }

    @Override
    public void startNewGame(int numberOfRowsAndCols, int numberOfMines) {
        this.grid = new Grid(numberOfRowsAndCols, numberOfRowsAndCols, numberOfMines);
        this.state = State.NEW_GAME;
        this.timeOfGameStartMills = System.currentTimeMillis();
        notifyObservers();
    }

    @Override
    public void commitNewSettingsAndRestart(int numberOfRowsAndCols, int numberOfMines) {
        startNewGame(numberOfRowsAndCols, numberOfMines);
        state = State.CHANGE_SETTINGS_SUCCESS;
        notifyObservers();
    }

    @Override
    public void revealCell(int row, int col) {
        revealCell(this.grid.getCellAt(row, col));
    }

    @Override
    public void revealCell(Cell cell) {

        if (cell == null || cell.isRevealed()
                || getState() == State.GAME_LOST || getState() == State.GAME_WON) return;

        cell.setRevealed(true);

        if (cell.hasMine()) {
            setElapsedTime();
            this.state = State.GAME_LOST;
        }

        if (cell.getSurroundingMines() == 0) {
            final List<Cell> neighbors = this.grid.getAllNeighbors(cell);
            for (Cell neighbor : neighbors) {
                revealCell(neighbor);
            }
        }

        if (allCellsAreRevealed()) {
            setElapsedTime();
            state = State.GAME_WON;
        }

        setState(State.REVEAL_CELL);
    }

    private void setElapsedTime() {
        long elapsedTimeNanos = System.nanoTime() - timeOfGameStartMills;
        elapsedTimeSeconds = TimeUnit.SECONDS.convert(elapsedTimeNanos, TimeUnit.NANOSECONDS);
    }

    @Override
    public void toggleFlag(int row, int col) {
        final Cell cell = this.grid.getCellAt(row, col);

        if (cell == null || getState() == State.GAME_LOST || getState() == State.GAME_WON) return;

        cell.setFlagged(!cell.isFlagged());
        setState(State.TOGGLE_FLAG);
    }

    @Override
    public boolean allCellsAreRevealed() {
        return grid.getTotalNumberOfCells() == grid.getNumberOfRevealedCells();
    }

    @Override
    public void setState(State state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getHelpText() {
        return "(TUI:n) GUI: Menu	->	New Game: 	This command starts a new game. (reset)\n"
                + "(TUI:q) GUI: Menu	->	Quit:		This command ends the Game and close it\n"
                + "(TUI:c) GUI: Menu	->	Settings:	This command sets the number for column/row and mines\n"
                + "(TUI:h) GUI: ?	    ->	Help:		This command shows the help text";
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public long getElapsedTimeSeconds() {
        return elapsedTimeSeconds;
    }
}
