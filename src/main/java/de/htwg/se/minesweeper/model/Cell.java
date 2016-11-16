package de.htwg.se.minesweeper.model;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class Cell {

    private boolean isMine;
    private boolean isFlagged;
    private boolean isRevealed;
    private Position position;
    private int surroundingMines;

    // default constructor
    public Cell(Position position) {
        this.isMine = false;
        this.isFlagged = false;
        this.isRevealed = false;
        this.position = position;
        this.surroundingMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSurroundingMines() {
        return surroundingMines;
    }

    public void setSurroundingMines(int surroundingMines) {
        this.surroundingMines = surroundingMines;
    }

    protected static class Position {

        // public so they can be accessed simply
        private int row;
        private int col;

        // default constructor
        public Position() {
            this(0, 0);
        }

        // copy constructor
        public Position(Position g) {
            this(g.row, g.col);
        }

        // int constructor
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public void setPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Position getNorth() {
            return new Position(row - 1, col);
        }

        public Position getEast() {
            return new Position(row, col + 1);
        }

        public Position getSouth() {
            return new Position(row + 1, col);
        }

        public Position getWest() {
            return new Position(row, col - 1);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position g = (Position) obj;
                return (row == g.row) && (col == g.col);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int sum = row + col;
            return sum * (sum + 1) / 2 + row;
        }

        @Override
        public String toString() {
            return getClass().getName() + "[row=" + row + ",col=" + col + "]";
        }
    }
}
