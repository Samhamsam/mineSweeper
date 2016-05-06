package de.htwg.minesweeper.model;

import java.util.BitSet;

public interface IGrid {

    /**
     * @param row
     * @param column
     * @return the cell at coordinates (row, col)
     */
    ICell getICell(int row, int column);

    /**
     * Calculate show all the mines in the Minesweeper puzzle.
     * 
     * @return true if all mines were found, false if none were found or missing some.
     */
    boolean show();

    /**
     * Calculates the id of the block at coordinates (row, col).
     * 
     * @param row
     * @param column
     * @return an index value between 0 and the number of blocks.
     */
    int blockAt(int row, int column);

    /**
     * Return the cells to "Game Start" appearance, doesn't change the puzzle
     */
    void reset();

    /**
     * Create a new  puzzle.
     */
    void create();

    /**
     * @param string
     *            - the pattern.
     * @return a textual representation of the minesweeper puzzle following a
     *         pattern.
     */
    String toString(String string);

    String toJson();

    /**
     * Fill the cells of a grid with values parsed from the input string.
     * 
     * @param input
     * @return true if a value for every cell was found, false if not enough
     *         values were found.
     */
    boolean parseStringToGrid(String input);

    /**
     * A representation of the available values for a cell at the coordinates
     * (row, col) in a BitSet.
     * 
     * @param row
     * @param col
     * @return
     */
    BitSet candidates(int row, int col);

}
