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

		filledField = insertbaad(insertGood(filledField));
		UserField = UserField(UserField);
		return UserField;
	}
	

	/**
	 * Fill with Blanks
	 * @param fillWithBlanks
	 * @return double array with value "good"
	 */
	private String[][] insertGood(String fillWithBlanks[][]){
		
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	fillWithBlanks[i][j] = "good";
		    }
		}
		return fillWithBlanks;
	}
	/**
	 * Fill 10 array fields with "baad"
	 * @param fillWithMines
	 * @return filledField with "good" and 10 times "baad"
	 */
	private String[][] insertbaad(String fillWithMines[][]){
		
		Random rand = new Random();
		
		for(int i = 0; i <= numberOfMines; i++){
			int m = rand.nextInt(10);
			int n = rand.nextInt(10);
			fillWithMines[m][n] = "baad";
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
	
	/**
	 * Prints the Field that was created on Field.java
	 * @param filledField
	 * @return String Field
	 */
	private String OriginalField(String[][] filledField){
		StringBuilder result = new StringBuilder();
		

		for(int j = 0; j < 10; j++){
			
			for(int i= 0; i < 10; i++){
				result.append(filledField[j][i]).append(" ");
			}
			result.append("\n");
		}
		
		return result.toString();
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
		if(getfilledField()[i][j].equals("good"))
			if(!stringnumber.equals("0")){
				UserField[i][j] = stringnumber;
				return;
			} else
				UserField[i][j] = "0";
		/*openAllBlanks(i+1, j);*/
	
		openAllBlanks(i-1, j);
		/*openAllBlanks(i, j+1);
		openAllBlanks(i, j-1);
		openAllBlanks(i+1, j+1);
		openAllBlanks(i+1, j-1);
		openAllBlanks(i-1, j+1);
		openAllBlanks(i-1, j-1);
*/
	}
	
	public String[][] getUserField(){
		return UserField;
	}
	
	
	private int getNumberMinesNearField(int i, int j){
		int number = 0;
		if((i>=0) && (i < 9) && (j>=0) && (j < 10)){
			if(filledField[i+1][j].equals("baad")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 10)){
			 if(filledField[i-1][j].equals("baad")){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i][j+1].equals("baad")){
				number++;
			}
		}
		if((i>=0) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i][j-1].equals("baad")){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=0) && (j < 9)){
			 if(filledField[i+1][j+1].equals("baad")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=1) && (j < 10)){
			 if(filledField[i-1][j-1].equals("baad")){
				number++;
			}
		}
		if((i>=0) && (i < 9) && (j>=1) && (j < 10)){
			 if(filledField[i+1][j-1].equals("baad")){
				number++;
			}
		}
		if((i>=1) && (i < 10) && (j>=0) && (j < 9)){
			 if(filledField[i-1][j+1].equals("baad")){
				number++;
			}
		}
		
		return number;
	}
	


}
