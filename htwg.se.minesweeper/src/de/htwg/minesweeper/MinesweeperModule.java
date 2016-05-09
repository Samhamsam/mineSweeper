package de.htwg.minesweeper;

//Imported from htwg.se.sudoku (markoboger)
// Any edits by Lycoris1305

import com.google.inject.AbstractModule;

import de.htwg.minesweeper.controller.IMinesweeperController;
import de.htwg.minesweeper.model.IGridCreation;

public class MinesweeperModule {

    protected void configure() {

        bind(IGridCreation.class)
                .to(de.htwg.minesweeper.model.impl.GridCreation.class);
        bind(IMinesweeperController.class).to(
                de.htwg.minesweeper.controller.logwrapper.MinesweeperController.class);

    }

}
