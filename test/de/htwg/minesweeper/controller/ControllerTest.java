package de.htwg.minesweeper.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.minesweeper.model.Model;

public class ControllerTest {
	
	Controller control;
	Model model;
	
	@Before
	public void setUp() throws Exception {
		control = new Controller();
		model = new Model(10, 10, 20);
	}

	@Test
	public void testController() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewGame() {
		control.newGame();
		assertTrue(control.isFirstStart());
	}
	
	@Test
	public void testStartgame() {
		
		control.setNumberOfMines(0);
		control.newGame();
		control.startgame("0,0");
		assertEquals(true, control.startgame("0,0"));
		control.setNumberOfMines(1000);
		control.newGame();
		control.startgame("0,0");
		assertEquals(false, control.startgame("0,0"));
		control.newGame();
		control.startgame("0,0,f");
		String field[][] = control.getFeldText();
		
		assertEquals("f", field[0][0]);
	}

	@Test
	public void testCheckIfGameIsWon() {
		fail("Not yet implemented");
	}

	@Test
	public void testStringToNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFlag() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsItaBomb() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatusText() {
		control.setStatusCode(0);
		assertEquals(0,control.getStatusCode());
	}

	@Test
	public void testGetFieldPosition() {
		assertTrue(control.getFieldPosition().isEmpty());
		control.startgame("2,2");
		assertFalse(control.getFieldPosition().isEmpty());
	}

	@Test
	public void testExitGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFeldText() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatusCode() {
		control.setStatusCode(0);
		assertEquals(0,control.getStatusCode());
	}

	@Test
	public void testSetFeldText() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimeWon() {
		fail("Not yet implemented");
	}

}
