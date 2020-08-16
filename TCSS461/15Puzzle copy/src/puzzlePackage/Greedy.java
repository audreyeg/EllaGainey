package puzzlePackage;

import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

/**
 * Greedy class utilizes greedy best first search techniques to find the puzzle goal state based
 * on the initial state provided by the user. 
 *  
 * @author Ella Gainey
 * @version July 10 2020
 */

public class Greedy {

	/** private field for PrintWriter object that is sent in as param from Main */
	private PrintWriter pw;

	/** private field for gameBoard node objects*/
	private gameBoard node;

	/** private field that says whether the a node was explored */
	private int expandedNodes;

	/** private field that keeps track of how many nodes are added to the fringe total */
	private int numCreated;

	/** private field that keeps track of the size of the fringe at any given time*/
	private int size;

	/** private field that represents heuristic selected (h1 or h2) h1 is misplaced tiles h2 is manhattan distance*/
	private String heuristic;

	/** private field that is backbone of GBFS. All new nodes are added to the fringe.*/
	private PriorityQueue<Node> fringe;

	/** private field that holds set of visited nodes (prevents cycles)*/
	Set<String> visitedNodes;

	/**
	 * Constructor
	 *  @param board - instance of the game board 
	 *  @param options - user selected heuristic (h1 or h2) 
	 *  @param pw - PrintWriter object sent from Main so BFS can write results to txt file
	 *  
	 */

	public Greedy(gameBoard board, String options, PrintWriter pw) {
		this.pw = pw;
		expandedNodes = 0;
		this.node = board;
		this.heuristic = options;
		fringe = new PriorityQueue<Node>();
		numCreated = 1;
		size = 1;
		//adds first node to the fringe. Parent is null, false because this isn't AStar search. 
		//First node is initial puzzle state (entered by user) 
		fringe.add(new Node(null,node, false));
		visitedNodes = new HashSet<>();
		startSearch();
	}
	

	/**
	 * Method that first checks if goal state has been reached, then 	explores all children
	 * and adds them to the fringe. 
	 * 
	 */
	
	public void startSearch() {
		//start timer
		long startTime = System.currentTimeMillis();
		while (true) {
			//remove Node to explore from the fringe
			Node temp = fringe.remove();
			size--;
			//executes when goal state has been reached
			if (temp.getBoard().isGoal()) {
				temp.getBoard().printArray();
				//stop timer
				long endTime = System.currentTimeMillis();
				long duration = (endTime - startTime); 

				//output
				System.out.println("depth: " + temp.getLevel());
				System.out.println("expanded nodes: " + expandedNodes);
				System.out.println("num created: "+ numCreated);
				System.out.println("max fringe: " + size);
				System.out.println("time taken (in ms): " + duration +  " Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue");
				pw.write("goal state reached: " + temp.getBoard().toString());
				pw.write("\n" + temp.getLevel());
				pw.write("," + numCreated);
				pw.write("," + expandedNodes);
				pw.write("," + size);
				pw.write("\ntime taken (in ms): " + duration + " Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue");
				break;
			}
			//executes when goal state isn't reached. Checks first if board state has been previously explored
			if (!visitedNodes.contains(temp.getBoard().toString())) {
				visitedNodes.add(temp.getBoard().toString());
				expandedNodes++;

				//checks if move right is legal and adds board to fringe
				if (temp.getBoard().getRight()) {
					gameBoard tempBoard = new gameBoard(temp.getBoard().moveRight(), heuristic);
					fringe.add(new Node(temp, tempBoard, false));
					numCreated++;
					size++;
				}

				//checks if move down is legal and adds board to fringe
				if (temp.getBoard().getDown()) {
					gameBoard tempBoard = new gameBoard(temp.getBoard().moveDown(), heuristic);
					fringe.add(new Node(temp, tempBoard, false));	
					numCreated++;
					size++;
				}

				//checks if move left is legal and adds board to fringe
				if (temp.getBoard().getLeft()) {
					gameBoard tempBoard = new gameBoard(temp.getBoard().moveLeft(), heuristic);
					fringe.add(new Node(temp, tempBoard, false));
					numCreated++;
					size++;
				}

				//checks if move up is legal and adds board to fringe
				if (temp.getBoard().getUp()) {
					gameBoard tempBoard = new gameBoard(temp.getBoard().moveUp(), heuristic);
					fringe.add(new Node(temp, tempBoard, false));
					numCreated++;
					size++;
				}
			}
		}

	}

}
