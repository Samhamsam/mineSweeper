package de.htwg.minesweeper.aview.gui;

import javax.swing.*;

import de.htwg.minesweeper.model.Field;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Window extends JFrame{

	//private final int ROWS = 10;
    //private final int COLUMNS = 10;
    //private JButton[][] Minefield;
    JButton[] jb = new JButton[100];  
    
    public void createWindow () {
    	setLocation (500,500);
    	JPanel jp = new JPanel(new GridLayout (10,10));
    	//Minefield = new JButton [ROWS][COLUMNS];
    	//Minefield = new JButton [(Field.filledField[0][10]), (Field.filledField[1][10])];
    	ArrayList list = new ArrayList();
    	
    	for (int x = 0; x < jb.length; x++){
    		jb[x] = new JButton("" + (x+1));
    		list.add(jb[x]);
    	}
    	
    	 getContentPane().add(jp);
    	    pack();
    	
    }
       

    
    public static void main(String[] args){
    	JFrame frame = new JFrame();
    	
    	JPanel panel = new JPanel();
    	frame.getContentPane().add(panel);
    	frame.setSize(new Dimension (500, 500));
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	frame.setTitle("Minesweeper");
    	
    }

}