/**
 * Using this driver to test scenarios for the Analysis.odt.
 * @author Eddie Quebrado
 *
 */
public class MethodsDriver {
	public static void main(String[] args) {
		// ARRAY SIZE EQUALS ZERO SCENARIO
		int[] set1 = {}; // No elements in array
		
		// BEST CASE SCENARIO FOR FIND() AND REPLACEALL()
		int[] set2 = {1}; // Single element in the array
		
		// WORST CASE SCENARIO FOR FIND()
		int[] set3 = {1, 2}; // Target element in last index of array
		
		// EXPECTED AVERAGE CASE SCENARIO FOR FIND()
		int[] set4 = {1, 2, 3}; // Target element in the middle.
		
		// WORST CASE SCENARIO FOR REPLACEALL()
		int[] set5 = {1, 1, 1}; // All the same
		
		// SCENARIOS FOR SORTIT()
		int[] set6 = {1, 2, 3}; // Ascending order
		int[] set7 = {3, 2, 1}; // Reverse order
		int[] set8 = {2, 3, 1}; // Random order
		int[] set9 = {1, 2, 4, 3}; // Some sort of order
		int[] set10 = {1, 3, 2, 1, 3, 4}; // Duplicate elements
		
		// Test to find out how many statements are being executed in find().
		System.out.println("/////////////////////////////////\n"
				       + "///////// find() method /////////\n"
				       + "/////////////////////////////////\n");
		
		System.out.println("Arraysize equals zero: " + MethodsToAnalyze.find(set1, 0));
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nTarget element in first index: " + MethodsToAnalyze.find(set2, 1));
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nTarget element in last index: " + MethodsToAnalyze.find(set3, 2));
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nAverage case: " + MethodsToAnalyze.find(set4, 2));
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements() + "\n");
		
		// Test run to find out what the replaceAll() should output.
		System.out.println("/////////////////////////////////\n"
				         + "////// replaceAll() method //////\n"
					     + "/////////////////////////////////\n");
		
		System.out.println("Arraysize equals zero:");
		MethodsToAnalyze.replaceAll(set1, 0, 1);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nTarget element in first index:");
		MethodsToAnalyze.replaceAll(set2, 1, 0);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nDuplicate elements:");
		MethodsToAnalyze.replaceAll(set5, 1, 0);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements() + "\n");
		
		
		// Test run to find out what the sortIt() should output.
		System.out.println("/////////////////////////////////\n"
		                 + "//////// sortIt() method ////////\n"
			             + "/////////////////////////////////\n");
		
		System.out.println("Array size equals zero: ");
		MethodsToAnalyze.sortIt(set1);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nArray size equals one: ");
		MethodsToAnalyze.sortIt(set2);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nAscending Order: ");
		MethodsToAnalyze.sortIt(set6);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nReverse Order:");
		MethodsToAnalyze.sortIt(set7);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nAll the same: ");
		MethodsToAnalyze.sortIt(set5);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nRandom Order: ");
		MethodsToAnalyze.sortIt(set8);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nSome sort of order: ");
		MethodsToAnalyze.sortIt(set9);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements());
		System.out.println("\nDuplicates: ");
		MethodsToAnalyze.sortIt(set10);
		System.out.println("Total Statements: " + MethodsToAnalyze.getStatements() + "\n");
	}

}
