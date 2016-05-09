package de.htwg.minesweeper;
import java.util.Random;


public class Field {
	
	int row = 10;
	int column = 10;
	int numberOfMines = 10;
	
	//Main Field
	private String filledField[][] = new String[column][row];
	
	Field(){
		String filledField[][] = this.filledField;
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

		filledField = insertGood(filledField);
		filledField = insertBad(filledField);
		
		return filledField;
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
	 * Fill 10 array fields with "bad"
	 * @param fillWithMines
	 * @return filledField with "good" and 10 times "bad"
	 */
	private String[][] insertBad(String fillWithMines[][]){
		
		Random rand = new Random();
		
		for(int i = 0; i <= numberOfMines; i++){
			int m = rand.nextInt(10);
			int n = rand.nextInt(10);
			fillWithMines[m][n] = "bad";
		}
		
		return fillWithMines;
	}
}
