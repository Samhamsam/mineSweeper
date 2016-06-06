package de.htwg.minesweeper.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.minesweeper.model.Field;



public class FieldTest {
	
	Field field;
	
	private int row = 10, column = 10, numberOfMines = 10;
	
	@Before
	public void setUp(){
		field = new Field(row, column, numberOfMines);
	}

	@Test
	public void testField() {
		int desiredlenghtofRow = 10;
		int desiredlenghtofColumn = 10;
		int desiredlenghtofMines = 10;
		Field fieldTest = new Field(desiredlenghtofRow,desiredlenghtofColumn,desiredlenghtofMines);
		assertEquals(fieldTest.getRow(),desiredlenghtofRow);
		assertEquals(fieldTest.getColumn(),desiredlenghtofColumn);
		assertEquals(fieldTest.getNumberOfMines(),desiredlenghtofMines);
	}

	@Test
	public void testSetupField() {
		field.setupField();
		assertEquals(field.getfilledField().length, row);
		assertEquals(field.getfilledField()[1].length, column);
	}

	@Test
	public void testPrintField() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetfilledField() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetUserField() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserField() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserFieldSimple() {
		fail("Not yet implemented");
	}

}
