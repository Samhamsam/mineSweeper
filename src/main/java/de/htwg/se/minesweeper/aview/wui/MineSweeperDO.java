package de.htwg.se.minesweeper.aview.wui;

/**
 * @author Niels Boecker, MaibornWolff
 */
public class MineSweeperDO {
    private final String[][] feldText;
    private final int numberOfMines;
    private final int statusCode;

    public MineSweeperDO(String[][] feldText, int numberOfMines, int statusCode) {

        this.feldText = feldText;
        this.numberOfMines = numberOfMines;
        this.statusCode = statusCode;
    }
}
