package puzzlePackage;

/**
 * Node class that is called for every new gameBoard object. 
 * Implements Comparable for use of priority queue when AStar or GBFS is used. 
 *  
 * @author Ella Gainey
 * @version July 10 2020
 */


public class Node implements Comparable<Node>{
	
	 /** private field that keeps track of the order nodes are added */
	private static int nextOrder = 0;
	
	 /** private field that keeps track of the level/depth nodes are at in tree*/
	private int level;
	
	 /** private field that holds the current game board state */
	private gameBoard board;
	
	/** private field that keeps track of the nodes parent */
	private Node parent;
	
	/** private field that is used in compareTo method */
	private int compare;
	
	/** private field that keeps track of order of added nodes */
	private int order;
	
	/** private field that says whether the search being used is AStar */
	private boolean isAStar;

	/**
	 * Constructor
	 *  @param node - parent node 
	 *  @param board - instance of the game board 
	 *  @param AStar - is false every time constructor is called unless AStar search is used
	 *  
	 */

	public Node(Node node, gameBoard board, boolean AStar) {
		this.isAStar = AStar;
		compare = 0;
		this.parent = node;
		this.board = board;

		//null when first Node is created
		if (parent == null) {
			level = 0;
		}
		else {
			level = parent.getLevel() + 1;
		}

		//keeping track of which nodes are added first for comparison when using GBFS or AStar
		this.order = nextOrder;
		Node.nextOrder++;
	}

	 /** returns depth of search tree*/
	public int getLevel() {
		return level;
	}

	/** returns parent of node*/
	public Node getParent() {
		return parent;
	}

	/** returns current board state*/
	public gameBoard getBoard() {
		return board;
	}

	/** returns order of node that was added*/
	public int getOrder() {
		return order;
	}

	/**
	 * Custom compareTo method
	 * @param heuristicPriority - node using in comparison 
	 */
	@Override
	public int compareTo(Node heuristicPriority) {
		//Heuristic for greedy search  (only takes heuristic into account, ignores level/depth)
		if (!isAStar) {
			compare = this.board.getHeuristic() - (heuristicPriority.board.getHeuristic());
			//if heuristics are equal then use the board that was added first
			if (compare == 0) {
				compare = this.getOrder() - heuristicPriority.getOrder();
			}
		}

		//Heuristic for AStar search (takes into account heuristic and level/depth)
		if (isAStar) {
			compare = (this.board.getHeuristic() + this.getLevel()) - 
					((heuristicPriority.board.getHeuristic() + heuristicPriority.getLevel()));
			//if heuristics are equal then use the board that was added first
			if (compare == 0) {
				compare = this.getOrder() - heuristicPriority.getOrder();
			}
		}
		return compare;
	}
}
