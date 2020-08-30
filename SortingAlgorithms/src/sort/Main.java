package sort;

/**
 * Main driver class that kick-starts the program. 
 *  
 * @author Ella Gainey
 * @version August 28 2020
 */
public class Main {

	/**
	 * driver method
	 * @param args - convention only 
	 */
	public static void main(String[] args) {
		
		SortStats stats = new SortStats();

		int[] unsortedArray = {20, 35, -15, 7, 55, 1, -22};

		AllSorts sort = new AllSorts(stats);


		sort.print("Array to be sorted: ", unsortedArray);

		//sort 
		int[] sortedArrayBubble = sort.bubbleSort(unsortedArray);
		int [] sortedArraySelection = sort.selectionSort(unsortedArray);
		int[] sortedArrayInsertion = sort.insertionSort(unsortedArray);
		int[] sortedArrayShell = sort.shellSort(unsortedArray);
		int[] sortedArrayMerge = sort.mergeSort(unsortedArray);
		int[] sortedArrayQuick = sort.quickSort(unsortedArray);
		
		sort.print("Array sorted: ", sortedArrayQuick);
		
		//print stats
		System.out.println("Bubble sort swaps made: " + stats.getBubble());
		System.out.println("Selection sort swaps made: " + stats.getSelection());
		System.out.println("Insertion sort shifts made: " + stats.getInsertion());
		System.out.println("Shell sort shifts made: " + stats.getShell());
		System.out.println("Merge sort merges made: " + stats.getMerge());
		System.out.println("Quick sort partitions made: " + stats.getQuick());
	}
}
