package sort;

/**
 * SortStats class that holds the counts/statistics for each sort method from AllSorts class.
 *  
 * @author Ella Gainey
 * @version August 29 2020
 */
public class SortStats {
	
	/** private field that holds the swap count for bubble sort*/
	private int bubbleCount;
	
	/** private field that holds the swap count for selection sort*/
	private int selectionCount;
	
	/** private field that holds the shift count for insertion sort*/
	private int insertionCount;
	
	/** private field that holds the shift count for shell sort*/
	private int shellCount;
	
	/** private field that holds the merge count for merge sort*/
	private int mergeCount;
	
	/** private field that holds the partition count for quick sort*/
	private int partitionCount;
	
	/**
	 * Constructor. Initializes counts to 0.
	 */
	public SortStats() {
		bubbleCount = 0;
		selectionCount = 0;
		insertionCount = 0;
		shellCount = 0;
		mergeCount = 0;
	}
	
	/**
	 * Sets swap count for bubble sort
	 * @param swapCount - number of swaps made in sort method
	 */
	public void setBubble(int swapCount) {
		this.bubbleCount = swapCount;
	}
	
	/** returns swap count for bubble sort*/
	public int getBubble() {
		return bubbleCount;
	}
	
	/**
	 * Sets swap count for selection sort
	 * @param swapCount - number of swaps made in sort method
	 */
	public void setSelection(int swapCount) {
		this.selectionCount = swapCount;
	}
	
	/** returns swap count for selection sort*/
	public int getSelection() {
		return selectionCount;
	}
	
	/**
	 * Sets shift count for insertion sort
	 * @param swapCount - number of shifts made in sort method
	 */
	public void setInsertion(int shiftCount) {
		this.insertionCount = shiftCount;
	}
	
	/** returns shift count for insertion sort*/
	public int getInsertion() {
		return insertionCount;
	}
	
	/**
	 * Sets shift count for shell sort
	 * @param swapCount - number of shifts made in sort method
	 */
	public void setShell(int shiftCount) {
		this.shellCount = shiftCount;
	}
	
	/** returns shift count for selection sort*/
	public int getShell() {
		return shellCount;
	}
	
	/**
	 * Sets merge count for merge sort
	 * @param merges - number of merges made in sort method
	 */
	public void setMerge(int merges) {
		this.mergeCount = merges;
	}
	
	/** returns shift count for merge sort*/
	public int getMerge() {
		return mergeCount;
	}
	
	/**
	 * Sets partition count for quick sort
	 * @param parts - number of partitions made in sort method
	 */
	public void setQuick(int parts) {
		this.partitionCount = parts;
	}
	
	/** returns partition count for quick sort*/
	public int getQuick() {
		return partitionCount;
	}
	
}
