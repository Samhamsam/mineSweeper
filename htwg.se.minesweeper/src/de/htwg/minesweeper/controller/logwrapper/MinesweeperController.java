package de.htwg.minesweeper.controller.logwrapper;

import org.apache.log4j.Logger;

//import com.google.inject.Inject;
//import com.google.inject.Singleton;

import de.htwg.minesweeper.controller.IMinesweeperController;
import de.htwg.minesweeper.model.IGrid;
import de.htwg.minesweeper.model.IGridCreation;
import de.htwg.minesweeper.util.observer.Event;
import de.htwg.minesweeper.util.observer.IObservable;
import de.htwg.minesweeper.util.observer.IObserver;

//@Singleton
public class MinesweeperController implements IObservable, IMinesweeperController {

    private Logger logger = Logger
            .getLogger("de.htwg.minesweeper.controller.wrappedimpl");
    private IMinesweeperController realController;
    private long startTime;

    //@Inject
    public MinesweeperController(IGridCreation gridCreation) {
        realController = new de.htwg.minesweeper.controller.impl.MinesweeperController(
                gridCreation);
    }

    private void pre() {
        logger.debug("Controller method " + getMethodName(1) + " was called.");
        startTime = System.nanoTime();
    }

    private void post() {
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        logger.debug("Controller method " + getMethodName(1)
                + " was finished in " + duration + " nanoSeconds.");
    }

    private static String getMethodName(final int depth) {
        final StackTraceElement[] stack = Thread.currentThread()
                .getStackTrace();
        return stack[2 + depth].getMethodName();
    }

    public void show() {
        pre();
        realController.show();
        post();
    }

    public void reset() {
        pre();
        realController.reset();
        post();
    }

    public void create() {
        pre();
        realController.create();
        post();
    }

    public String getStatus() {
        return realController.getStatus();
    }

    public String getGridString() {
        pre();
        String result = realController.getGridString();
        post();
        return result;
    }
  
    public void copy() {
        pre();
        realController.copy();
        post();
    }

    public void paste() {
        pre();
        realController.paste();
        post();
    }

    public int getValue(int row, int column) {
        return realController.getValue(row, column);
    }

    public int blockAt(int row, int column) {
        return realController.blockAt(row, column);
    }

    @Override
    public void resetSize(int newSize) {
        pre();
        realController.resetSize(newSize);
        post();
    }

    @Override
    public void removeObserver(IObserver s) {
        pre();
        realController.removeObserver(s);
        post();
    }

    @Override
    public void removeAllObservers() {
        pre();
        realController.removeAllObservers();
        post();
    }

    @Override
    public void notifyObservers() {
        pre();
        realController.notifyObservers();
        post();
    }

    @Override
    public void notifyObservers(Event e) {
        pre();
        realController.notifyObservers(e);
        post();
    }

	@Override
	public boolean isHighlighted(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IGrid getGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addObserver(IObserver s) {
		// TODO Auto-generated method stub
		
	}

}
