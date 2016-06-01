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
    
    
    //creates 100 buttons
    JButton[] jb = new JButton[100];  
    
    public void createWindow () {
    	setLocation (500,500); //location on screen
    	JPanel jp = new JPanel(new GridLayout (10,10)); //grid that is 10x10
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
    	frame.setVisible(true);//REQUIRED	
    	frame.setResizable(false); //User cannot change the size of the window
    	frame.setTitle("Minesweeper");
    	
    }

}
