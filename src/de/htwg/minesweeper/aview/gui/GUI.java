package de.htwg.minesweeper.aview.gui;

import javax.swing.*;

import de.htwg.minesweeper.aview.tui.TUI;
import de.htwg.minesweeper.model.Field;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//import de.htwg.

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private MineButton[][] buttonField;
	private JButton[][] jb;
	
	public GUI(){
		//Sets layout in a grid, with 10x10 buttons, with a separtion between buttons at 2 pixels
		setLayout(new GridLayout (10,10,2,2));
		buildGameField();
		
	}
	
	private void buildGameField(){
		jb = new JButton[10][10];
		
		//Should insert the userfield into each button
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				jb[x][y] = new MineButton(x,y);
				
				//makes a button and prints on the button b or g, or the mines and numbers for the user
				jb [x][y] = new JButton ("" + Field.getUserField());
				add(jb[x][y]);
			}
		}
	}
	
	public class MineButton extends JButton{
		private static final long serialVersionUID = 1L;
		
		private final Dimension buttonSize = new Dimension(40,40);
		private final Font font = new Font ("Dialog", Font.BOLD, 10);
		private int positionX, positionY;
		
		public MineButton(int positionX, int positionY){
			this.positionX = positionX;
			this.positionY = positionY;
			setPreferredSize(buttonSize);
			setFont(font);
			addListeners();
		}
		
		
		//for future use of user, so they can click on the button and it will reveal what is underneath
		private void addListeners(){
			this.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e){
					Object o = e.getButton();
					if (o.equals(MouseEvent.BUTTON1)) {
						leftClick();
					} else if (o.equals(MouseEvent.BUTTON3)) {
						rightClick();
				}
			}
		});
	}
	
		private void leftClick() {
			//if (!mineControl.getAt(positionX, positionY).isFlagged()) {
				//mineControl.click(positionX, positionY);
			}
		}

		private void rightClick() {
			//mineControl.toggleFlagged(positionX, positionY);
		}
}
	
/*

//sample main class, so this class can be run without running the entire game
public static void main(String[] args){
	JFrame frame = new JFrame();
	frame.setResizable(false);
	frame.setSize(new Dimension (500, 500));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("Minesweeper");
	new GUI().setVisible(true);
	}
}

*/
