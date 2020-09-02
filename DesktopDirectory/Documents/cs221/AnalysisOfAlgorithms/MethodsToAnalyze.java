/**
 * A collection of methods that work with arrays of ints.
 * 
 * @author mvail
 */
public class MethodsToAnalyze {
	
	// NEW VARIABLE FOR COLLECTING DATA
	private static long //ctr;
	private static long tmp;
	
	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	public static int find(int[] array, int value) {
		// USING TO COLLECT DATA
		//ctr = 0; // Initialization of i, and for-loop conditional check.
		//ctr += 2;
		for (int i = 0; i < array.length; i++) {
			// USING TO COLLECT DATA
			//ctr++; // If-then conditional check.
			if (array[i] == value) {
				return i;
			}
			// USING TO COLLECT DATA
			//ctr += 2; // for loop incrementing, and for-loop conditional check.
		}
		return -1;
	}

	/**
	 * Replace all occurrences of oldValue with newValue in array.
	 * @param array ints where oldValue may be found
	 * @param oldValue value to replace
	 * @param newValue new value
	 */
	public static void replaceAll(int[] array, int oldValue, int newValue) {
		//ctr = 0; // Reseting counter
		int index = find(array, oldValue);
		// USING TO COLLECT DATA
		//ctr++; // While-loop conditional check.
		while (index > -1) {
			array[index] = newValue;
			// USING TO COLLECT DATA
			//ctr++; // The assigning of oldValue in array to newValue.
			tmp = //ctr;
			index = find(array, oldValue);
			//ctr += tmp; // find() total statements.
			//ctr++; // While-loop conditional check.
		}
	}
	
	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void sortIt(int[] array) {
		// USING TO TAKE DATA
		//ctr = 0; // Reset counter
		//ctr += 2; // The initialization of i and the for-loop conditional check.
		for (int next = 1; next < array.length; next++) {
			int value = array[next];
			//ctr++; // Assigning value to next index.
			int index = next;
			//ctr++; // Assigning index to next index.
			//ctr++; // While-loop conditional check.
			while (index > 0 && value < array[index - 1]) {
				array[index] = array[index - 1];
				index--;
				//ctr += 3; // Assigning array[index] to array[index-1],
						  // index decrementing, and while-loop conditional check.
			}
			//ctr++; // Assigning element at index to next index value.
			array[index] = value;
			//ctr += 2; // variable next incrementing up and for-loop conditional check.
		}
	}
	/**
	 * Returns amount of executed statements.
	 * @return Executed Statements
	 */
	public static long getStatements() {
		return //ctr;
	}
}