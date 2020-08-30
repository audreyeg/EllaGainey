package sort;

/**
 * AllSorts class holds all the sorting algorithms.  
 *  
 * @author Ella Gainey
 * @version August 29 2020
 */
public class AllSorts {
	
	/** private field for SortStats object that is sent in from Main */
	private SortStats stats;
	
	/**
	 * Constructor
	 *  @param ss - Class to update statistics on from sort algorithms 
	 */
	public AllSorts(SortStats ss) {
		this.stats = ss;
	}


	/**
	 * Helper method that prints arrays
	 * @param message - message to print before array (denotes whether array is sorted or not)
	 * @param array - array to be printed
	 */
	public void print(String message, int[] array) {
		System.out.println(message);
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
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array 
	 */
	public int[] bubbleSort(int[] arrayToSort) {
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
		stats.setBubble(swapCount);
		return array;
	}
	
	/**
	 * Selection sort. In place sorting algorithm. O(n^2). Unstable algorithm. 
	 * Requires less swapping than bubble sort. 
	 * Finds largest element, swap largest element with last element. 
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array 
	 */
	public int[] selectionSort(int[] arrayToSort) {
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
		stats.setSelection(swapCount);
		return array;
	}
	
	/**
	 * Insertion sort. In place sorting algorithm. O(n^2). Stable algorithm. 
	 * Insert each element in unsorted partition into sorted partition. Look for value 
	 * that is less than or equal than value you are inserting. 
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array 
	 */
	public int[] insertionSort(int[] arrayToSort) {
		int shiftCount = 0;
		int[] array = arrayToSort;
		
		for (int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
			int newElement = array[firstUnsortedIndex];
			//need to save this for later use outside loop
			int i;
			for (i = firstUnsortedIndex; i > 0 && array[i - 1] > newElement; i--) {
				//want to shift elements left to right 
				array[i] = array[i - 1];
				shiftCount++;
			}
			array[i] = newElement;
			shiftCount++;
		}
		stats.setInsertion(shiftCount);
		return array;
	}
	
	/**
	 * Shell sort. In place sorting algorithm. Unstable algorithm. 
	 * Worst case: O(n^2), can usually perform much better than that. 
	 * Variation of Insertion Sort using decreasing gaps until gap = 1.
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array 
	 */
	public int[] shellSort(int[] arrayToSort) {
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
		stats.setShell(shiftCount);
		return array;
	}
	
	/**
	 * Merge Sort. Called from Main class, uses other MergeSort method
	 * to handle the sorting. MergeSort implementation is recursive so overloaded method
	 * exists to simplify matters from mains perspective.  
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array
	 */
	public int[] mergeSort(int[] arrayToSort) {
		int[] array = arrayToSort;
		mergeSort(array, 0, array.length);
		return array;
		
	}
	
	/**
	 * Merge Sort (divide and conquer). Splitting phase: Array is split continuously until one element in each 
	 * array. Merging phase: merge elements together while sorting. NOT an in-place algorithm.
	 * O(n log n). Stable algorithm. Uses recursion.
	 * @param arrayToSort - array to be sorted 
	 * @param start - element at start of array
	 * @param end -   element at end of array
	 */
	public void mergeSort(int[] arrayToSort, int start, int end) {
		int mergeCount = 0;
		
		if (end - start < 2) {
			return;
		}
		int mid = (start + end) / 2;
		//recursive calls
		mergeSort(arrayToSort, start, mid);
		mergeSort(arrayToSort, mid, end);
		//merge
		merge(arrayToSort, start, mid, end);
		mergeCount++;
		stats.setMerge(mergeCount);
	}
	
	/**
	 * Merge helper method used in merge sort to merge and sort partitions together. 
	 * @param array - array to be sorted 
	 * @param start - element at start of array
	 * @param mid - element in between start and mid element of array 
	 * @param end - element at end of array
	 */
	public void merge(int[] array, int start, int mid, int end) {
		//if elements in left partition are less than right partition
		if (array[mid - 1] <= array[mid]) {
			return;
		}
		
		int i = start;
		int j = mid;
		int temp = 0;
		
		int[] tempArray = new int[end - start];
		//traverse through left and right array
		while (i < mid && j < end) {
			tempArray[temp++] = array[i] <= array[j] ? array[i++] : array[j++];
		}
		//only handles left overs from left array 
		System.arraycopy(array, i, array, start + temp, mid - i);
		//copy temp array into sorted array
		System.arraycopy(tempArray, 0, array, start, temp);
	}
	
	
	/**
	 * Quick Sort. Called from Main class, uses other quickSort method
	 * to handle the sorting. QuickSort implementation is recursive so overloaded method
	 * exists to simplify matters from mains perspective.  
	 * @param arrayToSort - array to be sorted 
	 * @return array - sorted array
	 */
	public int[] quickSort(int[] arrayToSort) {
		int[] array = arrayToSort;
		quickSort(array, 0, array.length);
		return array;
		
	}
	/**
	 * Quick Sort (divide and conquer). Splitting phase: Uses a pivot to partition array into two parts.
	 * Elements smaller than pivot are shifted left and elements larger than pivot are moved right of pivot
	 * O(n log n). Unstable algorithm. Uses recursion. In-place algorithm.
	 * @param arrayToSort - array to be sorted 
	 * @param start - element at start of array
	 * @param end -   element at end of array
	 */
	public void quickSort(int[] arrayToSort, int start, int end) {
		int partitionCount = 0;
		//1 element array 
		if (end - start < 2) {
			return;
		}
		//find pivot
		int pivot = partition(arrayToSort, start, end);
		partitionCount++;
		//left of pivot
		quickSort(arrayToSort, start, pivot);
		//right of pivot
		quickSort(arrayToSort, pivot + 1, end);
		stats.setQuick(partitionCount);
	}
	
	/**
	 * Partition helper method used in quick sort to merge and sort partitions together. 
	 * @param array - array to be sorted 
	 * @param start - element at start of array
	 * @param end - element at end of array
	 * @return pivot - index of pivot element
	 */
	public static int partition(int[] array, int start, int end) {
		int pivot = array[start];
		int i = start;
		int j = end;
		
		while (i < j) {
			//empty loop body 
			while (i < j && array[--j] >= pivot);
				if (i < j) {
					array[i] = array[j];
				}
				//empty loop body
				while (i < j && array[++i] <= pivot);
					if (i < j) {
						array[j] = array[i];
					}
		}
		array[j] = pivot;
		return j;
	}

}
