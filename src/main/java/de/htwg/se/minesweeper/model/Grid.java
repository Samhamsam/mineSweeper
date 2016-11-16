package de.htwg.se.minesweeper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Grid {

    private List<Cell> cells;

    // default constructor for empty grid
    public Grid(int numberOfRows, int numberOfColums) {

        cells = new ArrayList<>(numberOfColums * numberOfRows);

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColums; col++) {
                final Cell cell = new Cell(new Cell.Position(row, col));
                cells.add(cell);
            }
        }
    }

    // constructor for grid with mines
    public Grid(int numberOfRows, int numberOfColums, int numberOfMines) {
        this(numberOfRows, numberOfColums);

        // place mines on grid
        final Random random = new Random();
        int remainingMines = numberOfMines;
        while (remainingMines > 0) {
            final int mineRow = random.nextInt(numberOfRows);
            final int mineCol = random.nextInt(numberOfColums);
            final Cell cellAtPosition = getCellAt(mineRow, mineCol);
            if (cellAtPosition.isMine()) {
                continue;
            }
            cellAtPosition.setMine(true);
            remainingMines--;
        }

        // set number of surrounding mines accordingly
        for (Cell cell : cells) {
            int surroundingMines = 0;
            final Cell.Position ownPosition = cell.getPosition();
            final List<Cell.Position> surroundingPositions = Arrays.asList(
                    ownPosition.getNorth(), ownPosition.getEast(), ownPosition.getSouth(), ownPosition.getWest());
            for (Cell.Position position : surroundingPositions) {
                if (position != null && getCellAt(position).isMine()) {
                    surroundingMines++;
                }
            }
            cell.setSurroundingMines(surroundingMines);
        }
    }

    public Cell getCellAt(Cell.Position position) {
        return getCellAt(position.getRow(), position.getCol());
    }

    public Cell getCellAt(int row, int column) {
        for (Cell cell : cells) {
            final Cell.Position position = cell.getPosition();
            if (position.getRow() == row && position.getCol() == column) {
                return cell;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void setCellAt(int row, int column, Cell cell) {
        final Cell oldCell = getCellAt(row, column);
        cells.remove(oldCell);
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getNumberOfCells() {
        return cells.size();
    }
}
