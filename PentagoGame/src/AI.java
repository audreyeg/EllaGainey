import java.util.ArrayList;
import java.util.List;

/**
 * AI class that implements min max tree and alpha beta pruning for AI player to make best moves against human player. 
 *  
 * @author Ella Gainey
 * @version August 11 2020
 */
public class AI {

	/** a value used for checking in alpha beta pruning**/
	private  static int CHECK = Integer.MAX_VALUE;
	/** private field that represents if a space on the board is empty or contains token */
	private boolean empty;

	/** private field that keeps track of turn count (to determine token placement) */
	private int turnCount;

	/** private field that sets the max depth we want to look in our tree*/
	private int maxDepth;

	/** private field that is a game board state for the ai*/
	private GameBoard boardMove;

	/** private field that is a list of possible moves for ai*/
	private List<GameBoard> gameBoards;

	/** private field that determines whether the ai is playing as a max or min player*/
	private int order;
	
	/** private field that keeps track of number of 'nodes' expanded*/
	private int nodeCount;


	/**
	 * Constructor 
	 *  @param player - sends in the turn count so AI can determine if w or b token 
	 */
	public AI(int player) {
		turnCount = player;

		//b token 
		if (turnCount % 2 == 0) {
			order = 3;
		}
		//w token 
		else {
			order = 1;
		}
		//max depth for searching in our tree 
		maxDepth = 5;
	}


	/**
	 * Method that is called when it is AI's turn to make a move
	 * @param board - current state of the board 
	 */
	public void aiMove(GameBoard board) {
		nodeCount = 0;
		gameBoards = new ArrayList<>();
		//miniMax(board, 0, order);
		miniMaxAB(board, 0, order,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	/**
	 * Method that utilizes min max algorithm 
	 * @param board - current state of the board 
	 * @param depth - current depth of tree (starts at 0) 
	 * @param bCheck - controls flow in the four if statements 
	 */
	private int miniMax(GameBoard board, int depth, int bCheck) {
		nodeCount++;
		winConditions getValue = new winConditions();
		getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4());
		//stop when we hit the max depth we hard coded 
		if (depth == maxDepth) {
			return getValue.getUtility();
		}

		//max player, looking for tile placements
		if (bCheck == 1) {
			//calls getBoards method which has all available AI tile placement options
			List<GameBoard> placements = getBoards(board);
			int currentBest = Integer.MIN_VALUE;
			for (int i = 0; i < placements.size(); i++) {
				//check if current board is winning board. if yes then return value of board
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				//recursive call
				else {	
					int check = miniMax(placements.get(i), depth + 1, 2);
					if (check > currentBest) {
						currentBest = check;
						if (depth == 0) {
							boardMove = placements.get(i);
						}
					}
				}
			}
			return currentBest;
		}

		//max player, looking for board rotation options
		if (bCheck == 2) {
			//calls getRotations method which determines grid rotations for all 4 grids (left and right turns)
			List<GameBoard> rotations = getRotations(board);
			int currentBest = Integer.MIN_VALUE;
			GameBoard temp = new GameBoard();
			for (int i = 0; i < rotations.size(); i++) {
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				else{
					//recursive call
					int check = miniMax(rotations.get(i), depth + 1, 3);
					if (check > currentBest) {
						currentBest = check;
						if (depth == 1) {
							temp = rotations.get(i);
						}
					}
				}
			}
			if (depth == 1) {
				gameBoards.add(temp);
				board.setIndex(gameBoards.size() - 1);
			}
			return currentBest;
		}

		//min player, looking for tile placements options
		if (bCheck == 3) {
			//calls getBoards method to find all possible tile placement options
			List<GameBoard> placements = getBoards(board);
			int currentBest = Integer.MAX_VALUE;
			for (int i = 0; i < placements.size(); i++) {
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				//recursive call
				else {
					int check = miniMax(placements.get(i), depth + 1, 4);
					if (check < currentBest) {
						currentBest = check;
						if (depth == 0) {
							boardMove = placements.get(i);
						}
					}
				}
			}
			return currentBest;
		}

		//max player, looking for board rotation options
		if (bCheck == 4) {
			//calls getRotations method which determines grid rotations for all 4 grids (left and right turns)
			List<GameBoard> rotations = getRotations(board);
			int currentBest = Integer.MAX_VALUE;
			GameBoard temp = new GameBoard();
			for (int i = 0; i < rotations.size(); i++) {
				//recursive call
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				else {
					int check = miniMax(rotations.get(i), depth + 1, 1);
					if (check < currentBest) {
						currentBest = check;
						if (depth == 1) {
							temp = rotations.get(i);
						}
					}
				}
			}
			if (depth == 1) {
				gameBoards.add(temp);
				board.setIndex(gameBoards.size() - 1);
			}
			return currentBest;
		}
		//should never reach this state 
		return 0;
	}

	/**
	 * Method that determines if a tile is empty or not
	 * @param grid - grid to check (1,2,3,4)
	 * @param index - tile placement to check (1,2,3,4,5,6,7,8,9)
	 */
	private boolean availablePositions(char[] grid, int index) {
		empty = false;
		if (grid[index - 1] == '*') {
			empty = true;
		}
		return empty;
	}

	/**
	 * Method that has copies of every game board state where AI can place a tile
	 * @param b- current state of the board 
	 */
	private List<GameBoard> getBoards(GameBoard b) {
		List<GameBoard> gbs = new ArrayList<>();
		//traverse through b -> check available spot -> place tile in spot + add to list 
		for (int j = 1; j < 10; j++) {
			//grid 1 
			if (availablePositions(b.getGrid1(),j)) {
				gbs.add(b.placeTile(1,j));
			}
			//grid 2
			if (availablePositions(b.getGrid2(),j)) {
				gbs.add(b.placeTile(2,j));
			}
			//grid 3
			if (availablePositions(b.getGrid3(),j)) {
				gbs.add(b.placeTile(3,j));
			}
			//grid 4
			if (availablePositions(b.getGrid4(),j)) {
				gbs.add(b.placeTile(4,j));
			}
		}
		return gbs;
	}

	/**
	 * Method that has copies of every game board state where AI can rotate a grid left or right 
	 * @param b - current state of the board 

	 */
	private List<GameBoard> getRotations(GameBoard b) {
		List<GameBoard> rotations = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			GameBoard temp = new GameBoard(b); 
			//all 4 grids rotate left
			temp.rotateBoard(temp, Integer.toString(i), "L");
			rotations.add(temp);
			temp = new GameBoard(b); 
			//all 4 grids rotate right 
			temp.rotateBoard(temp, Integer.toString(i), "R");
			rotations.add(temp);
		}
		return rotations;
	}

	/** returns state of board after tile placement*/
	public GameBoard getPlacement() {
		return boardMove;
	}

	/** returns index location in list of board rotations*/
	public GameBoard getRotation() {
		return gameBoards.get(boardMove.getIndex());
	}
	
	/**
	 * Method that utilizes min max algorithm and alpha beta pruning
	 * @param board - current state of the board 
	 * @param depth - current depth of tree (starts at 0) 
	 * @param bCheck - controls flow in the four if statements 
	 * @param newAlpha - for max player this is minimum num in Java 
	 * @param newBeta - for min player this is max num in Java
	 */
	private int miniMaxAB(GameBoard board, int depth, int bCheck,int newAlpha,int newBeta) {
		nodeCount++;
		int alpha = newAlpha;
		int beta = newBeta;
		winConditions getValue = new winConditions();
		getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4());
		//stop when we hit the max depth we hard coded 
		if (depth == maxDepth) {
			return getValue.getUtility();
		}

		//max player, looking for tile placements
		if (bCheck == 1) {
			//calls getBoards method which has all available AI tile placement options
			List<GameBoard> placements = getBoards(board);
			int currentBest = Integer.MIN_VALUE;
			for (int i = 0; i < placements.size(); i++) {
				int check = CHECK;
				//check if current board is winning board. if yes then return value of board
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				//recursive call
				else if(beta > alpha){	
					check = miniMaxAB(placements.get(i), depth + 1, 2, alpha, beta);
				}
				if(check > alpha && check != CHECK){
					alpha = check;
				}
					if (check > currentBest && check != CHECK) {
						currentBest = check;
						if (depth == 0) {
							boardMove = placements.get(i);
						}
					}
			}
			return currentBest;
		}

		//max player, looking for board rotation options
		if (bCheck == 2) {
			//calls getRotations method which determines grid rotations for all 4 grids (left and right turns)
			List<GameBoard> rotations = getRotations(board);
			int currentBest = Integer.MIN_VALUE;
			GameBoard temp = new GameBoard();
			for (int i = 0; i < rotations.size(); i++) {
				int check = CHECK;
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				else if(beta > alpha){
					//recursive call
					check = miniMaxAB(rotations.get(i), depth + 1, 3, alpha, beta);
				}
				if(check > alpha && check != CHECK) {
					alpha = check;
				}
				if (check > currentBest && check != CHECK) {
					currentBest = check;
					if (depth == 1) {
						temp = rotations.get(i);
					}
				}
			}
			if (depth == 1) {
				gameBoards.add(temp);
				board.setIndex(gameBoards.size() - 1);
			}
			return currentBest;
		}

		//min player, looking for tile placements
		if (bCheck == 3) {
			//calls getBoards method which has all available AI tile placement options
			List<GameBoard> placements = getBoards(board);
			int currentBest = Integer.MAX_VALUE;
			for (int i = 0; i < placements.size(); i++) {
				int check = CHECK;
				//check if current board is winning board. if yes then return value of board
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				//recursive call
				else if(beta > alpha){	
					check = miniMaxAB(placements.get(i), depth + 1, 4, alpha, beta);
				}
				if(check < beta && check != CHECK){
					beta = check;
				}
					if (check < currentBest && check != CHECK) {
						currentBest = check;
						if (depth == 0) {
							boardMove = placements.get(i);
						}
					}
			}
			return currentBest;
		}

		//min player, looking for board rotation options
		if (bCheck == 4) {
			//calls getRotations method which determines grid rotations for all 4 grids (left and right turns)
			List<GameBoard> rotations = getRotations(board);
			int currentBest = Integer.MAX_VALUE;
			GameBoard temp = new GameBoard();
			for (int i = 0; i < rotations.size(); i++) {
				int check = CHECK;
				if (getValue.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					return getValue.getUtility();
				}
				else if(beta > alpha){
					//recursive call
					check = miniMaxAB(rotations.get(i), depth + 1, 1, alpha, beta);
				}
				if(check < beta && check != CHECK) {
					beta = check;
				}
				if (check < currentBest && check != CHECK) {
					currentBest = check;
					if (depth == 1) {
						temp = rotations.get(i);
					}
				}
			}
			if (depth == 1) {
				gameBoards.add(temp);
				board.setIndex(gameBoards.size() - 1);
			}
			return currentBest;
		}
		//should never reach this.
		return 0;
	}

}
