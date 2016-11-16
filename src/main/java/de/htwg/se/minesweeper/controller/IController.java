package de.htwg.se.minesweeper.controller;

import observer.IObservable;

/**
 * @author Niels Boecker, MaibornWolff
 */
public interface IController extends IObservable {

    void quit();

    void startNewGame();

    void startNewGame(int numberOfRowsAndCols, int numberOfMines);

    void commitNewSettingsAndRestart(int numberOfRowsAndCols, int numberOfMines);

    void revealCell(int row, int col);

    void toggleFlag(int row, int col);

    boolean allCellsAreRevealed();

    boolean checkIfCellHasMine(int row, int col);

    boolean checkIfCellIsRevealed(int row, int col);

    int getCurrentRound();

    void setTimeOfGameStart(long timeOfGameStart);

    void setStatusCode(StatusCode statusCode);

    String getHelpText();

    enum StatusCode {
        INFO_TEXT,
        HELP_TEXT,
        GAME_WON,
        GAME_LOST,
        ERROR,
        CHANGE_VARIABLES, // ???
        CHANGE_SETTINGS
    }

}
