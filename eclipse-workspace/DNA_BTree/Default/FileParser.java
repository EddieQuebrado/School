package Default;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to parse the .gbk files.
 */
public class FileParser {

	// Strings that resemble the start and the end of data
	private static final String ORIGIN = "ORIGIN";
	private static final String DOUBLESLASH = "//";

	// Delimiters
	private static final String DELIMITERS = new String("[\\d\\t\\s]+");

	// Booleans to keep track of whether the origin or the end is found in the file
	private static boolean foundOrigin;
	private static boolean foundEnd;

	// Length of the sequence
	private static int sequenceLength;

	public static ArrayList<String> getArrayListOfKeys(String fileName, int k) {
		// ArrayList to store all the key values
		ArrayList<String> listOfKeys = new ArrayList<>();

		// Instantiate a new file
		File file = new File(fileName);

		sequenceLength = k;

		// Initially, Origin and End is not found in the file
		foundOrigin = false;
		foundEnd = false;

		try {
			// Set up a file scanner
			Scanner fileScanner = new Scanner(file);

			// Set up a string to concatenate data
			String concatenatedString = "";

			// Run the while loop as long as the file has next line
			while (fileScanner.hasNextLine()) {
				// Store the line as a string
				String line = fileScanner.nextLine();

				// Set up a scanner to scan the line and use the delimiters
				@SuppressWarnings("resource")
				Scanner lineScanner = new Scanner(line).useDelimiter(DELIMITERS);

				// Checks if the line has the word ORIGIN
				if (lineScanner.hasNext(ORIGIN)) {
					foundOrigin = true; // Found the origin
					foundEnd = false; // Haven't found the end yet
					// advance the scanner to the next line
					lineScanner.nextLine();
				}

				// Checks if the line has two forward slash
				if (lineScanner.hasNext(DOUBLESLASH)) {
					foundEnd = true; // Found the end
					foundOrigin = false; // Set it back to false for future parsing

					// Sanity checking for any unwanted characters

					// Boolean to keep track of whether we're done with that concatenated String or
					// not
					boolean done = false;
					int i = 0; // Starting index for the temp string, inclusive
					int j = sequenceLength; // Ending index for the temp string, exclusive
					while (!done) {
						boolean validString = true; // Assume that the string is valid at first

						// Get a substring of the concatenatedString
						String temp = concatenatedString.substring(i, j).toLowerCase();

						// Check if the line contains the character n
						if (temp.contains("n")) {
							validString = false;
						}
						if (validString) {
							listOfKeys.add(temp);
						}

						// Check if done with the string
						if (i + sequenceLength == concatenatedString.length()) {
							done = true;
						}

						// Increment i and j by 1
						i++;
						j++;
					}
					// Empty concatenatedString for future concatenations
					concatenatedString = "";
				}

				// If the origin is found and the end is not found, go on adding stuff to the
				// concatenatedString
				if (foundOrigin && !foundEnd) {
					while (lineScanner.hasNext()) {
						concatenatedString += lineScanner.next();
					}
				}
				lineScanner.close();
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		}

		return listOfKeys;
	}
}
