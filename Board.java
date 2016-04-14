import java.awt.Graphics; //abstract base class for graphics
import java.util.Random; //generates random numbers 
import javax.swing.JLabel; // text labels
import javax.swing.JPanel; //groups components

public class Board extends Jpanel {
	private final int NumberImages = 10;
	private final int CellSize = 15;
	private final int CellCover = 10;
	private final int CellMarks = 10;
	private final int EmptyCells = 0;
	private final int MineCells = 9;
	private final int CoveredCellwMine = MineCells + CellCover;
	private final int MarkedCell = CellCover + CellMarks;
	private final int Mine = 9;
	private final int Cover = 10;
	private final int Mark = 11;
	private final int WrongMark = 12;
	
	private final int NumberofMines = 20;
	private final int NumberofRows = 10;
	private final int NumberofColumns = 10;
	
	
	
	
	public Board(JLabel statusbar){
		
	}
}
