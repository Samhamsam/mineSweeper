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
	JMenuItem newGame;
	JMenuItem quit;
	
	
	public GUI(Controller controller){
		this.controller = controller;
		controller.addObserver(this);
		
		frame = new JFrame("Minesweeper");
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuBar.add(menu);
		newGame = new JMenuItem("New Game");
		quit = new JMenuItem("Quit");
		menu.add(newGame);
		menu.add(quit);
		newGame.addActionListener(this);
		quit.addActionListener(this);
		
		
		
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
		
		String[][] fieldString= getFeldText();
		
		setStringInButton(frame,fieldString);
	}
	
	private void setStringInButton(JFrame frame, String[][] fieldString){
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				buttonForTheMineSweeperFields [x][y] = new JButton (fieldString[y][x]); 
				frame.add(buttonForTheMineSweeperFields[x][y]);
				buttonForTheMineSweeperFields[x][y].setBackground(Color.GRAY);
				buttonForTheMineSweeperFields [x][y].addActionListener(this);
			}
		}
	}
	
	private void setStringInButton(String[][] fieldString){
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				setJButtonText(fieldString[y][x], y, x);
				if("0".equals(getJButtonText(y,x))){
					setJButtonColor(y,x,Color.GREEN);
				}
				else if("x".equals(getJButtonText(y,x))){
					setJButtonColor(y,x,Color.GRAY);
				}
				else if("b".equals(getJButtonText(y,x)) || "f".equals(getJButtonText(y,x))){
					setJButtonColor(y,x,Color.RED);
				}
				else{
					setJButtonColor(y,x,Color.WHITE);
				}
			}
		}
	}
	
	private void setEnableButtons(boolean status){
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				buttonForTheMineSweeperFields [x][y].setEnabled(status);
			}
		}
	}
	
	public void setJButtonColor(int i, int j, Color color){
		buttonForTheMineSweeperFields[i][j].setBackground(color);
	}

	
	public void setJButtonText(String text, int i, int j){
		buttonForTheMineSweeperFields[i][j].setText(text);
	}
	
	public String getJButtonText(int i, int j){
		return buttonForTheMineSweeperFields[i][j].getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==newGame){
			controller.newGame();
			setEnableButtons(true);

		}
		else if(e.getSource()==quit){
			controller.exitGame();
		}
		else {
			for (int y=0; y < 10; y++){
				for (int x = 0; x < 10; x++){
					Object buttonText = e.getSource();
					if(buttonText.equals(buttonForTheMineSweeperFields[y][x])){
						controller.startgame(y+","+x);
					}
	
				}
			}

		}
	}

	@Override
	public void update(Event e) {
		setStringInButton(getFeldText());
		
		if(controller.getStatusCode() == 2){
			messageDialog("You Lost!");
			setEnableButtons(false);
		}

		if(controller.getStatusCode() == 3)
			messageDialog("You Won! "+controller.getTimeWon()+" Points!");
	}
	
	public String[][] getFeldText(){
		return controller.getFeldText();
	}
	
	private void messageDialog(String text){
		JOptionPane.showMessageDialog(frame, text);
	}


}
