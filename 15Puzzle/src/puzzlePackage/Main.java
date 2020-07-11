package puzzlePackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;


/**
 * Main driver class that parses user input and calls on the right searches.
 *  
 * @author Ella Gainey
 * @version July 10 2020
 */

public class Main {

	/**
	 * Main method that kick starts the program
	 *  @param args - based on user entered command line arguments (simulated in Eclipse environment using
	 *  Run -> Run Configurations and typing arguments in the 'arguments' tab 
	 *  Input should be entered with a 16 digit (space included) 1-9 A-F puzzle start state surrounded by quotations
	 *  Followed by a space, followed by a search technique to use (BFS, DFS, AStar, GBFS, DLS).
	 *  If DLS then third argument after a space should be a valid depth (non negative integer)
	 *  If GBFS or AStar then h1 or h2 should be passed after a space. 
	 *  Example of valid argument input: "123456789ABC DFE" AStar h1
	 *  
	 */

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			//file for output 
			File file = new File("./output/output.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			pw = new PrintWriter(file);

			//if no arguments were entered from command line
			if (args.length <= 0) {
				System.out.println("Bad input. Good input example: \"123456789ABC DFE\" BFS ");
				return;
			}

			//initial puzzle (16 characters including a space) 
			String userPuzzle = args[0];
			//search method specified (BFS, DFS, DLS, GBFS, AStar)
			String userSearch = args[1];

			//checks is the initial start state matches correct format
			if (userPuzzle.length() != 16) {
				System.out.println("bad input. Please use quotations around entire puzzle start state and make sure your puzzle is 16 characters long (including a space");
				pw.println("bad input. Please use quotations around entire puzzle start state and make sure your puzzle is 16 characters long (including a space");
			}

			//if puzzle start state user gave was good then continue
			else {

				//options indicate depth selected for DLS or heuristic selected for GBFS and AStar
				String userOptions = "";

				//only runs if there is a third argument
				try {
					userOptions = args[2];

				}
				catch(Exception e) {
					//  Block of code to handle errors
					//e.printStackTrace();
				}

				//collecting all user arguments into one string for parsing
				String input = userPuzzle + " " + userSearch + " " + userOptions;
				checkInput(input, pw);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();

	}

	/**
	 * Method that deals with user input after the initial checks have been done. 
	 * This method will call the correct classes based on the user-specified search technique.
	 * @param theInput - one string that represents all user-entered arguments in command line 
	 *  @param pw - PrintWriter object sent from Main so BFS can write results to txt file
	 */

	public static boolean checkInput(String theInput, PrintWriter pw) {
		String parseInput = theInput;

		//split arguments using spaces as indicator
		String[] splitStrings = parseInput.split(" ");

		//space is included to represent blank space in puzzle. Replace the space with '0'
		String puzzleStartState = splitStrings[0] + "0" + splitStrings[1];
		
		//location of the blank space
		int zeroLocation = splitStrings[0].length();
		
		//check if the initial state given is solvable 
		int count = 0;
		//finds number of inversions 
		for (int i = 0; i < 16; i++) {
			for (int j = i + 1; j < 16; j++) {
				if (puzzleStartState.charAt(i) == '0' || puzzleStartState.charAt(j) == '0') {
					//do nothing
				}
				else if ((int)puzzleStartState.charAt(i) > (int) puzzleStartState.charAt(j)) {
					count++;
				}
			}
		}
		//initial state given is solvable 
		if ((zeroLocation >= 0 && zeroLocation <= 3) || (zeroLocation >= 8 && zeroLocation <= 11)) {
			if (count % 2 > 0) {
				//do nothing
			}
			//initial state given is unsolvable 
			else {
				System.out.println("Puzzle is unsolveable");
				 System.exit(0); 
			}
		}
		//initial state given is solvable 
		if ((zeroLocation >= 4 && zeroLocation <= 7) || (zeroLocation >= 12 && zeroLocation <= 15)) {
			if (count % 2 > 0) {
				//do nothing
			}
			//initial state given is unsolvable 
			else {
				System.out.println("Puzzle is unsolveable");
				System.exit(0); 
			}
		}

		//this only exists to print the start state for the user the same way they entered.
		//Otherwise the puzzle would be printed with a '0' which is unfamiliar to the user.
		String initialState = splitStrings[0] + " " + splitStrings[1];

		//search method argument (BFS, DFS, DLS, AStar, GBFS)
		String searchMethod = splitStrings[2];

		//output
		pw.println("search: " + searchMethod);
		pw.println("puzzle start state: " + initialState);

		//third argument is default null
		String options = "";

		//if third argument exists use it (h1, h2, or specified depth for DLS)
		try {
			options = splitStrings[3];

		}
		catch(Exception e) {
			//e.printStackTrace();
		}

		//BFS
		if (searchMethod.equals("BFS") || searchMethod.equals("bfs")) {
			if (puzzleStartState.length() != 16) {
				System.out.println("Incorrect puzzle entered");
			}
			else {

				//String to character array for puzzle state 
				char[] bfsArray = new char[puzzleStartState.length()];
				for (int i = 0; i < puzzleStartState.length(); i++) {
					bfsArray[i] = puzzleStartState.charAt(i);
				}

				//create first instance of gameBaord
				gameBoard puzzle = new gameBoard(bfsArray);

				//start the search 
				new BFS(puzzle, pw);

				//output
				System.out.println("Starting puzzle state: " + initialState);
				System.out.println("Search method selected: " + searchMethod);
			}
		}

		//DFS
		else if (searchMethod.equals("DFS") || searchMethod.equals("dfs")) {
			if (puzzleStartState.length() != 16) {
				System.out.println("Incorrect puzzle entered");
			}
			else {
				//String to character array for puzzle state 
				char[] dfsArray = new char[puzzleStartState.length()];
				for (int i = 0; i < puzzleStartState.length(); i++) {
					dfsArray[i] = puzzleStartState.charAt(i);
				}
				//create first instance of gameBaord
				gameBoard puzzle = new gameBoard(dfsArray);
				//start the search. 0 indicates this is DFS and not DLS
				new DFS(puzzle, 0, pw);

				//output
				System.out.println("Starting puzzle state: " + initialState);
				System.out.println("Search method selected: " + searchMethod);
			}
		}

		//DLS
		else if (searchMethod.equals("DLS") || searchMethod.equals("dls")) {
			if (puzzleStartState.length() != 16) {
				System.out.println("Incorrect puzzle entered");
			}
			else {
				//String to character array for puzzle state 
				char[] dlsArray = new char[puzzleStartState.length()];
				for (int i = 0; i < puzzleStartState.length(); i++) {
					dlsArray[i] = puzzleStartState.charAt(i);
				}
				try {
					//check to see if the depth given is an integer greater than or equal to 0
					if (Integer.valueOf(options) >= 0) {
						//create first instance of gameBaord
						gameBoard puzzle = new gameBoard(dlsArray);
						//start the search 
						new DFS(puzzle,Integer.valueOf(options), pw);

						//output
						System.out.println("Starting puzzle state: " + puzzleStartState);
						System.out.println("Search method selected: " + searchMethod);
						System.out.println("Depth selected: " + options);
						pw.println("\ndepth selected: " + options);
					}

				}
				//if invalid depth was given
				catch(NumberFormatException e) {
					System.out.println("Invalid depth entered. Please only type a non-negative integer. You entered: " + options);
					pw.println("Invalid depth entered. Please only type a non-negative integer. You entered: " + options);
				}
			}
		}

		//GBFS
		else if (searchMethod.equals("GBFS") || searchMethod.equals("gbfs")) {
			if (puzzleStartState.length() != 16) {
				System.out.println("Incorrect puzzle entered");
			}
			else {
				//String to character array for puzzle state 
				char[] gbfsArray = new char[puzzleStartState.length()];
				for (int i = 0; i < puzzleStartState.length(); i++) {
					gbfsArray[i] = puzzleStartState.charAt(i);
				}
				//checks is user wants heuristic one or heuristic two
				if (options.equals("h1") || options.equals("h2")) {
					//create first instance of gameBaord using second constructor that takes in heuristic used
					gameBoard puzzle = new gameBoard(gbfsArray, options);
					//start the search 
					new Greedy(puzzle, options, pw);

					//output
					System.out.println("Starting puzzle state: " + initialState);
					System.out.println("Search method selected: " + searchMethod);
					System.out.println("Heuristic Selected: " + options);
					pw.println("\nHeuristic selected: " + options);
				}
				
				//if heuristic argument entered isn't h1 or h2
				else {
					System.out.println("Wrong heuristic entered. Please only type h1 or h2. You entered: " + options);
					pw.println("Wrong heuristic entered. Please only type h1 or h2. You entered: " + options);
				}
			}

		}

		//AStar 
		else if (searchMethod.equals("AStar") || searchMethod.equals("A*") || searchMethod.equals("Astar")) {
			if (puzzleStartState.length() != 16) {
				System.out.println("Incorrect puzzle entered");
			}
			else {
				//String to character array for puzzle state 
				char[] astarArray = new char[puzzleStartState.length()];
				for (int i = 0; i < puzzleStartState.length(); i++) {
					astarArray[i] = puzzleStartState.charAt(i);
				}
				//checks is user wants heuristic one or heuristic two
				if (options.equals("h1") || options.equals("h2")) {
					//create first instance of gameBaord using second constructor that takes in heuristic used
					gameBoard puzzle = new gameBoard(astarArray, options);
					//start the search 
					new AStar(puzzle, options, pw);

					//output
					System.out.println("Starting puzzle state: " + initialState);
					System.out.println("Search method selected: " + searchMethod);
					System.out.println("Heuristic Selected: " + options);
					pw.println("\nHeuristic selected: " + options);
				}
				//if heuristic argument entered isn't h1 or h2
				else {
					System.out.println("Wrong heuristic entered. Please only type h1 or h2. You entered: " + options);
					pw.println("Wrong heuristic entered. Please only type h1 or h2. You entered: " + options);
				}
			}
		}

		//if argument given does not match any of the above searches 
		else {
			System.out.println("Search not recognized. Supoprted searches are BFS, DFS, DLS, GBFS, AStar. You entered: " + searchMethod);
			pw.println("Search not recognized. Supoprted searches are BFS, DFS, DLS, GBFS, AStar. You entered: " + searchMethod);
		}


		return false;
	}
}
