package de.htwg.minesweeper.aview.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import de.htwg.

public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton[][] buttonForTheMineSweeperFields;
	
	public GUI(String buttonStartStatus){
		//Sets layout in a grid, with 10x10 buttons, with a separtion between buttons at 2 pixels
		JFrame frame = new JFrame("Minesweeper");
		frame.setLayout(new GridLayout (10,10,2,2));
		buildGameField(frame, buttonStartStatus);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(550, 500));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		
	}
	
	//sample main class, so this class can be run without running the entire game
	/*
	public static void main(String[] args){
		GUI start = new GUI("x");
		start.setJButtonText("h", 9, 9);
		System.out.println(start.getJButtonText(9, 9));
	}
	*/
	
	private void buildGameField(JFrame frame, String buttonStartStatus){
		buttonForTheMineSweeperFields = new JButton[10][10];
		
		//Should insert the userfield into each button
		for (int y=0; y < 10; y++){
			for (int x = 0; x < 10; x++){
				buttonForTheMineSweeperFields [x][y] = new JButton (buttonStartStatus);
				frame.add(buttonForTheMineSweeperFields[x][y]);
				buttonForTheMineSweeperFields [x][y].addActionListener(this);
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

}
