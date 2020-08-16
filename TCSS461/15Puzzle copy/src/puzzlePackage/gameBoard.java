package puzzlePackage;

import java.util.Arrays;
import java.awt.Point;
import java.util.*;

/**
 * gameBoard class that provides legal moves for each board state (and logic of moves) and heuristics.
 *  
 * @author Ella Gainey
 * @version July 10 2020
 */


public class gameBoard {

	/** private field for the goal state trying to be reached*/
	private char[] goal1 = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','0'};

	/** private field for the goal state trying to be reached. Slightly different from the one above*/
	private char[] goal2 = {'1','2','3','4','5','6','7','8','9','A','B','C','D','F','E','0'};

	/** private field  of index location of the space (also represented by '0') */
	private int space;

	/** private field to indicate if black space can move right */
	private boolean right;

	/** private field to indicate if black space can move left */
	private boolean left;

	/** private field to indicate if black space can move up */
	private boolean up;

	/** private field to indicate if black space can move down */
	private boolean down;

	/** private field that represents the puzzle state of a game board */
	private char[] puzzleToSolve;

	/** private field that represents the heuristic sent in by the user (h1 or h2) */
	private String heuristic;

	/** private field that represents the calculated heuristic value */
	private int h;

	/** private field that is an array of points that is used for the Manhattan distance */
	private Point[] points;


	/**
	 * Constructor
	 *  @param array - character array that represents the puzzle state of a game board
	 *  
	 */

	public gameBoard(char[] array) {

		right = true;
		down = true;
		left = true;
		up = true;
		puzzleToSolve = array;
		//find the space in the array (also represented by '0') this is the piece that moves around 
		findSpace(puzzleToSolve);
		canMove();
	}

	/**
	 * Constructor used for GBFS and A* search only 
	 *  @param array - character array that represents the puzzle state of a game board
	 *  @param heuristic - h1: number of misplaced tiles, h2: Manhattan distance from current piece to goal state
	 *  
	 */

	public gameBoard(char[] array, String heuristic) {
		points = new Point[16];
		int index = 0;

		//points created to calculate Manhattan distance (heuristic 2)
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				points[index] = new Point(j,k);
				index++;
			}
		}

		right = true;
		down = true;
		left = true;
		up = true;
		this.heuristic = heuristic;
		puzzleToSolve = array;
		findSpace(puzzleToSolve);

		//calls correct heuristic calculation method based on user enterered input 
		if (this.heuristic.equals("h1")) {
			heuristicOne();
		}
		else if (this.heuristic.equals("h2")) {
			heuristicTwo();
		}
		canMove();

	}

	/**
	 * Method that prints the array when goal state is reached (that is the only time the method is called)
	 * Stops at index 15 because index 16 is a 0 which represents the blank space.
	 * 
	 */
	public void printArray() {
		for (int i = 0; i < 15; i++) {
			System.out.print(puzzleToSolve[i]);
		}
		System.out.println();
	}

	/**
	 * Method that finds the space from the user entered initial puzzle state. The space is replaced by a '0'
	 * for easier calculations.
	 * 
	 */
	public void findSpace(char[] puzzleToSolve) {
		for (int i = 0; i < puzzleToSolve.length; i++) {
			if (puzzleToSolve[i] == ('0')) {
				space = i;
			}
		}
	}

	/**
	 * Method that checks if a piece at any given index can move right, down, left, or up. 
	 * Visualized like a 2d array to find indexes used below.
	 * 
	 */
	public void canMove() {
		//when piece can't move right
		if (space == 3 || space == 7 || space == 11 || space == 15) {
			right = false;
		}
		//when piece can't move down
		if (space == 12 || space == 13 || space == 14 || space == 15) {
			down = false;
		}
		//when piece can't move left
		if (space == 0 || space == 4 || space == 8 || space == 12) {
			left = false;
		}
		//when piece can't move up
		if (space == 0 || space == 1 || space == 2 || space == 3) {
			up = false;
		}
	}

	/**
	 * Method that moves the blank space to the right. Blank space (represented by '0') is swapped with 
	 * character to the right.
	 * @return new array representing the state of the game board
	 */
	public char[] moveRight() {
		char[] copy = puzzleToSolve.clone();
		if (right) {
			int swap = space + 1;
			char temp = copy[space];
			copy[space] = copy[swap];
			copy[swap] = temp;
		}
		return copy;
	}

	/**
	 * Method that moves the blank space down. Blank space (represented by '0') is swapped with 
	 * character below.
	 * @return new array representing the state of the game board
	 */
	public char[] moveDown() {
		char[] copy = puzzleToSolve.clone();
		if (down) {
			int swap = space + 4;
			char temp = copy[space];
			copy[space] = copy[swap];
			copy[swap] = temp;
		}
		return copy;
	}

	/**
	 * Method that moves the blank space to the left. Blank space (represented by '0') is swapped with 
	 * character to the left.
	 * @return new array representing the state of the game board
	 */
	public char[] moveLeft() {
		char[] copy = puzzleToSolve.clone();
		if (left) {
			int swap = space - 1;
			char temp = copy[space];
			copy[space] = copy[swap];
			copy[swap] = temp;
		}
		return copy;
	}

	/**
	 * Method that moves the blank space up. Blank space (represented by '0') is swapped with 
	 * character above.
	 * @return new array representing the state of the game board
	 */
	public char[] moveUp() {
		char[] copy = puzzleToSolve.clone();
		if (up) {
			int swap = space - 4;
			char temp = copy[space];
			copy[space] = copy[swap];
			copy[swap] = temp;
		}
		return copy;
	}

	/**
	 * Method that checks if either of the two goal states have been reached.
	 * @return true if goal state has been reached
	 */
	public boolean isGoal() {
		int x = 0,y = 0;

		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal1[i]) {
				//incremented every time an index in our puzzle state doesn't match index in goal state
				x++;
			}
		}

		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal2[i]) {
				//incremented every time an index in our puzzle state doesn't match index in other goal state
				y++;
			}
		}
		return x == 0 || y == 0;
	}

	/** returns true if piece can move right and false if it can't*/
	public boolean getRight() {
		return right;
	}

	/** returns true if piece can move left and false if it can't*/
	public boolean getLeft() {
		return left;
	}

	/** returns true if piece can move down and false if it can't*/
	public boolean getDown() {
		return down;
	}

	/** returns true if piece can move up and false if it can't*/
	public boolean getUp() {
		return up;
	}

	/**
	 * Custom toString method 
	 */
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		//Stops at index 15 because index 16 is a 0 which represents the blank space.
		for(int i = 0; i < 15 ; i++)
		{
			returnString.append(puzzleToSolve[i]);
		}
		return returnString.toString();
	}

	/**
	 * Method that calculates heuristic one. This heuristic checks for the number of misplaced tiles.
	 * @return value of calculated heuristic 
	 */
	public int heuristicOne() {
		int x = 0, y = 0;

		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal1[i]) {
				//incremented every time an index in our puzzle state doesn't match index in goal state
				x++;
			}
		}
		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal2[i]) {
				//incremented every time an index in our puzzle state doesn't match index in other goal state
				y++;
			}
			//always picks smaller heuristic to reach goal state fastest 
			if (x > y) {
				h = y;
			}
			else {
				h = x;
			}
		}
		return h;
	}

	/**
	 * Method that calculates heuristic two. This heuristic calculates Manhattan distance from each piece 
	 * to its target/goal location.
	 * @return value of calculated heuristic 
	 */
	public int heuristicTwo() {
		int sum1 = 0, sum2 = 0;

		//for goal 1
		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal1[i]) {
				int index = 0;
				for (int j = 0; j < 16; j++) {
					if (puzzleToSolve[i] == goal1[j]) {
						index = j;
					}
				}
				//calculate distance
				sum1 += (Math.abs(points[i].getX() - points[index].getX()) +
						Math.abs(points[i].getY() - points[index].getY()));		
			}

		}

		//for goal 2
		for (int i = 0; i < 16; i++) {
			if (puzzleToSolve[i] != goal2[i]) {
				int index = 0;
				for (int j = 0; j < 16; j++) {
					if (puzzleToSolve[i] == goal2[j]) {
						index = j;
					}
				}
				//calculate distance
				sum2 += (Math.abs(points[i].getX() - points[index].getX()) +
						Math.abs(points[i].getY() - points[index].getY()));		
			}

		}

		//always picks smaller heuristic to reach goal state fastest 
		if (sum1 > sum2) {
			h = sum2;
		}
		else {
			h = sum1;
		}
		return h;
	}

	/** returns value of calculated heuristic*/
	public int getHeuristic() {
		return h;

	}
}

