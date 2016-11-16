package de.htwg.se.minesweeper.aview.gui;

import java.awt.Dimension;
import java.awt.Frame;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.UIManager;

import de.htwg.se.minesweeper.controller.OldIController;


public class GUISettings {
	
	static JOptionPane optionPane;
	Frame frame;
	
	private int maximumCR = 40;
	private int maximumB = 100;
	private int minimum = 5;
	private int majorSpacing = 5;
	private int minorSpacing = 1;
	private int initialValueColumnAndRow;
	private int initialValueMines;
	
	
	private int newRowColumn;
	private int newMines;

  	private OldIController controller;
	static JSlider eins = new JSlider(JSlider.HORIZONTAL);
  	static JSlider zwei = new JSlider(JSlider.HORIZONTAL);

  public GUISettings(int initialValueColumnAndRow, int initialValueMines, OldIController controller, Frame frame){
	  this.controller = controller;
	  this.frame = frame;
	  this.initialValueColumnAndRow = initialValueColumnAndRow;
	  this.initialValueMines = initialValueMines;
  }
  
  public void run(){
	  eins.setMaximum(maximumCR);
	  eins.setMinimum(minimum);
	  eins.setValue(initialValueColumnAndRow);	
	  eins.setMajorTickSpacing(majorSpacing);
	  eins.setMinorTickSpacing(minorSpacing);
	  eins.setSnapToTicks(true);
	  eins.setPaintTicks(true);
	  eins.setPaintLabels(true);
	  
	  zwei.setMaximum(maximumB);
	  zwei.setMinimum(minimum);
	  zwei.setValue(initialValueMines);
	  zwei.setMajorTickSpacing(majorSpacing);
	  zwei.setMinorTickSpacing(minorSpacing);
	  zwei.setSnapToTicks(true);
	  zwei.setPaintTicks(true);
	  zwei.setPaintLabels(true);
	  
	  UIManager.put("OptionPane.minimumSize",new Dimension(500,100));
	  Object[] complexMsg = { "Set number row/column", eins, "Set number mines",zwei};

	  int result = JOptionPane.showConfirmDialog(frame, complexMsg, 
		       "Settings", JOptionPane.OK_CANCEL_OPTION);
	  
	  newRowColumn = eins.getValue();
	  newMines = zwei.getValue();
	  if(result == 0){
		  setController();
		  controller.notifyIfSettingsSet();
	  }
		  
  }
  
  
  private void setController(){
	  String answer = "c,"+newRowColumn+","+newMines;
	  List<String> list = Arrays.asList(answer.split(","));
	  controller.setRowAndColumnAndBombs(list,false);
	  return;
  }

}