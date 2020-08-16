/**
 * WinConditions class that checks to see if win state has been achieved and calculates utility value of each board.
 *  
 * @author Ella Gainey
 * @version August 11 2020
 */
public class winConditions {

	/** private field that represents grid1 (top left grid of board)*/
	private char[] g1;

	/** private field that represents grid1 (top right grid of board)*/
	private char[] g2;

	/** private field that represents grid1 (bottom left grid of board)*/
	private char[] g3;

	/** private field that represents grid1 (bottom right grid of board)*/
	private char[] g4;

	/** private field that represents all of the possible win states (there are 32 of them) on any board*/
	private char[][] wins;

	/** private field that determines if player token w wins*/
	private boolean wWinner;

	/** private field that determines if player token b wins*/
	private boolean bWinner;

	/** private field that represents the utility of each board */
	private int utility;

	/**
	 * Constructor
	 *  Initializes that there is no winner. 
	 */
	public winConditions() {
		wWinner = false;
		bWinner = false;
	}

	/**
	 * Method that returns if there was a winner.
	 * @param grid1 - top left grid 
	 * @param grid2 - top right grid
	 * @param grid3 - bottom left grid
	 * @param grid4 - bottom right grid
	 */
	boolean isWin(char[] grid1, char[] grid2, char[] grid3, char[] grid4) {
		//array for the 32 win states
		wins = new char[32][6];
		g1 = grid1;
		g2 = grid2;
		g3 = grid3;
		g4 = grid4;
		//method that fills in the win arrays
		wins();
		return (wWinner || bWinner);
	}

	/**
	 * Method that fills in the win arrays. There are 32 possible winning situations. 
	 * To win is to get five of the same token in a row. 
	 */
	private void wins(){ 
		char[] temp = {g1[0],g1[1],g1[2],g2[0],g2[1]};
		wins[0] = temp;
		char[] temp1 = {g1[3],g1[4],g1[5],g2[3],g2[4]};
		wins[1] = temp1;
		char[] temp2 = {g1[6],g1[7],g1[8],g2[6],g2[7]};
		wins[2] = temp2;
		char[] temp3 = {g3[0],g3[1],g3[2],g4[0],g4[1]};
		wins[3] = temp3;
		char[] temp4 = {g3[3],g3[4],g3[5],g4[3],g4[4]};
		wins[4] = temp4;
		char[] temp5 = {g3[6],g3[7],g3[8],g3[6],g3[7]};
		wins[5] = temp5;
		char[] temp6 = {g1[1],g1[2],g2[0],g2[1],g2[2]};
		wins[6] = temp6;
		char[] temp7 = {g1[4],g1[5],g2[3],g2[4],g2[5]};
		wins[7] = temp7;
		char[] temp8 = {g1[7],g1[8],g2[6],g2[7],g2[8]};
		wins[8] = temp8;
		char[] temp9 = {g3[1],g3[2],g3[0],g4[1],g4[2]};
		wins[9] = temp9;
		char[] temp10 = {g3[4],g3[5],g4[3],g4[4],g4[5]};
		wins[10] = temp10;
		char[] temp11 = {g3[7],g3[8],g4[6],g4[7],g4[8]};
		wins[11] = temp11;
		char[] temp12 = {g1[0],g1[3],g1[6],g3[0],g3[3]};
		wins[12] = temp12;
		char[] temp13 = {g1[1],g1[4],g1[7],g3[1],g3[4]};
		wins[13] = temp13;
		char[] temp14 = {g1[2],g1[5],g1[8],g3[2],g3[5]};
		wins[14] = temp14;
		char[] temp15 = {g1[3],g1[6],g3[0],g3[3],g3[6]};
		wins[15] = temp15;
		char[] temp16 = {g1[4],g1[7],g3[1],g3[4],g3[7]};
		wins[16] = temp16;
		char[] temp17 = {g1[5],g1[8],g3[2],g3[5],g3[8]};
		wins[17] = temp17;
		char[] temp18 = {g2[0],g2[3],g2[6],g4[0],g4[3]};
		wins[18] = temp18;
		char[] temp19 = {g2[1],g2[4],g2[7],g4[1],g4[4]};
		wins[19] = temp19;
		char[] temp20 = {g2[2],g2[5],g2[8],g4[2],g4[5]};
		wins[20] = temp20;
		char[] temp21 = {g2[3],g2[6],g4[0],g4[3],g4[6]};
		wins[21] = temp21;
		char[] temp22 = {g2[4],g2[7],g4[1],g4[4],g4[7]};
		wins[22] = temp22;
		char[] temp23 = {g2[5],g2[8],g4[2],g4[5],g4[8]};
		wins[23] = temp23;
		char[] temp24 = {g1[0],g1[4],g1[8],g4[0],g4[4]};
		wins[24] = temp24;
		char[] temp25 = {g1[4],g1[8],g4[0],g4[4],g4[8]};
		wins[25] = temp25;
		char[] temp26 = {g3[6],g3[4],g3[2],g2[6],g2[4]};
		wins[26] = temp26;
		char[] temp27 = {g3[4],g3[2],g2[6],g2[4],g2[2]};
		wins[27] = temp27;
		char[] temp28 = {g1[1],g1[5],g2[6],g4[1],g4[5]};
		wins[28] = temp28;
		char[] temp29 = {g3[3],g3[1],g1[8],g2[3],g2[1]};
		wins[29] = temp29;
		char[] temp30 = {g2[5],g2[7],g4[0],g3[5],g3[7]};
		wins[30] = temp30;
		char[] temp31 = {g1[3],g1[7],g3[2],g4[3],g4[7]};
		wins[31] = temp31;
		calculateValue();
	}

	/**
	 * Method that calculates the utility of the current game board state. The AI uses this information to make the
	 * best decisions on where to play a tile. 
	 */
	private void calculateValue() {
		utility = 0;
		//to go through all 32 possible win states
		for (int i = 0; i < 32; i++) {
			int temp = 0;
			int wWin = 0;
			int bWin = 0;
			if ((wins[i][0] == 'w' || wins[i][0] == '*') && (wins[i][1] == 'w' || wins[i][1] == '*') && (wins[i][2] == 'w' || wins[i][2] == '*')
					&& (wins[i][3] == 'w' || wins[i][3] == '*') && (wins[i][4] == 'w' || wins[i][4] == '*')) {
				for (int j = 0; j < 5; j++) {
					//how many w tokens in win arrays 
					if (wins[i][j] == 'w') {
						wWin++;
					}
				}
			} else if ((wins[i][0] == 'b' || wins[i][0] == '*') && (wins[i][1] == 'b' || wins[i][1] == '*') && (wins[i][2] == 'b' || wins[i][2] == '*')
					&& (wins[i][3] == 'b' || wins[i][3] == '*') && (wins[i][4] == 'b' || wins[i][4] == '*')) {
				for (int j = 0; j < 5; j++) {
					//how many b tokens in win arrays 
					if (wins[i][j] == 'b') {
						bWin++;
					}
				}
			}
			//if the board is better overall for w player 
			if (wWin > bWin) {
				temp = (int) Math.pow(2, wWin); // 2^n where n is from 0 to 5 

				//if the board is better overall for b player
			} else if (bWin > wWin) {
				temp = -((int) Math.pow(2, bWin)); // 2^n where n is from 0 to 5 
			}
			//if n was 5 (see above) then 2^n or 2^5 = 32 which means there was a winner 
			//w player wins
			if (temp == 32) {
				wWinner = true;
				utility += 10000;
				//System.out.println("Player 1 (player w) has won the game");
			}
			//b player wins
			if (temp == -32) {
				bWinner = true;
				utility -= 10000;
				//System.out.println("Player 2 (player b) has won the game");
			}
			utility += temp;
		}
	}

	/** returns utility value of board*/
	public int getUtility() {
		return utility;
	}
}
