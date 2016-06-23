package de.htwg.minesweeper.model;

import static org.junit.Assert.*;
import java.lang.String;

import org.junit.Before;
import org.junit.Test;

import de.htwg.minesweeper.model.Model;



public class FieldTest {
	
	Model field;
	
	private int row = 10, column = 10, numberOfMines = 10;
	
	@Before
	public void setUp(){
		field = new Model(row, column, numberOfMines);
		field.setupField();
	}

	@Test
	public void testField() {
		int desiredlenghtofRow = 10;
		int desiredlenghtofColumn = 10;
		int desiredlenghtofMines = 10;
		Model fieldTest = new Model(desiredlenghtofRow,desiredlenghtofColumn,desiredlenghtofMines);
		assertEquals(fieldTest.getRow(),desiredlenghtofRow);
		assertEquals(fieldTest.getColumn(),desiredlenghtofColumn);
		assertEquals(fieldTest.getNumberOfMines(),desiredlenghtofMines);
	}

	@Test
	public void testSetupField() {
		assertEquals(field.getfilledField().length, row);
		assertEquals(field.getfilledField()[1].length, column);
	}

	@Test //Testing getFilledField
	public void testPrintField() {
		assertTrue((field.printField(field.getfilledField()).toString().contains("b")));
		assertTrue((field.printField(field.getfilledField()).toString().contains("g")));
	}

	@Test
	public void testSetUserField() {
		field.setUserField(2, 3);
		String i = field.getUserFieldSimple(2, 3);
		Boolean iBoolean = (i.equals("0") || i.equals("1") || i.equals("2") || i.equals("3") || i.equals("4") || i.equals("5") || i.equals("6") || i.equals("7") || i.equals("8") || i.equals("b"));
		assertTrue(iBoolean);
	}
}
