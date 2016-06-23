package de.htwg.minesweeper.aview.gui;

import javax.swing.*;

import de.htwg.minesweeper.controller.Controller;
import util.observer.Event;
import util.observer.IObserver;

import java.awt.*;
import java.awt.event.*;


//import de.htwg.

public class GUI extends JFrame implements ActionListener,IObserver{
	private static final long serialVersionUID = 1L;
	private JButton[][] buttonForTheMineSweeperFields;
	private Controller controller;
	
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem1;
	JMenuItem menuItem2;
	
	public GUI(Controller controller){
		this.controller = controller;
		controller.addObserver(this);
		
		frame = new JFrame("Minesweeper");
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuBar.add(menu);
		menuItem1 = new JMenuItem("New Game");
		menuItem2 = new JMenuItem("Quit");
		menu.add(menuItem1);
		menu.add(menuItem2);
		
		
		frame.setJMenuBar(menuBar);
		
		frame.setLayout(new GridLayout (10,10,2,2));
		buildGameField(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(550, 500));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	
	private void buildGameField(JFrame frame){
		buttonForTheMineSweeperFields = new JButton[10][10];
		
		String FieldString[][] = getFeldText();
		
		setStringInButton(frame,FieldString);
	}
	
	private void setStringInButton(JFrame frame, String[][] FieldString){
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				buttonForTheMineSweeperFields [x][y] = new JButton (FieldString[y][x]); 
				frame.add(buttonForTheMineSweeperFields[x][y]);
				buttonForTheMineSweeperFields[x][y].addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                System.out.println(e.getSource());
		            }
		        });
			}
		}
	}
	
	private void setStringInButton(String[][] FieldString){
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				setJButtonText(FieldString[y][x], y, x);
			}
		}
	}
	
	public void setJButtonText(String text, int i, int j){
		buttonForTheMineSweeperFields[i][j].setText(text);
	}
	
	public String getJButtonText(int i, int j){
		return buttonForTheMineSweeperFields[i][j].getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//System.out.println(e.getSource());
		
	}

	@Override
	public void update(Event e) {
		setStringInButton(getFeldText());
	}
	
	public String[][] getFeldText(){
		return controller.getFeldText();
	}


}
