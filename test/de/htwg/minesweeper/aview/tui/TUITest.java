package de.htwg.minesweeper.aview.tui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import de.htwg.minesweeper.controller.impl.Controller;
import util.observer.Event;

public class TUITest {
	
	Controller controller = new Controller();
	TUI tui;
	Event e;
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Before
	public void setUp() throws Exception {
		
		tui = new TUI(controller);
	}

	@Test
	public void testAnswerOptions() {
		tui.answerOptions("n");
		assertEquals("n", controller.getFieldPosition());
		tui.answerOptions("0,0");
		assertEquals("0,0", controller.getFieldPosition());
		tui.answerOptions("h");
		assertTrue(controller.getHelpText().contains("GUI"));
		
		exit.expectSystemExit();
		assertTrue(tui.answerOptions("q"));
	}

	@Test
	public void testUpdate() {
		tui.update(e);
		assertEquals(0, controller.getStatusCode());
	}
}
