package de.htwg.minesweeper.model;
import java.util.Observable;
import java.util.Random;


public class Field extends Observable {
	
	private int row;;
	private int column;
	private int numberOfMines;
	

	
	//Main Field
	private String filledField[][];
	private String UserField[][];
	
	public Field(){
		this.row=10;
		this.column = 10;
		this.numberOfMines = 10;
		this.filledField = new String[column][row];
		this.UserField = new String[column][row];
	}
	
	/**
	 * Test Method 
	 * @param args
	 */
	public static void main(String[] args){
		Field test = new Field();
		test.filledField = test.setupField();
		
		for(String[] row: test.filledField){
			for(String value: row){
				
				System.out.println(value);
			}
		}
	}
	

	/**
	 * Setup the Field and fill with Mines and Blanks
	 * @return the filled Field
	 */
	public String[][] setupField(){

		filledField = insertb(insertg(filledField));
		UserField = UserField(UserField);
		return UserField;
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
		    	fillWithBlanks[i][j] = "g";
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
		
		for(int i = 0; i <= numberOfMines; i++){
			int m = rand.nextInt(10);
			int n = rand.nextInt(10);
			fillWithMines[m][n] = "b";
		}
		
		return fillWithMines;
	}
	
	private String[][] UserField(String t[][]){
		
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	t[i][j] = "x";
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
	
	
	
	public String[][] getfilledField(){
		return filledField;
	}
	
	public void setUserField(int i, int j){
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		if(stringnumber.equals("0"))
			openAllBlanks(i, j);
		UserField[i][j] = stringnumber;
	}
	

	
	private void openAllBlanks(int i, int j){
		
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		
		UserField[i][j] = stringnumber;
		
		
		if(i < 9){
			String plusI = String.valueOf(getNumberMinesNearField(i+1,j));
			if((plusI.equals("0")) && (getUserFieldSimple(i+1,j).equals("x")))
				openAllBlanks(i+1,j);
			else if((stringnumber.equals("0")) && (!plusI.equals("0")) && (getUserFieldSimple(i+1,j).equals("x")))
				UserField[i+1][j] = String.valueOf(getNumberMinesNearField(i+1, j));
		}
		if(i > 0){
			String minusI = String.valueOf(getNumberMinesNearField(i-1,j));
			if((minusI.equals("0")) && (getUserFieldSimple(i-1, j).equals("x")))
				openAllBlanks(i-1,j);
			else if((stringnumber.equals("0")) && (!minusI.equals("0")) && (getUserFieldSimple(i-1,j).equals("x")))
				UserField[i-1][j] = String.valueOf(getNumberMinesNearField(i-1, j));
		}
		if(j < 9){
			String plusJ = String.valueOf(getNumberMinesNearField(i,j+1));
			if((plusJ.equals("0")) && (getUserFieldSimple(i,j+1).equals("x")))
				openAllBlanks(i,j+1);
			else if((stringnumber.equals("0")) && (!plusJ.equals("0")) && (getUserFieldSimple(i,j+1).equals("x")))
				UserField[i][j+1] = String.valueOf(getNumberMinesNearField(i, j+1));
		}
		if(j > 0){
			String minusJ = String.valueOf(getNumberMinesNearField(i,j-1));
			if((minusJ.equals("0")) && (getUserFieldSimple(i,j-1).equals("x")))
				openAllBlanks(i,j-1);
			else if((stringnumber.equals("0")) && (!minusJ.equals("0")) && (getUserFieldSimple(i,j-1).equals("x")))
				UserField[i][j-1] = String.valueOf(getNumberMinesNearField(i, j-1));
		}
		else
			return;

	}

	
	
	public String[][] getUserField(){
		return UserField;
	}
	
	public String getUserFieldSimple(int i, int j){
		return UserField[i][j];
	}
	
	
	private int getNumberMinesNearField(int i, int j){
		int number = 0;
		if((i>=0) && (i < 9) && (j>=0) && (j < 10)){
			if(filledField[i+1][j].equals("b")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 10)){
			 if(filledField[i-1][j].equals("b")){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i][j+1].equals("b")){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i][j-1].equals("b")){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=0) && (j < 9)){
			 if(filledField[i+1][j+1].equals("b")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i-1][j-1].equals("b")){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=1) && (j < 10)){
			 if(filledField[i+1][j-1].equals("b")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i-1][j+1].equals("b")){
				number++;
			}
		}
		
		return number;
	}
	


}
