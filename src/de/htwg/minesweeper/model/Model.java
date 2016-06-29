package de.htwg.minesweeper.model;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {
	
	private int row;;
	private int column;
	private int numberOfMines;
	
	private static final String BOMB = "b";
	private static final String FREE_FIELD = "g";
	private static final String USER_HIDDEN_FIELD = "x";
	private static final String FREE_USER_FIELD = "0";
	private static final String FLAG = "f";
	
	
	private String bomb = BOMB;
	private String freeField = FREE_FIELD;
	private String userHiddenField = USER_HIDDEN_FIELD;
	private String flag = FLAG;


	private String freeUserField = FREE_USER_FIELD;
	
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
	
	public String[][] setOneBomb(int i, int j, String fillWithMines[][]){
			fillWithMines[i][j] = bomb;
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
		for(int j = 0; j < column; j++){
			for(int i= 0; i < row; i++){
				if((UserField[i][j].equals(userHiddenField) && getfilledField()[i][j].equals(bomb)) || (UserField[i][j].equals(flag) && getfilledField()[i][j].equals(bomb)))
					setsizeOfxAndfWithBomb(getsizeOfxAndfWithBomb()+1);

				if(UserField[i][j].equals(userHiddenField) || UserField[i][j].equals(flag))
					setsizeOfxAndfWithoutBomb(getsizeOfxAndfWithoutBomb() +1);
			}
		}
	}

	public void setUserField(int i, int j){
		String stringnumber = String.valueOf(getNumberMinesNearField(i, j));
		if(stringnumber.equals(freeUserField))
			openAllBlanks(i, j);
		UserField[i][j] = stringnumber;
		countIfGameIsWon();
	}
	
	public void setFlag(int i, int j){
		UserField[i][j] = flag;
	}
	public void resetFlag(int i, int j){
		UserField[i][j] = userHiddenField;
	}
	
	void openAllBlanks(int i, int j){
		
		String stringnumber = openAllBlanksHelper(i, j);
		
 		UserField[i][j] = stringnumber;

		if(i < row-1){
			openAllBlanksI9(i,j,stringnumber);
		}
		
		if(i > 0){
			openAllBlanksI0(i,j,stringnumber);
		}
		
		if(j < column-1){
			openAllBlanksJ9(i,j,stringnumber);
		}
		
		if(j > 0){
			openAllBlanksJ0(i,j,stringnumber);
		}
		
		if(i > 0 && j > 0){
			openAllBlanksI0J0(i,j,stringnumber);
		}
		
		if(i < row-1 && j < column-1){
			openAllBlanksI9J9(i,j,stringnumber);
		}
		
		if((i >= 0) && (j < column) && (i < row-1) && (j > 0)){
			openAllBlanksI9J0(i,j,stringnumber);
		}
		
		if((i > 0) && (j < column-1) && (i < row) && (j >= 0)){
			openAllBlanksI0J9(i,j,stringnumber);
		}


		else
			return;
	}
	
	void openAllBlanksI9(int i, int j, String stringnumber){
		i = i+1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	
	void openAllBlanksI0(int i, int j, String stringnumber){
		i = i-1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i, j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	
	void openAllBlanksJ9(int i, int j, String stringnumber){
		j = j+1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	
	void openAllBlanksJ0(int i, int j, String stringnumber){
		j = j-1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	
	void openAllBlanksI0J0(int i, int j, String stringnumber){
		i = i-1;
		j = j-1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	void openAllBlanksI9J9(int i, int j, String stringnumber){
		i = i+1;
		j = j+1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	void openAllBlanksI9J0(int i, int j, String stringnumber){
		i = i+1;
		j = j-1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
	}
	void openAllBlanksI0J9(int i, int j, String stringnumber){
		i = i-1;
		j = j+1;
		String numberBombs = openAllBlanksHelper(i,j);
		if((numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			openAllBlanks(i,j);
		else if((stringnumber.equals(freeUserField)) && (!numberBombs.equals(freeUserField)) && (getUserFieldSimple(i,j).equals(userHiddenField)))
			UserField[i][j] = openAllBlanksHelper(i, j);
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
