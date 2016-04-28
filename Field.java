package minesweeper;
import java.util.Random;


public class Field {
	
	int row = 10;
	int column = 10;
	
	
	private String filledField[][] = new String[column][row];
	
	public static void main(String[] args){
		Field test = new Field();
		test.filledField = test.setupField();
		
		for(String[] row: test.filledField){
			for(String value: row){
				
				System.out.println(value);
			}
		}
	}
	
	
	private String[][] setupField(){

		filledField = insertGood(filledField);
		filledField = insertBad(filledField);
		
		return filledField;
	}
	
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

	
	private String[][] insertBad(String fillWithMines[][]){
		
		Random rand = new Random();
		
		for(int i = 0; i < 11; i++){
			int m = rand.nextInt(10);
			int n = rand.nextInt(10);
			fillWithMines[m][n] = "bad";
		}
		
		return fillWithMines;
	}
}