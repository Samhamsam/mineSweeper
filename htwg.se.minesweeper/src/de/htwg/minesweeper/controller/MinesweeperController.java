package de.htwg.minesweeper.controller.impl;

// Imported from htwg.se.sudoku (markoboger)
//Any edits made by Lycoris1305

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
//import java.util.BitSet;

//import com.google.inject.Inject;
//import com.google.inject.Singleton;

import de.htwg.minesweeper.controller.IMinesweeperController;
import de.htwg.minesweeper.controller.SizeChangeEvent;
import de.htwg.minesweeper.model.ICell;
import de.htwg.minesweeper.model.IGrid;
import de.htwg.minesweeper.model.IGridCreation;
import de.htwg.minesweeper.util.observer.Event;
import de.htwg.minesweeper.util.observer.Observable;

public class MinesweeperController extends Observable implements IMinesweeperController {
	
	private String statusLine = "Welcome to HTWG Minesweeper!";
    private IGrid grid;
    private IGridCreation gridCreation;
    private int highlighted = 0;
    //private static final int NORMALGRID = 3;

    //@Inject
    public MinesweeperController(IGridCreation gridCreation) {
        this.gridCreation = gridCreation;
        //this.grid = gridCreation.create(NORMALGRID);
    }

    public void setValue(int row, int column, int value) {
        ICell cell = grid.getICell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(value);
            statusLine = "The cell " + cell.mkString()
                    + " was successfully set";
        } else {
            statusLine = "The cell " + cell.mkString() + " is already set";
        }
        notifyObservers();
    }

    public void show() {
        boolean result;
        result = grid.show();
        if (result) {
            statusLine = "The Minesweeper solution is as shown.";
        } else {
            statusLine = "Error ";
        }
        notifyObservers();
    }

    public void reset() {
        grid.reset();
        statusLine = "Minesweeper was reset";
        notifyObservers();
    }

    public void create() {
        grid.create();
        highlighted = 0;
        statusLine = "New Minesweeper Puzzle created";
        notifyObservers();
    }

    public String getStatus() {
        return statusLine;
    }

    public String getGridString() {
        return grid.toString();
    }

    public void copy() {
        StringSelection gridString = new StringSelection(grid.toString("0"));
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(gridString, null);
        statusLine = "Copied Minesweeper";
        notifyObservers();
    }

    public void paste() {
        Transferable transferable = Toolkit.getDefaultToolkit()
                .getSystemClipboard().getContents(null);
        if (transferable != null
                && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            String input;
            try {
                input = (String) transferable
                        .getTransferData(DataFlavor.stringFlavor);
                grid.parseStringToGrid(input);
            } catch (UnsupportedFlavorException e1) {

                statusLine = "Could not read from Clipboard";
            } catch (IOException e1) {

                statusLine = "Could not read from Clipboard";
            }
        }
        statusLine = "Pasted Minesweeper";
        notifyObservers();
    }

    public int getValue(int row, int column) {
        return grid.getICell(row, column).getValue();
    }

    public void highlight(int value) {
        highlighted = value;
        notifyObservers();
    }

    public int blockAt(int row, int column) {
        return grid.blockAt(row, column);
    }
   //     notifyObservers();

    public boolean isHighlighted(int row, int column) {
        return grid.candidates(row, column).get(highlighted);
    }

    public boolean isCandidate(int row, int column, int candidate) {
        return grid.candidates(row, column).get(candidate);
    }

    //@Override
    //public void parseStringToGrid(String gridString) {
      //  grid.parseStringToGrid(gridString);
        //notifyObservers();

    //}

    @Override
    public void resetSize(int newSize) {
        this.grid = gridCreation.create(newSize);
        reset();
        Event event = new SizeChangeEvent();
        notifyObservers(event);
    }

    public IGrid getGrid() {
        return grid;
    }

}