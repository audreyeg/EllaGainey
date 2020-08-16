import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * GameBoard class that is called to place token, rotate grid, and print board.
 *  
 * @author Ella Gainey
 * @version August 11 2020
 */
public class GameBoard {

	/** private field that represents initial grid1 (top left grid of board)*/
	private char[] grid1= {'*', '*', '*', '*', '*', '*', '*', '*', '*', 0};

	/** private field that represents initial grid2 (top right grid of board)*/
	private char[] grid2 = {'*', '*', '*', '*', '*', '*', '*', '*', '*', 0};

	/** private field that represents initial grid3 (bottom left grid of board)*/
	private char[] grid3 = {'*', '*', '*', '*', '*', '*', '*', '*', '*', 0};

	/** private field that represents initial grid4 (bottom right grid of board)*/
	private char[] grid4 = {'*', '*', '*', '*', '*', '*', '*', '*', '*', 0};

	/** private field that makes sure move is valid*/
	private boolean valid;

	/** private field that keeps track of the turn count*/
	private int turnCount;

	/** private field that keeps track of index of best board play for AI class*/
	private int aiIndex;


	/**
	 * Default Constructor
	 *  prints initial empty board
	 */
	public GameBoard() {
		
		//printBoard();
	}

	/**
	 * Copy constructor
	 * @param b - GameBoard being copied
	 *  Sets grids of the board. 
	 */
	public GameBoard(GameBoard b) {
		List<char[]> temp = b.copy();
		grid1 = temp.get(0);
		grid2 = temp.get(1);
		grid3 = temp.get(2);
		grid4 = temp.get(3);
	}

	/**
	 * Another constructor. To ensure the original board isn't being modified. 
	 *  @param one - grid 1
	 *  @param two - grid 2
	 *  @param three - grid 3
	 *  @param four - grid 4
	 */
	public GameBoard(char[] one, char[] two, char[] three, char[] four) {
		grid1 = one;
		grid2 = two;
		grid3 = three;
		grid4 = four;
	}
	
	/**
	 * Constructor
	 *  @param board - instance of the game board 
	 *  @param block - grid 1 - 4 for tile to placed in 
	 *  @param space - space 1 - 9 to place tile
	 *  @param turn - keeps track of human or AI turn 
	 *  
	 */
	public GameBoard(GameBoard board, String block, String space, int turn) {
		turnCount = turn;
		
		List<char[]> temp = board.copy();
		grid1 = temp.get(0);
		grid2 = temp.get(1);
		grid3 = temp.get(2);
		grid4 = temp.get(3);

		//place tile in grid 1
		if (block.equals("1")) {
			grid1(space);
		}
		//place tile in grid 2
		else if (block.equals("2")) {
			grid2(space);
		}

		//place tile in grid 3
		else if (block.equals("3")) {
			grid3(space);
		}

		//place tile in grid 4
		else if (block.equals("4")) {
			grid4(space);
		}

		//only print board if a valid move was made
//		if (valid) {
//			printBoard();
//		}	
	}
	
	
	/**
	 * Method that calls the appropriate rotation on the appropriate grid
	 * @param board - current instance of board
	 * @param rotateBlock - grid to be rotated (1,2,3,4)
	 * @param rotateDirection - direction to rotate grid (either left or right)
	 */
	public void rotateBoard(GameBoard board, String rotateBlock, String rotateDirection) {
		
		//grid 1 to be rotated either left or right
		if (rotateBlock.equals("1")) {
			grid1Rotate(rotateDirection);
		}
		
		//grid 2 to be rotated either left or right
		else if (rotateBlock.equals("2")) {
			grid2Rotate(rotateDirection);
		}
		
		//grid 3 to be rotated either left or right
		else if (rotateBlock.equals("3")) {
			grid3Rotate(rotateDirection);
		}
		
		//grid 4 to be rotated either left or right
		else if (rotateBlock.equals("4")) {
			grid4Rotate(rotateDirection);
		}
	}

	/**
	 * Method that places a tile in grid 1 (top left grid)
	 * @param space - tile (1-9) to place token in 
	 */
	char[] grid1(String space) {
		int index = Integer.parseInt(space);
		//check if space is empty
		if ((grid1[index - 1] == 'w') || (grid1[index - 1] == 'b')){
			System.out.println("invalid move");
		}
		//tile is empty
		else {
			valid = true;
			if (turnCount % 2 == 0) {
				grid1[index - 1] = 'b';
			}
			else {
				grid1[index - 1] = 'w';
			}
		}
		return grid1;
	}

	/**
	 * Method that rotates grid 1
	 * @param rotateDirection - l or r (left or right) rotation
	 */
	private  void grid1Rotate(String rotateDirection) {
		//left rotation
		if (rotateDirection.equals("L")) {
			char[] tempL = {grid1[2], grid1[5], grid1[8], grid1[1], grid1[4], grid1[7],
					grid1[0], grid1[3], grid1[6]};
			grid1 = tempL;
		}
		//right rotation
		else if (rotateDirection.equals("R")) {
			char[] tempR = {grid1[6], grid1[3], grid1[0], grid1[7], grid1[4], grid1[1],
					grid1[8], grid1[5], grid1[2]};
			grid1 = tempR;
		}
	}

	/**
	 * Method that places a tile in grid 2 (top right grid)
	 * @param space - tile (1-9) to place token in 
	 */
	char[] grid2(String space) {
		int index = Integer.parseInt(space);
		//check if space is empty
		if ((grid2[index - 1] == 'w') || (grid2[index - 1] == 'b')){
			System.out.println("invalid move");
		}
		//tile is empty
		else {
			valid = true;
			if (turnCount % 2 == 0) {
				grid2[index - 1] = 'b';
			}
			else {
				grid2[index - 1] = 'w';
			}
		}
		return grid2;
	}
	
	/**
	 * Method that rotates grid 2
	 * @param rotateDirection - l or r (left or right) rotation
	 */
	private void grid2Rotate(String rotateDirection) {
		//left rotation
		if (rotateDirection.equals("L")) {
			char[] tempL = {grid2[2], grid2[5], grid2[8], grid2[1], grid2[4], grid2[7],
					grid2[0], grid2[3], grid2[6]};
			grid2 = tempL;
		}
		//right rotation
		else if (rotateDirection.equals("R")) {
			char[] tempR = {grid2[6], grid2[3], grid2[0], grid2[7], grid2[4], grid2[1],
					grid2[8], grid2[5], grid2[2]};
			grid2 = tempR;
		}
	}

	/**
	 * Method that places a tile in grid 3 (bottom left grid)
	 * @param space - tile (1-9) to place token in 
	 */
	char[] grid3(String space) {
		int index = Integer.parseInt(space);
		//check if space is empty
		if ((grid3[index - 1] == 'w') || (grid3[index - 1] == 'b')){
			System.out.println("invalid move");
		}
		//tile is empty
		else {
			valid = true;
			if (turnCount % 2 == 0) {
				grid3[index - 1] = 'b';
			}
			else {
				grid3[index - 1] = 'w';
			}
		}
		return grid3;
	}

	/**
	 * Method that rotates grid 3
	 * @param rotateDirection - l or r (left or right) rotation
	 */
	private void grid3Rotate(String rotateDirection) {
		//left rotation
		if (rotateDirection.equals("L")) {
			char[] tempL = {grid3[2], grid3[5], grid3[8], grid3[1], grid3[4], grid3[7],
					grid3[0], grid3[3], grid3[6]};
			grid3 = tempL;
		}
		//right rotation
		else if (rotateDirection.equals("R")) {
			char[] tempR = {grid3[6], grid3[3], grid3[0], grid3[7], grid3[4], grid3[1],
					grid3[8], grid3[5], grid3[2]};
			grid3 = tempR;
		}
	}

	/**
	 * Method that places a tile in grid 4 (bottom right grid)
	 * @param space - tile (1-9) to place token in 
	 */
	char[] grid4(String space) {
		int index = Integer.parseInt(space);
		//check if space is empty
		if ((grid4[index - 1] == 'w') || (grid4[index - 1] == 'b')){
			System.out.println("invalid move");
		}
		//tile is empty
		else {
			valid = true;
			if (turnCount % 2 == 0) {
				grid4[index - 1] = 'b';
			}
			else {
				grid4[index - 1] = 'w';
			}
		}
		return grid4;
	}

	/**
	 * Method that rotates grid 4
	 * @param rotateDirection - l or r (left or right) rotation
	 */
	private void grid4Rotate(String rotateDirection) {
		//left rotation
		if (rotateDirection.equals("L")) {
			char[] tempL = {grid4[2], grid4[5], grid4[8], grid4[1], grid4[4], grid4[7],
					grid4[0], grid4[3], grid4[6]};
			grid4 = tempL;
		}
		//right rotation
		else if (rotateDirection.equals("R")) {
			char[] tempR = {grid4[6], grid4[3], grid4[0], grid4[7], grid4[4], grid4[1],
					grid4[8], grid4[5], grid4[2]};
			grid4 = tempR;
		}
	}

	/**
	 * Method that prints the board
	 * @param pw - print writer to write to output file
	 */
	public void printBoard(PrintWriter pw) {
		
		System.out.println("+-------+-------+");
		pw.println("+-------+-------+");
		System.out.print("|" + " " + grid1[0] + " " + grid1[1] + " " + grid1[2] + " " + "|" + " " + grid2[0] + " " + grid2[1] + " " + grid2[2] + " " + "|");
		pw.print("|" + " " + grid1[0] + " " + grid1[1] + " " + grid1[2] + " " + "|" + " " + grid2[0] + " " + grid2[1] + " " + grid2[2] + " " + "|");
		System.out.println();
		pw.println();

		System.out.print("|" + " " + grid1[3] + " " + grid1[4] + " " + grid1[5] + " " + "|" + " " + grid2[3] + " " + grid2[4] + " " + grid2[5] + " " + "|");
		pw.print("|" + " " + grid1[3] + " " + grid1[4] + " " + grid1[5] + " " + "|" + " " + grid2[3] + " " + grid2[4] + " " + grid2[5] + " " + "|");
		System.out.println();
		pw.println();

		System.out.print("|" + " " + grid1[6] + " " + grid1[7] + " " + grid1[8] + " " + "|" + " " + grid2[6] + " " + grid2[7] + " " + grid2[8] + " " + "|");
		pw.print("|" + " " + grid1[6] + " " + grid1[7] + " " + grid1[8] + " " + "|" + " " + grid2[6] + " " + grid2[7] + " " + grid2[8] + " " + "|");
		System.out.println();
		pw.println();

		System.out.println("+-------+-------+");
		pw.println("+-------+-------+");
		System.out.print("|" + " " + grid3[0] + " " + grid3[1] + " " + grid3[2] + " " + "|" + " " + grid4[0] + " " + grid4[1] + " " + grid4[2] + " " + "|");
		pw.print("|" + " " + grid3[0] + " " + grid3[1] + " " + grid3[2] + " " + "|" + " " + grid4[0] + " " + grid4[1] + " " + grid4[2] + " " + "|");
		System.out.println();
		pw.println();

		System.out.print("|" + " " + grid3[3] + " " + grid3[4] + " " + grid3[5] + " " + "|" + " " + grid4[3] + " " + grid4[4] + " " + grid4[5] + " " + "|");
		pw.print("|" + " " + grid3[3] + " " + grid3[4] + " " + grid3[5] + " " + "|" + " " + grid4[3] + " " + grid4[4] + " " + grid4[5] + " " + "|");
		System.out.println();
		pw.println();

		System.out.print("|" + " " + grid3[6] + " " + grid3[7] + " " + grid3[8] + " " + "|" + " " + grid4[6] + " " + grid4[7] + " " + grid4[8] + " " + "|");
		pw.write("|" + " " + grid3[6] + " " + grid3[7] + " " + grid3[8] + " " + "|" + " " + grid4[6] + " " + grid4[7] + " " + grid4[8] + " " + "|");
		System.out.println();
		pw.println();
		System.out.println("+-------+-------+");
		pw.println("+-------+-------+");
	}

	/**
	 * Setter for index location in list of gameBoard moves for AI class
	 * @param i - index location
	 */
	public void setIndex(int i) {
		this.aiIndex = i;
	}

	/** returns index of best move for AI player*/
	public int getIndex() {
		return aiIndex;
	}

	/** returns if move was valid or not*/
	public boolean getValidity() {
		return valid;
	}

	/** returns grid 1*/
	public char[] getGrid1() {
		char[] temp = new char[9];
		for (int i = 0; i < 9; i++) {
			temp[i] = grid1[i];
		}
		return temp;
	}

	/** returns grid 2*/
	public char[] getGrid2() {
		char[] temp = new char[9];
		for (int i = 0; i < 9; i++) {
			temp[i] = grid2[i];
		}
		return temp;
	}

	/** returns grid 3*/
	public char[] getGrid3() {
		char[] temp = new char[9];
		for (int i = 0; i < 9; i++) {
			temp[i] = grid3[i];
		}
		return temp;
	}

	/** returns grid 4*/
	public char[] getGrid4() {
		char[] temp = new char[9];
		for (int i = 0; i < 9; i++) {
			temp[i] = grid4[i];
		}
		return temp;
	}
	
	/**
	 * Method that copies the grid sent in to prevent original board from being altered. 
	 * @param copy - grid to be copied 
	 */
	public List<char[]> copy(){
		List<char[]> temp = new ArrayList<char[]>();
		char[] temp1 = new char[9];
		for (int i = 0; i < 9; i++) {
			temp1[i] = grid1[i];
		}
		
		char[] temp2 = new char[9];
		for (int i = 0; i < 9; i++) {
			temp2[i] = grid2[i];
		}
		
		char[] temp3 = new char[9];
		for (int i = 0; i < 9; i++) {
			temp3[i] = grid3[i];
		}
		
		char[] temp4 = new char[9];
		for (int i = 0; i < 9; i++) {
			temp4[i] = grid4[i];
		}
		temp.add(temp1);
		temp.add(temp2);
		temp.add(temp3);
		temp.add(temp4);
		return temp;
	}
	
	/**
	 * Helper method for AI to make moves
	 * @param grid - grid (1,2,3,4) AI wants to place tile in 
	 * @param space - space (1,2,3,4,5,6,7,8,9) AI wants to place tile 
	 */
	 GameBoard placeTile(int grid, int space) {
		List<char[]> temp = this.copy();
		//temp.get(grid - 1)[space - 1] = 'b';
		if (turnCount % 2 == 0) {
			temp.get(grid - 1)[space - 1] = 'w';
		}
		else {
			temp.get(grid - 1)[space - 1] = 'b';
		}
		return new GameBoard(temp.get(0), temp.get(1), temp.get(2), temp.get(3));
	}

}
