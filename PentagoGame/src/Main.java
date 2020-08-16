import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Main driver class that parses user input and controls game-play between human and AI.
 *  
 * @author Ella Gainey
 * @version August 11 2020
 */
public class Main {


	/**
	 * Main method that kick starts the program
	 *  @param args - convention only 
	 * To Run program. Hit "Run" You will be prompted for user response in the Console. Please enter only options given.
	 * You will be given choice to play as token 'w' or token 'b' as well as the ability to go first or second.
	 *  
	 */
	public static void main(String[] args) {
		//get player name
		System.out.println("Welcome to Pentago! What is your name?");
		Scanner scan = new Scanner(System.in);
		String playerName = scan.nextLine();
		System.out.println("Player Name: " + playerName);

		//player choice to play as w or b token 
		System.out.println("Play as w or b");
		String playerToken = scan.nextLine();
		System.out.println(playerName + " playing as " + playerToken + " token");

		//player choice to either make the first move or have the AI make first move
		System.out.println("Do you want to go first? Please answer yes or no.");
		String playerOrder = scan.nextLine();
		boolean first;
		//if human player wants w token then turn = 1. In GameBoard class this is used to determine what token to place
		//if turn % 2 == 0 then a b token is placed. 
		int turn = 0;
		if (playerToken.equals("w")) {
			turn = 1;
		} else {
			turn = 2;
		}
		//if player elects to go first 
		if (playerOrder.equals("yes")) {
			first = true;
		} else {
			first = false;
			//turn++;
		}

		PrintWriter pw = null;
		try {
			//file for output 
			File file = new File("./Output/output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			pw = new PrintWriter(file);
			pw.println("Human player: " + playerName + " playing as " + playerToken);
			if (first) {
				pw.println("going first");
			} else {
				pw.println("going second");
			}

			GameBoard board = new GameBoard();
			board.printBoard(pw);
			GameBoard temp = new GameBoard();
			winConditions winnerCheck = new winConditions();
			AI aiPlayer = new AI(turn);

			//only breaks when game has ended
			while (true) {
				//grid 1 - 4
				if(first){
					System.out.println("Enter block to put piece (1,2,3,4)");
					String block = scan.nextLine();

					//placement 1-9
					System.out.println("Enter space to put piece (1,2,3,4,5,6,7,8,9)");
					String space = scan.nextLine();

					temp = new GameBoard(board, block, space, turn);

					//keep human player in this loop until they make a move that is valid 
					//must be grid 1,2,3,4 and placement 1,2,3,4,5,6,7,8,9 and not be where another token is already 
					while(temp.getValidity() == false) {
						System.out.println("Enter block to put piece (1,2,3,4)");
						scan = new Scanner(System.in);
						block = scan.nextLine();

						System.out.println("Enter space to put piece (1,2,3,4,5,6,7,8,9)");
						space = scan.nextLine();

						temp = new GameBoard(board, block, space, turn);
					}
					//loop exits when valid move has been made by human player and tile has been placed 
					System.out.println("grid " + block + " space " + space);
					pw.println("grid " + block + " space " + space);
					board = temp;
					board.printBoard(pw);
					turn++;

					//check for winner after tile placement
					if (winnerCheck.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
						break;
					}

					//prompt human player to rotate one of the grids either left or right 
					System.out.println("Enter block to rotate(1,2,3,4)");
					String rotateBlock = scan.nextLine();

					System.out.println("Enter L for left rotation or R for right rotation");
					String rotateDirection = scan.nextLine();
					
					System.out.println("grid rotated " + rotateBlock + " direction " + rotateDirection);
					pw.println("grid rotated " + rotateBlock + " direction " + rotateDirection);

					System.out.println("utility " + winnerCheck.getUtility());
					board.rotateBoard(board, rotateBlock, rotateDirection.toUpperCase());
					board.printBoard(pw);

					//check for winner after grid rotation
					if (winnerCheck.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
						break;
					}
					first = false;
				}
				//ai player turn 
				aiPlayer.aiMove(board);
				board = aiPlayer.getPlacement();
				System.out.println("AI board after move");
				pw.println("AI board after move");
				board.printBoard(pw);
				

				//check for winner after AI places tile
				if (winnerCheck.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					break;
				}

				//ai player rotates grid
				//aiPlayer.getRotation();
				board = aiPlayer.getRotation(); 

				System.out.println("AI board after rotation");
				pw.println("AI board after rotation");
				board.printBoard(pw);

				//check for winner after grid rotation
				if (winnerCheck.isWin(board.getGrid1(), board.getGrid2(), board.getGrid3(), board.getGrid4()) == true) {
					break;
				}
				turn++;
				first = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
	}

}
