package de.htwg.se.minesweeper.aview.tui;

import de.htwg.se.minesweeper.controller.Controller;
import observer.Event;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// TODO Mark
@Ignore
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
