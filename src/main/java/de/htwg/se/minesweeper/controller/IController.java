package de.htwg.se.minesweeper.controller;

import observer.IObservable;

/**
 * @author Niels Boecker, MaibornWolff
 */
public interface IController extends IObservable {

    void startNewGame();

    void revealCell(int row, int col);

    void setFlag(int row, int col);

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
