package de.htwg.minesweeper;


import static org.junit.Assert.*;
import org.junit.Test;

import de.htwg.minesweeper.TUI;
import de.htwg.minesweeper.Field;

public class minesweeperTest {
	
	private TUI tui;
	private Field field;
	private String test[][];
	
	public void setUp() throws Exception{
		tui = new TUI();
		field = new Field();
		test = field.setupField();
	}
	
	
	@Test
	public void setupFieldTest(){
		assertEquals(field.column * field.row, test.length * test[0].length);
	}

}
