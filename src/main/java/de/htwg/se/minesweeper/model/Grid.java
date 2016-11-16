package de.htwg.se.minesweeper.model;

import java.util.List;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Grid {

    private List<Cell> cells;

    // default constructor
    public Grid(int numberOfRows, int numberOfColums) {

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColums; col++) {
                final Cell cell = new Cell(new Cell.Position(row, col));
                cells.add(cell);
            }
        }

    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getNumberOfCells() {
        return cells.size();
    }
}
