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
	private String userHidenField = "x";
	private String userBombField = "0";
	

	
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
			for(int i = 0; i <= numberOfMines; i++){
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
		    	t[i][j] = userHidenField;
		    }
		}
		return t;
	}



public StringBuilder printField(String[][] filledField){
		
		StringBuilder result = new StringBuilder();
	
		for(int j = 0; j < 10; j++){
			
			for(int i= 0; i < 10; i++){
				result.append(filledField[i][j]).append(" ");
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
	

	
	private void openAllBlanks(int i, int j){
		
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		
		UserField[i][j] = stringnumber;
		
		
		if(i < 9){
			String plusI = String.valueOf(getNumberMinesNearField(i+1,j));
			if((plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals(userHidenField)))
				openAllBlanks(i+1,j);
			else if((stringnumber.equals(userBombField)) && (!plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals("x")))
				UserField[i+1][j] = String.valueOf(getNumberMinesNearField(i+1, j));
		}
		if(i > 0){
			String minusI = String.valueOf(getNumberMinesNearField(i-1,j));
			if((minusI.equals(userBombField)) && (getUserFieldSimple(i-1, j).equals(userHidenField)))
				openAllBlanks(i-1,j);
			else if((stringnumber.equals(userBombField)) && (!minusI.equals(userBombField)) && (getUserFieldSimple(i-1,j).equals("x")))
				UserField[i-1][j] = String.valueOf(getNumberMinesNearField(i-1, j));
		}
		if(j < 9){
			String plusJ = String.valueOf(getNumberMinesNearField(i,j+1));
			if((plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals(userHidenField)))
				openAllBlanks(i,j+1);
			else if((stringnumber.equals(userBombField)) && (!plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals("x")))
				UserField[i][j+1] = String.valueOf(getNumberMinesNearField(i, j+1));
		}
		if(j > 0){
			String minusJ = String.valueOf(getNumberMinesNearField(i,j-1));
			if((minusJ.equals(userBombField)) && (getUserFieldSimple(i,j-1).equals(userHidenField)))
				openAllBlanks(i,j-1);
			else if((stringnumber.equals(userBombField)) && (!minusJ.equals(userBombField)) && (getUserFieldSimple(i,j-1).equals("x")))
				UserField[i][j-1] = String.valueOf(getNumberMinesNearField(i, j-1));
		}
		else
			return;

	}


	private int getNumberMinesNearField(int i, int j){
		int number = 0;
		if((i>=0) && (i < 9) && (j>=0) && (j < 10)){
			if(filledField[i+1][j].equals(bomb)){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 10)){
			 if(filledField[i-1][j].equals(bomb)){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i][j+1].equals(bomb)){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i][j-1].equals(bomb)){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=0) && (j < 9)){
			 if(filledField[i+1][j+1].equals(bomb)){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i-1][j-1].equals(bomb)){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=1) && (j < 10)){
			 if(filledField[i+1][j-1].equals(bomb)){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i-1][j+1].equals(bomb)){
				number++;
			}
		}
		
		return number;
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


}
