package de.htwg.minesweeper.controller;


import de.htwg.minesweeper.model.IGrid;
import de.htwg.minesweeper.util.observer.IObservable;

public interface IMinesweeperController extends IObservable{
	/**
     * Create a string representation of the minesweeper puzzle and puts it into the
     * system clip board
     */
    void copy();

    /**
     * Calculate a new minesweeper puzzle.
     */
    void create();

    /**
     * @return the textual representation of the puzzle.
     */
    String getGridString();

    /**
     * @return the textual representation of the status line.
     */
    String getStatus();

    /**
     * Get the value of the cell at coordinates (row, col).
     * 
     * @param row
     * @param column
     * @return
     */
    int getValue(int row, int column);

    /**
     * All cells that have the value index as a possible value (candidate)
     * should be highlighted in a graphical user interface.
     * 
     * @param index
     */
    //void highlight(int index);

    /**
     * @param row
     * @param column
     * @return true if the cell at (row, col) should be highlighted in a gui,
     *         false if not.
     */
    boolean isHighlighted(int row, int column);

    /**
     * @param row
     * @param column
     * @return true if value of the cell at (row, col) is different from zero.
     */
    //boolean isSet(int row, int column);

    /**
     * Take the content of the system clip board and try to parse the
     * puzzle out of it.
     */
    void paste();

    /**
     * Sets the Minesweeper game back to initial game
     */
    void reset();

    /**
     * Shows the solution for the minesweeper puzzle.
     */
    void show();

    /**
     * @param row
     * @param column
     * @return the id of the block at coordinates (row, column)
     */
    int blockAt(int row, int column);
    
    /**
     * Create a new puzzle with a size newSize
     * 
     * @param newSize
     */
    void resetSize(int newSize);

    IGrid getGrid();

}