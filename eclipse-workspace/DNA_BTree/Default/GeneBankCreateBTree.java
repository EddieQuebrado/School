package Default;
import java.io.IOException;
import java.util.ArrayList;

public class GeneBankCreateBTree{
	
	private static final int MAXSEQUENCELENGTH = 31;
	private static final int MAXDEBUGLEVEL = 1;
	
	public static void main(String[] args) throws IOException{
		// Verifying valid argument length
		if(args.length < 4 || args.length > 6) {
			printUsage();
		}
		// Cache
		boolean useCache = false;
		// Parsing argument to obtain whether cache will be used
		try {
			int c = Integer.parseInt(args[0]);
			//Verifying whether cache will be used
			if(c == 0) {
				// Cache will not be used
				useCache = false;
			} else if(c == 1) {
				// Cache will be used
				useCache = true;
			} else {
				// If c is anything other than 0 or 1 go here
				printUsage();
			}
		} catch (NumberFormatException e) {
			printUsage();
		}
		// BTree optimal degree
		int degree = 0;
		// Parsing argument to obtain degree for BTree
		try {
			int deg = Integer.parseInt(args[1]);
			// If degree is less than 0 print Usage
			if(deg < 0) {
				printUsage();
			} if (deg == 0) { 
				// If no degree specified optimal degree will be found for BTree
				degree = findOptimalDegree();
			} else {
				// Setting value for the degree
				degree = deg;
			}
		} catch (NumberFormatException e) {
			printUsage();
		}
		// BTree sequence length
		int sequenceLength = 0;
		// Parsing argument to obtain sequence length
		try {
			int seq = Integer.parseInt(args[3]);
			if(seq < 1 || seq > MAXSEQUENCELENGTH) {
				printUsage();
			} else {
				sequenceLength = seq;
			}
		} catch (NumberFormatException e) {
			printUsage();
		}
		// Cache size and Debug Level
		int cacheSize = 0;
		int debugLevel = 0;
		
		// Parsing arguments to obtain cache size and debug level if
		// either was specified by the user
		if(args.length > 4) {
			if(useCache) {
				try {
					int size = Integer.parseInt(args[4]);
					if(size < 1) {
						printUsage();
					} else {
						cacheSize = size;
					}
				} catch (NumberFormatException e) {
					printUsage();
				}
			}
			// If user did not use cache, or if argument length is greater than 5
			if(!useCache || args.length > 5) {
				try {
					// Using ternary operator
					int dLevel = Integer.parseInt(useCache ? args[5] : args[4]); // If useCache is true then debugLevel will be arg[5]
					if(dLevel < 0 || dLevel > MAXDEBUGLEVEL) {					 // else debugLevel will be arg[4]
						printUsage();
					} else {
						debugLevel = dLevel;
					}
				} catch (NumberFormatException e) {
					printUsage();
				}
			}
		}
		// Filename
		String fileName = args[2];
		// Calling the helper class FileParser to parse the gbk file
		ArrayList<String> listOfKeys = FileParser.getArrayListOfKeys(fileName, sequenceLength);
		for(String str: listOfKeys) {
			// Printing out dna sequence length to be stored in the BTree
			System.out.println(str);
			
		}
	}
	
	/**
	 * Prints program usage to console
	 */
	public static void printUsage() {
		System.err.println("Usage: java GeneBankCreateBTree <cache> <degree> <gbk file> <sequence length> [<cache size>] [<debug level>]");
		System.err.println("<cache> 0/1 (no cache/ cache");
		System.err.println("<degree>: Degree of BTree (defaults to 0)");
		System.err.println("<gbk file>: GeneBank file");
		System.err.println("<sequence length>: 1 - 31");
		System.err.println("<cache size>: (Optional) Size of Cache");
		System.err.println("<debug level>: 0/1 (no/yes");
		System.exit(1);
	}
	
	/**
	 * Calculates optimal degree if degree specified by the user is 0.
	 * @return Optimal degree
	 */
	public static int findOptimalDegree() {
		int sizeOfPointer = 4;
		int sizeOfObject = 30;
		int sizeOfMetaData = 100;
		double optimalDegree = 4096;
		optimalDegree += sizeOfObject;
		optimalDegree -= sizeOfPointer;
		optimalDegree -= sizeOfMetaData;
		optimalDegree /= (2 * (sizeOfObject + sizeOfPointer));
		return (int)Math.floor(optimalDegree);
	}
}