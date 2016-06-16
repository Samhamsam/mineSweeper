package de.htwg.minesweeper.model;
import java.util.Observable;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Field extends Observable {
	
	private static final Logger log = LogManager.getLogger();
	
	private int row;;
	private int column;
	private int numberOfMines;
	private String bomb = "b";
	private String freeField = "g";
	private String userHiddenField = "x";
	private String userBombField = "0";
	
	private int sizeOfxAndfWithBomb = 0;
	private int sizeOfxAndfWithoutBomb = 0;
	
	

	
	//Main Field
	private String filledField[][];
	private String UserField[][];
	
	public Field(int i, int j, int k){
		this.row=i;
		this.column = j;
		this.numberOfMines = k;
		this.filledField = new String[column][row];
		this.UserField = new String[column][row];
	}

	
	/**
	 * Setup the Field and fill with Mines and Blanks
	 * @return the filled Field
	 */
	public void setupField(){
		filledField = insertb(insertg(filledField));
		UserField = UserField(UserField);
	}
	

	/**
	 * Fill with Blanks
	 * @param fillWithBlanks
	 * @return double array with value "g"
	 */
	private String[][] insertg(String fillWithBlanks[][]){
		
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	fillWithBlanks[i][j] = freeField;
		    }
		}
		return fillWithBlanks;
	}
	/**
	 * Fill 10 array fields with "b"
	 * @param fillWithMines
	 * @return filledField with "g" and 10 times "b"
	 */
	private String[][] insertb(String fillWithMines[][]){
		
		Random rand = new Random();
		try{
			for(int i = 0; i < numberOfMines; i++){
				int mrow = rand.nextInt(row);
				int ncolumn = rand.nextInt(column);
				fillWithMines[mrow][ncolumn] = bomb;
			}
		}catch(IllegalArgumentException er){
			log.error("Error: ",er.getMessage());
		}catch(ArrayIndexOutOfBoundsException er){
			log.error("Error: " +er.getMessage());
		}
		
		return fillWithMines;
	}
	
	private String[][] UserField(String t[][]){
		
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	t[i][j] = userHiddenField;
		    }
		}
		return t;
	}



public StringBuilder printField(String[][] field){
		
		StringBuilder result = new StringBuilder();
	
		for(int j = 0; j < 10; j++){
			
			for(int i= 0; i < 10; i++){
				result.append(field[i][j]).append(" ");
				if((field[i][j].equals("x") && filledField[i][j].equals("b")) || (field[i][j].equals("f") && filledField[i][j].equals("b")))
					sizeOfxAndfWithBomb++;
				if(field[i][j].equals("x") || field[i][j].equals("f"))
					sizeOfxAndfWithoutBomb++;
			}
			result.append("\n");
		}
		return result;
	}

	

	
	public void setUserField(int i, int j){
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		if(stringnumber.equals(userBombField))
			openAllBlanks(i, j);
		UserField[i][j] = stringnumber;
	}
	
	public void setFlag(int i, int j){
		UserField[i][j] = "f";
	}
	

	
	private void openAllBlanks(int i, int j){
		
		String stringnumber = openAllBlanksHelper(i, j);
		
		UserField[i][j] = stringnumber;

		if(i < 9){
			openAllBlanksI9(i,j,stringnumber);
		}
		if(i > 0){
			openAllBlanksI0(i,j,stringnumber);
		}
		if(j < 9){
			openAllBlanksJ9(i,j,stringnumber);
		}
		if(j > 0){
			openAllBlanksJ0(i,j,stringnumber);
		}
		else
			return;

	}
	private void openAllBlanksI9(int i, int j, String stringnumber){
		String plusI = openAllBlanksHelper(i+1,j);
		if((plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals(userHiddenField)))
			openAllBlanks(i+1,j);
		else if((stringnumber.equals(userBombField)) && (!plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals("x")))
			UserField[i+1][j] = openAllBlanksHelper(i+1, j);
	}
	private void openAllBlanksI0(int i, int j, String stringnumber){
		String minusI = openAllBlanksHelper(i-1,j);
		if((minusI.equals(userBombField)) && (getUserFieldSimple(i-1, j).equals(userHiddenField)))
			openAllBlanks(i-1,j);
		else if((stringnumber.equals(userBombField)) && (!minusI.equals(userBombField)) && (getUserFieldSimple(i-1,j).equals("x")))
			UserField[i-1][j] = openAllBlanksHelper(i-1, j);
	}
	private void openAllBlanksJ9(int i, int j, String stringnumber){
		String plusJ = openAllBlanksHelper(i,j+1);
		if((plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals(userHiddenField)))
			openAllBlanks(i,j+1);
		else if((stringnumber.equals(userBombField)) && (!plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals("x")))
			UserField[i][j+1] = openAllBlanksHelper(i, j+1);
	}
	private void openAllBlanksJ0(int i, int j, String stringnumber){
		String minusJ = openAllBlanksHelper(i,j-1);
		if((minusJ.equals(userBombField)) && (getUserFieldSimple(i,j-1).equals(userHiddenField)))
			openAllBlanks(i,j-1);
		else if((stringnumber.equals(userBombField)) && (!minusJ.equals(userBombField)) && (getUserFieldSimple(i,j-1).equals("x")))
			UserField[i][j-1] = openAllBlanksHelper(i, j-1);
	}
	
	private String openAllBlanksHelper(int i, int j){
		return String.valueOf(getNumberMinesNearField(i,j));
	}

	private int getNumberMinesNearField(int i, int j){
		int number = 0;
		int[] Ii = {i,i-1,i+1};
		int[] Jj = {j,j-1,j+1};
		
		for(int ii: Ii){
			for(int jj: Jj){
				if(inBoundsHelper(ii,jj))
					number += getNumberMinesNearFieldHelper(ii,jj);
			}
		}
		
		return number;
	}
	
	private int getNumberMinesNearFieldHelper(int ii, int jj){
		int number = 0;
		if(filledField[ii][jj].equals(bomb)){
			number = 1;
		}
		return number;
	}
	
	private boolean inBoundsHelper(int i, int j){
		int length = filledField.length -1; //9
		boolean inBounds = false;
		if((i>=0)&&(i<=length)&&(j>=0)&&(j<=length))
			inBounds = true;
			
		return inBounds;		
	}
	
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
	public int getNumberOfMines(){
		return numberOfMines;
	}
	public String[][] getfilledField(){
		return filledField;
	}
	public String[][] getUserField(){
		return UserField;
	}
	
	public String getUserFieldSimple(int i, int j){
		return UserField[i][j];
	}
	
	public int getsizeOfxAndfWithBomb(){
		return sizeOfxAndfWithBomb;
	}
	public int getsizeOfxAndfWithoutBomb(){
		return sizeOfxAndfWithoutBomb;
	}
	
	public void resetSizeOFBoMB(){
		sizeOfxAndfWithoutBomb = 0;
		sizeOfxAndfWithBomb = 0;
	}



}
