package de.htwg.minesweeper.controller;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;


import de.htwg.minesweeper.model.Model;

public class ControllerTest {

	Controller control,control2;
	Model model, modelWithoutBombs,modelWithLotsBombs;
	/*
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	*/
	@Before
	public void setUp() throws Exception {
		model = new Model(10, 10, 20);
		modelWithoutBombs = new Model(10, 10, 0);
		modelWithLotsBombs = new Model(10, 10, 10000);
		control = new Controller(model);
		control2 = new Controller();
	}
	
	@Test
	public void testhilfe(){
		control.hilfe();
		assertTrue(control.getHelpText().contains("GUI"));
	}

	@Test
	public void testNewGame() {
		control.newGame();
		assertTrue(control.getStatusCode() == 1);
	}

	@Test
	public void testStartgame() {
		control.newGame(modelWithoutBombs);
		control.startGame("0,0");
		assertEquals("0", modelWithoutBombs.getUserFieldSimple(0, 0));
		
		control.newGame(modelWithLotsBombs);
		control.startGame("0,0");
		assertEquals("b", modelWithLotsBombs.getUserFieldSimple(0, 0));

		control.newGame(model);
		control.startGame("0,0,f");
		assertEquals("f", model.getUserFieldSimple(0, 0));
		
		control2.setNumberOfMines(2);
		control2.newGame();
		assertEquals(2, control2.getNumberOfMines());

	}
	


	@Test
	public void testsetFlag() {
		String answer = "0,0,f";
		List<String> list = Arrays.asList(answer.split(","));
		control.setFlag(list);
		assertEquals("f",model.getUserFieldSimple(0, 0));
		control.setFlag(list);
		assertEquals("x",model.getUserFieldSimple(0, 0));
	}
	

	@Test
	public void testGetStatusText() {
		control.setStatusCode(0);
		assertEquals(0,control.getStatusCode());
	}

	@Test
	public void testGetFieldPosition() {
		assertTrue(control.getFieldPosition().isEmpty());
		control.startGame("2,2");
		assertFalse(control.getFieldPosition().isEmpty());
	}

	@Test
	public void testSetStatusCode() {
		control.setStatusCode(0);
		assertEquals(0,control.getStatusCode());
	}

}
