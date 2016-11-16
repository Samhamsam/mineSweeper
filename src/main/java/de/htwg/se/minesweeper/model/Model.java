package de.htwg.se.minesweeper.model;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {
	
	private int row;
	private int column;
	private int numberOfMines;
	
	private static final String BOMB = "b";
	private static final String FREE_FIELD = "g";
	private static final String USER_HIDDEN_FIELD = "x";
	private static final String FREE_USER_FIELD = "0";
	private static final String FLAG = "f";
	
	private String bombString = BOMB;

	private String freeFieldString = FREE_FIELD;
	private String userHiddenFieldString = USER_HIDDEN_FIELD;
	private String flagString = FLAG;

	private String freeUserField = FREE_USER_FIELD;
	
	private int sizeOfxAndfWithBomb = 0;
	private int sizeOfxAndfWithoutBomb = 0;
	
	private String[][] filledField;
	private String[][] userField;
	
	public Model(int i, int j, int k){
		this.row=i;
		this.column = j;
		this.numberOfMines = k;
		this.filledField = new String[column][row];
		filledField = insertb(insertg(filledField));
		userField = userField();
	}

	private String[][] insertg(String[][] fillWithBlanks){
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	fillWithBlanks[i][j] = freeFieldString;
		    }
		}
		return fillWithBlanks;
	}

	private String[][] insertb(String[][] fillWithMines){
		Random rand = new Random();

		for(int i = 0; i < numberOfMines; i++) {
			int mrow = rand.nextInt(row);
			int ncolumn = rand.nextInt(column);
			fillWithMines[mrow][ncolumn] = bombString;
		}

		return fillWithMines;
	}
	
	public String[][] setOneBomb(int i, int j, String[][] fillWithMines){
			fillWithMines[i][j] = bombString;
		return fillWithMines;
	}
	
	public String[][] userField(){
		
		String[][] t = new String[column][row];
		
		for (int i = 0; i < column; i++)
		{
		    for (int j = 0; j < row; j++)
		    {
		    	t[i][j] = userHiddenFieldString;
		    }
		}
		return t;
	}

	public void countIfGameIsWon(){
		for(int j = 0; j < column; j++){
			for(int i= 0; i < row; i++){
				boolean one = userField[i][j].equals(userHiddenFieldString) && getfilledField()[i][j].equals(bombString);
				boolean two = userField[i][j].equals(flagString) && getfilledField()[i][j].equals(bombString);
				if(one || two)
					setsizeOfxAndfWithBomb(getsizeOfxAndfWithBomb()+1);

				if(userField[i][j].equals(userHiddenFieldString) || userField[i][j].equals(flagString))
					setsizeOfxAndfWithoutBomb(getsizeOfxAndfWithoutBomb() +1);
			}
		}
	}

	public void setUserField(int i, int j){
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		if(stringnumber.equals(freeUserField))
			openAllBlanks(i, j);
		userField[i][j] = stringnumber;
		countIfGameIsWon();
	}
	
	public void setFlag(int i, int j){
		userField[i][j] = flagString;
	}
	public void resetFlag(int i, int j){
		userField[i][j] = userHiddenFieldString;
	}
	
	void openAllBlanks(int i, int j){
		
		String stringNumber = openAllBlanksHelper(i, j);
		
		userField[i][j] = stringNumber;

 		openAllBlanksSimpleHelper(i,j,stringNumber);
		
 		openAllBlanksDifficultHelper(i,j,stringNumber);

		
	}
	
	private void openAllBlanksSimpleHelper(int i, int j, String stringNumber){
		if(i < row-1){
			openAllBlanksI9(i,j,stringNumber);
		}
		
		if(i > 0){
			openAllBlanksI0(i,j,stringNumber);
		}
		
		if(j < column-1){
			openAllBlanksJ9(i,j,stringNumber);
		}
		
		if(j > 0){
			openAllBlanksJ0(i,j,stringNumber);
		}
	}
	
	private void openAllBlanksDifficultHelper(int i, int j, String stringNumber){

		openAllBlanksDifficultHelper1(i,j,stringNumber);
		openAllBlanksDifficultHelper2(i,j,stringNumber);


	}
	
	private void openAllBlanksDifficultHelper1(int i, int j, String stringNumber){
		if(i > 0 && j > 0){
			openAllBlanksI0J0(i,j,stringNumber);
		}
		
		if(i < row-1 && j < column-1){
			openAllBlanksI9J9(i,j,stringNumber);
		}
	}
	private void openAllBlanksDifficultHelper2(int i, int j, String stringNumber){
		if((i >= 0) && (j < column) && (i < row-1) && (j > 0)){
			openAllBlanksI9J0(i,j,stringNumber);
		}
		
		if((i > 0) && (j < column-1) && (i < row) && (j >= 0)){
			openAllBlanksI0J9(i,j,stringNumber);
		}
	}
	
	void openAllBlanksI9(int i, int j, String stringNumber){
		int variableX = i+1;
		int variableY = j;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	
	void openAllBlanksI0(int i, int j, String stringNumber){
		int variableX = i-1;
		int variableY = j;
		openAllBlanksChecker(variableX, variableY, stringNumber);
		
	}
	
	void openAllBlanksJ9(int i, int j, String stringNumber){
		int variableX = i;
		int variableY = j+1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	
	void openAllBlanksJ0(int i, int j, String stringNumber){
		int variableX = i;
		int variableY = j-1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	
	void openAllBlanksI0J0(int i, int j, String stringNumber){
		int variableX = i-1;
		int variableY = j-1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	void openAllBlanksI9J9(int i, int j, String stringNumber){
		int variableX = i+1;
		int variableY = j+1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	void openAllBlanksI9J0(int i, int j, String stringNumber){
		int variableX = i+1;
		int variableY = j-1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	void openAllBlanksI0J9(int i, int j, String stringNumber){
		int variableX = i-1;
		int variableY = j+1;
		openAllBlanksChecker(variableX, variableY, stringNumber);
	}
	
	private void openAllBlanksChecker(int variableX, int variableY, String stringNumber){
		String numberBombs = openAllBlanksHelper(variableX,variableY);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(variableX,variableY).equals(userHiddenFieldString)))
			openAllBlanks(variableX,variableY);
		else if((stringNumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(variableX,variableY).equals(userHiddenFieldString)))
			userField[variableX][variableY] = openAllBlanksHelper(variableX, variableY);
	}
	
	private String openAllBlanksHelper(int i, int j){
		return String.valueOf(getNumberMinesNearField(i,j));
	}

	private int getNumberMinesNearField(int i, int j){
		int number = 0;
		int[] iii = {i,i-1,i+1};
		int[] jjj = {j,j-1,j+1};
		
		for(int ii: iii){
			for(int jj: jjj){
				if(inBoundsHelper(ii,jj))
					number += getNumberMinesNearFieldHelper(ii,jj);
			}
		}
		return number;
	}
	
	private int getNumberMinesNearFieldHelper(int ii, int jj){
		int number = 0;
		if(filledField[ii][jj].equals(bombString)){
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
		return userField;
	}
	
	public String getUserFieldSimple(int i, int j){
		return userField[i][j];
	}
	
	public void setUserFieldSimple(int i, int j, String a){
		userField[i][j] = a;
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
		return userHiddenFieldString;
	}


	public void setUserHiddenField(String userHiddenFieldString) {
		this.userHiddenFieldString = userHiddenFieldString;
	}
	
	public String getBombString() {
		return bombString;
	}


	public void setBombString(String bombString) {
		this.bombString = bombString;
	}
	
	public String getFlagString() {
		return flagString;
	}


	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}
}
