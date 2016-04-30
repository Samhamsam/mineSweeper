package de.htwg.minesweeper;


import static org.junit.Assert.*;
import org.junit.Test;

import de.htwg.minesweeper.*;

public class minesweeperTest {
	Field testField = new Field();
	
	@Test
	public void setupFieldTest(){
		
		String test[][] = testField.setupField();
		assertEquals(testField.column * testField.row, test.length * test[0].length);
	}
}
