package de.htwg.se.minesweeper.aview.gui;

import javax.swing.*;
import de.htwg.se.minesweeper.controller.IController;
import observer.Event;
import observer.IObserver;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener,IObserver,MouseListener{
	private static final long serialVersionUID = 1L;
	private JButton[][] buttonForTheMineSweeperFields;
	private IController controller;
	private GUISettings guiSettings;
	
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu, submenu, menuQuestion;
	JMenuItem newGame;
	JMenuItem quit,settingsmenu,help;
	
	public GUI(IController controller){
		this.controller = controller;
		controller.addObserver(this);
		frame = new JFrame("Minesweeper");
		setFrame();

	}
	
	private void setFrame(){

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuQuestion = new JMenu("?");
		menuBar.add(menu);
		menuBar.add(menuQuestion);
		newGame = new JMenuItem("New Game");
		quit = new JMenuItem("Quit");
		help = new JMenuItem("Help");
		settingsmenu = new JMenuItem("Settings");
		menu.add(newGame);
		menu.add(settingsmenu);
		menu.add(quit);
		menuQuestion.add(help);
		
		newGame.addActionListener(this);
		quit.addActionListener(this);
		help.addActionListener(this);
		settingsmenu.addActionListener(this);
		
		frame.setJMenuBar(menuBar);
		
		frame.setLayout(new GridLayout (controller.getRow(),controller.getColumn()));
		buildGameField(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	
	private void buildGameField(JFrame frame){
		buttonForTheMineSweeperFields = new JButton[controller.getRow()][controller.getColumn()];
		
		String[][] fieldString= getFeldText();
		
		setStringInButton(frame,fieldString);
	}
	
	private void setStringInButton(JFrame frame, String[][] fieldString){
		for (int y=0; y < controller.getColumn(); y++){
			for (int x = 0; x < controller.getColumn(); x++){
				buttonForTheMineSweeperFields [x][y] = new JButton (fieldString[y][x]); 
				frame.add(buttonForTheMineSweeperFields[x][y]);
				buttonForTheMineSweeperFields[x][y].setBackground(Color.GRAY);
				buttonForTheMineSweeperFields [x][y].addMouseListener(this);
				buttonForTheMineSweeperFields[x][y].setPreferredSize(new Dimension(50, 50));
			}
		}
	}
	
	private void setStringInButton(String[][] fieldString){
		for (int y=0; y < controller.getColumn(); y++){
			for (int x = 0; x < controller.getRow(); x++){
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
		for (int y=0; y < controller.getColumn(); y++){
			for (int x = 0; x < controller.getRow(); x++){
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

		}
		else if(e.getSource()==quit){
			controller.exitGame();
		}
		
		else if(e.getSource()==help) {
			controller.hilfe();
		}
		else if(e.getSource()==settingsmenu) {
			setSettings();
		}
		

	}

	@Override
	public void update(Event e) {
		if(!(controller.getStatusCode() == 5)){
			if(!((controller.getStatusCode() == 6) || (controller.getStatusCode() == 7))){
				setStringInButton(getFeldText());
			}
				
			
			if(controller.getStatusCode() == 1){
				setEnableButtons(true);
			}
			
			if(controller.getStatusCode() == 2){
				messageDialog("You Lost!");
				setEnableButtons(false);
			}
	
			if(controller.getStatusCode() == 3)
				messageDialog("You Won! "+controller.getTimeWon()+" Points!");
			
			if(controller.getStatusCode() == 4)
				messageDialog(controller.getHelpText());
			
			if(controller.getStatusCode() == 6)
				setSettings();

			if(controller.getStatusCode() == 7)
				doSettings();
		}
	}
	
	public String[][] getFeldText(){
		return controller.getFeldText();
	}
	
	private void messageDialog(String text){
		JOptionPane.showMessageDialog(frame, text);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not needed
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1){
		
			if(e.getSource()==newGame){
				controller.newGame();
				setEnableButtons(true);
	
			}
			else if(e.getSource()==quit){
				controller.exitGame();
			}
			else {
				getButtonPositionAndStartController(e);
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			setFlag(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not needed
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not needed
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not needed
	}
	
	private void setFlag(MouseEvent e){
		for (int y=0; y < controller.getColumn(); y++){
			for (int x = 0; x < controller.getRow(); x++){
				Object buttonText = e.getSource();
				if(buttonText.equals(buttonForTheMineSweeperFields[y][x])){
					controller.startGame(y+","+x+",f");
				}

			}
		}
	}
	
	private void getButtonPositionAndStartController(MouseEvent e){
		for (int y=0; y < controller.getColumn(); y++){
			for (int x = 0; x < controller.getRow(); x++){
				Object buttonText = e.getSource();
				if(buttonText.equals(buttonForTheMineSweeperFields[y][x])){
					controller.startGame(y+","+x);
				}

			}
		}
	}
	
	private void setSettings(){
		guiSettings = new GUISettings(controller.getColumn(), controller.getNumberOfMines(),controller,frame);
		guiSettings.run();
	}
	private void doSettings(){
		frame.getContentPane().removeAll();
		setFrame();
		frame.validate();
		frame.repaint();
	}

}
