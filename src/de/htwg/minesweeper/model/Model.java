package de.htwg.minesweeper.model;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {
	
	private int row;;
	private int column;
	private int numberOfMines;
	
	private String bomb = "b";
	private String freeField = "g";
	private String userHiddenField = "x";


	private String userBombField = "0";
	
	private int sizeOfxAndfWithBomb = 0;
	private int sizeOfxAndfWithoutBomb = 0;
	
	private String filledField[][];
	private String UserField[][];
	
	public Model(int i, int j, int k){
		this.row=i;
		this.column = j;
		this.numberOfMines = k;
		this.filledField = new String[column][row];
		filledField = insertb(insertg(filledField));
		UserField = UserField();
	}

	
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

	private String[][] insertb(String fillWithMines[][]){
		Random rand = new Random();

		for(int i = 0; i < numberOfMines; i++) {
			int mrow = rand.nextInt(row);
			int ncolumn = rand.nextInt(column);
			fillWithMines[mrow][ncolumn] = bomb;
		}

		return fillWithMines;
	}
	
	public String[][] UserField(){
		String t[][] = new String[column][row];
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	t[i][j] = userHiddenField;
		    }
		}
		return t;
	}

	public void countIfGameIsWon(){
		for(int j = 0; j < 10; j++){
			for(int i= 0; i < 10; i++){
				if((UserField[i][j].equals("x") && getfilledField()[i][j].equals("b")) || (UserField[i][j].equals("f") && getfilledField()[i][j].equals("b")))
					setsizeOfxAndfWithBomb(getsizeOfxAndfWithBomb()+1);

				if(UserField[i][j].equals("x") || UserField[i][j].equals("f"))
					setsizeOfxAndfWithoutBomb(getsizeOfxAndfWithoutBomb() +1);
			}
		}
	}

	public void setUserField(int i, int j){
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		if(stringnumber.equals(userBombField))
			openAllBlanks(i, j);
		UserField[i][j] = stringnumber;
		countIfGameIsWon();
	}
	
	public void setFlag(int i, int j){
		UserField[i][j] = "f";
	}
	public void resetFlag(int i, int j){
		UserField[i][j] = "x";
	}
	
	void openAllBlanks(int i, int j){
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
	
	void openAllBlanksI9(int i, int j, String stringnumber){
		String plusI = openAllBlanksHelper(i+1,j);
		if((plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals(userHiddenField)))
			openAllBlanks(i+1,j);
		else if((stringnumber.equals(userBombField)) && (!plusI.equals(userBombField)) && (getUserFieldSimple(i+1,j).equals("x")))
			UserField[i+1][j] = openAllBlanksHelper(i+1, j);
	}
	
	void openAllBlanksI0(int i, int j, String stringnumber){
		String minusI = openAllBlanksHelper(i-1,j);
		if((minusI.equals(userBombField)) && (getUserFieldSimple(i-1, j).equals(userHiddenField)))
			openAllBlanks(i-1,j);
		else if((stringnumber.equals(userBombField)) && (!minusI.equals(userBombField)) && (getUserFieldSimple(i-1,j).equals("x")))
			UserField[i-1][j] = openAllBlanksHelper(i-1, j);
	}
	
	void openAllBlanksJ9(int i, int j, String stringnumber){
		String plusJ = openAllBlanksHelper(i,j+1);
		if((plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals(userHiddenField)))
			openAllBlanks(i,j+1);
		else if((stringnumber.equals(userBombField)) && (!plusJ.equals(userBombField)) && (getUserFieldSimple(i,j+1).equals("x")))
			UserField[i][j+1] = openAllBlanksHelper(i, j+1);
	}
	
	void openAllBlanksJ0(int i, int j, String stringnumber){
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
	
	/**
	 * 
	 * @return the Field with b and g
	 */
	public String[][] getfilledField(){
		return filledField;
	}
	
	/**
	 * 
	 * @return the field with x
	 */
	public String[][] getUserField(){
		return UserField;
	}
	
	public String getUserFieldSimple(int i, int j){
		return UserField[i][j];
	}
	
	public void setUserFieldSimple(int i, int j, String a){
		UserField[i][j] = a;
	}
	
	public void setsizeOfxAndfWithBomb(int sizeOfXAndWithBomb){
		this.sizeOfxAndfWithBomb = sizeOfXAndWithBomb;
	}
	
	public int getsizeOfxAndfWithBomb(){
		return sizeOfxAndfWithBomb;
	}
	
	public void setsizeOfxAndfWithoutBomb(int sizeOfxAndfWithoutBomb) {
		this.sizeOfxAndfWithoutBomb = sizeOfxAndfWithoutBomb;
	}
	
	public int getsizeOfxAndfWithoutBomb(){
		return sizeOfxAndfWithoutBomb;
	}
	
	public void resetSizeOFBoMB(){
		sizeOfxAndfWithoutBomb = 0;
		sizeOfxAndfWithBomb = 0;
	}
	
	public String getUserHiddenField() {
		return userHiddenField;
	}


	public void setUserHiddenField(String userHiddenField) {
		this.userHiddenField = userHiddenField;
	}
}
