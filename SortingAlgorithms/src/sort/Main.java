package sort;

/**
 * Main driver class that holds all sorting algorithms 
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
		
		int[] unsortedArray = {20, 35, -15, 7, 55, 1, -22};
		
		
		System.out.println("Array to be sorted: ");
		print(unsortedArray);
		
		
		bubbleSort(unsortedArray);
		selectionSort(unsortedArray);
		insertionSort(unsortedArray);
		shellSort(unsortedArray);

	}
	
	/**
	 * Helper method that prints arrays
	 * @param array - array to be printed
	 */
	public static void print(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			
			if (i < array.length - 1) {
				System.out.print(array[i] + ", ");
			}
			else if (i < array.length) {
				System.out.print(array[i]);
			}
		}
		System.out.print("}");
		System.out.println("\n");
	}
	
	/**
	 * Swap helper method used in various sorting algorithms to swap two elements in array.
	 * @param array - array to be sorted 
	 * @param i - element to swap with element at j
	 * @param j - element to swap with element at i
	 */
	public static void swap(int[] array, int i, int j) {
		//swap element with itself so nothing to do 
		if (i == j) {
			return;
		}
		//make the swap
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * Bubble sort. In place sorting algorithm. O(n^2)
	 * Larger elements 'bubble' up.
	 * @param array - array to be sorted 
	 */
	public static void bubbleSort(int[] arrayToSort) {
		//keep track of amount of swaps bubble sort makes 
		int swapCount = 0;
		int[] array = arrayToSort;
		for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			//only go through unsorted partition
			for (int i = 0; i < lastUnsortedIndex; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
					swapCount++;
				}
			}
		}
		System.out.println("Array sorted: ");
		print(array);
		System.out.println("Bubble sort swaps made: " + swapCount);
		System.out.println();
	}
	
	/**
	 * Selection sort. In place sorting algorithm. O(n^2). Unstable algorithm. 
	 * Requires less swapping than bubble sort. 
	 * Finds largest element, swap largest element with last element. 
	 * @param array - array to be sorted 
	 */
	public static void selectionSort(int[] arrayToSort) {
		//keep track of amount of swaps selection sort makes 
		int swapCount = 0;
		int[] array = arrayToSort;
		for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			int largest = 0;
			//compare elements against what element is largest
			for (int i = 1; i <= lastUnsortedIndex; i++) {
				if (array[i] > array[largest]) {
					largest = i;
				}
			}
			//make the swap
			swap(array, largest, lastUnsortedIndex);
			swapCount++;
		}
		//System.out.println("Array sorted: ");
		//print(array);
		System.out.println("Selection sort swaps made: " + swapCount);
	}
	
	/**
	 * Insertion sort. In place sorting algorithm. O(n^2). Stable algorithm. 
	 * Insert each element in unsorted partition into sorted partition. Look for value 
	 * that is less than or equal than value you are inserting. 
	 * @param array - array to be sorted 
	 */
	public static void insertionSort(int[] arrayToSort) {
		int shiftCount = 0;
		int[] array = arrayToSort;
		for (int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
			int newElement = array[firstUnsortedIndex];
			//need to save this for later use outside loop
			int i;
			for (i = firstUnsortedIndex; i > 0 && array[i - 1] > newElement; i--) {
				//want to shift elements left to right 
				array[i] = array[i - 1];
			}
			array[i] = newElement;
			shiftCount++;
		}
		System.out.print("\n");
		System.out.println("Insertion sort shifts made: " + shiftCount);
	}
	
	/**
	 * Shell sort. In place sorting algorithm. Unstable algorithm. 
	 * Worst case: O(n^2), can usually perform much better than that. 
	 * Variation of Insertion Sort using decreasing gaps until gap = 1.
	 * @param array - array to be sorted 
	 */
	public static void shellSort(int[] arrayToSort) {
		//keep track of amount of shifts shell sort makes 
		int shiftCount = 0;
		int[] array = arrayToSort;
		
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				int newElement = array[i];
				int j = i;
				//compare items across gap 
				while (j >= gap && array[j - gap] > newElement) {
					array[j] = array[j - gap];
					j -= gap;
				}
				array[j] = newElement;
				shiftCount++;
			}
		}
		System.out.print("\n");
		System.out.println("Shell sort shifts made: " + shiftCount);
	}

}
