package de.htwg.minesweeper.aview.gui;

// Imported from htwg.se.sudoku (markoboger)
// Any edits by Lycoris1305

import java.awt.GridLayout;

import javax.swing.JPanel;

public class BlockPanel extends JPanel {

    private static final long serialVersionUID = 9094365991959087973L;

    public BlockPanel(int blockSize) {
        setLayout(new GridLayout(blockSize, blockSize, 1, 1));
    }
}
