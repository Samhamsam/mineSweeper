package de.htwg.se.minesweeper.controller;

import de.htwg.se.minesweeper.controller.impl.OldController;
import de.htwg.se.minesweeper.model.Model;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

// TODO Mark
@Ignore
public class OldControllerTest {

	OldController control,control2;
	Model model, modelWithoutBombs,modelWithLotsBombs;
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Before
	public void setUp() throws Exception {
		model = new Model(10, 10, 20);
		modelWithoutBombs = new Model(10, 10, 0);
		modelWithLotsBombs = new Model(10, 10, 10000);
		control = new OldController(model);
		control2 = new OldController();
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
		control.startGame("0,0");


		control.newGame(model);
		control.startGame("0,0,f");
		assertEquals("f", model.getUserFieldSimple(0, 0));
		
		control2.setNumberOfMines(2);
		control2.newGame();
		assertEquals(2, control2.getNumberOfMines());
		
		control.newGame();
		control.startGame("0,0,0,0");
		assertEquals(1, control.getStatusCode());
		control.startGame("a,a");
		assertEquals(5, control.getStatusCode());
		control.startGame("a,a,a");
		assertEquals(5, control.getStatusCode());
		control.startGame("");
		
	}
	


	@Test
	public void testsetFlag() {
		String answer = "0,0,f";
		List<String> list = Arrays.asList(answer.split(","));
		control.setFlag(list);
		assertEquals("f",model.getUserFieldSimple(0, 0));
		control.setFlag(list);
		assertEquals("x",model.getUserFieldSimple(0, 0));
		String answer2 = "0,0,c";
		List<String> list2 = Arrays.asList(answer.split(","));
		control.setFlag(list2);
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
	
	@Test
	public void testexitGame() {
		exit.expectSystemExitWithStatus(0);
		control.exitGame();
	}
	
	@Test
	public void testgetFeldText() {
		control.newGame(modelWithoutBombs);
		assertEquals("x", control.getFeldText()[0][0]);
	}
	
	@Test
	public void testgetTimeWon() {
		control.newGame(modelWithoutBombs);
		control.startGame("0,0");
		assertEquals("0", control.getTimeWon());
	}
	
	@Test
	public void testSetAndGetRow() {
		control2.setRow(10);
		assertEquals(10, control2.getRow());
	}
	
	@Test
	public void testSetAndGetColumn() {
		control2.setColumn(10);
		assertEquals(10, control2.getColumn());
	}
	
	@Test
	public void testSetRowAndColumnAndBombs(){
		String answer = "c,10,10";
		List<String> list = Arrays.asList(answer.split(","));
		control.setRowAndColumnAndBombs(list, false);
		assertEquals(10, control.getColumn());
		control.setRowAndColumnAndBombs(list, true);
		assertEquals(6, control.getStatusCode());
		String answer1 = "c,c,c";
		List<String> list1 = Arrays.asList(answer1.split(","));
		control.setRowAndColumnAndBombs(list1, false);
		
	}
	
	@Test
	public void testnotifyIfSettingsSet(){
		control.notifyIfSettingsSet();
		assertEquals(7, control.getStatusCode());
	}
	

}
