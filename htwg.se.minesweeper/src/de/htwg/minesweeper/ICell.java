package de.htwg.minesweeper.model;

public interface ICell {
    boolean isUnSet();

    /**
     * Set the value of a cell.
     * 
     * @param value
     */
    void setValue(int value);

    /**
     * @return a rich textual representation. This is intended for status line
     *         of debug, not for the grid.
     */
    String mkString();

    /**
     * @return the value of the cell.
     */
    int getValue();

    /**
     * Set the value showCandidates to b.
     * 
     * @param b
     */
    //void setShowCandidates(boolean b);

    /**
     * @return the value of showCandidates.
     */
   //boolean isShowCandidates();

    /**
     * invert the value of showCandidates.
     */
    //void toggleShowCandidates();

}
