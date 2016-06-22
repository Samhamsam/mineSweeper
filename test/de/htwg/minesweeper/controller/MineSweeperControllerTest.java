package de.htwg.minesweeper.controller;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class MineSweeperControllerTest {

	Controller control;
	
	@Before
	public void setUp() throws Exception {
		control = new Controller();
		control.run(new ByteArrayInputStream("1".getBytes()));
	}

	@Test
	public void testMineSweeperController() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		int a = control.field.getfilledField().length;
		assertEquals(10, a);
	}

	@Test
	public void testAnswerOptions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartgame() {
		fail("Not yet implemented");
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
		assertTrue(control.IsItaBomb(1,1));
	}

	@Test
	public void testExitGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRow() {
		control.setRow(10);
		assertEquals(10, control.getRow());
	}

	@Test
	public void testSetRow() {
		control.setRow(10);
		assertEquals(10, control.getRow());
	}

}
