import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Reads contents of file and formats file using the given contents
 * of the file.
 */
public class FormatChecker {
	
	/*
	 * Creating Instance Variables
	 */
	final private int rows, columns;
	private Scanner scan;
	private Object grid[][];
	
	/*
	 * Scans through file and formats all values into a 2d array.
	 * @param String filename
	 */
	public FormatChecker(String filename) throws FileNotFoundException {
		File file = new File(filename);
		scan = new Scanner(file);
		rows = scan.nextInt();
		columns = scan.nextInt();
		grid = new Object[rows][columns];
		int i = 0;
		int j;
		String line;
		String token;
		
		while(scan.hasNextLine()) {
			line = scan.nextLine();
			Scanner lineScan = new Scanner(line);
			j = 0;
			while(lineScan.hasNext()) {
				token = lineScan.next();
				grid[i-1][j] = token;
				j++;
			}
			i++;
			lineScan.close();
		}
	}
	
	/*
	 * Checks all elements within 2d array making sure all
	 * elements are either integers or doubles.
	 * 
	 * @param Object[][] array
	 */
	public void checkValidFormat(Object[][] array) {
		Object[][] vArray = getGrid();
		String str = "";
		for(int i = 0; i < vArray.length; i++) {
			for(int j = 0; j < vArray[i].length; j++) {
				str = (String)vArray[i][j];
				checkElement(str);
			}
		}
	}
	
	/**
	 * Takes in a value as a string, then checks whether string is a 
	 * double or integer.
	 * 
	 * @param String value
	 */
	@SuppressWarnings("unused")
	public void checkElement(String value) {
		String x = value.trim();
		double doubleVal;
		int intVal;
		if(x.contains(".")) {
			doubleVal = Double.parseDouble(x);
		}				
		else {
			intVal = Integer.parseInt(x);
		}
	} 
	
	/**
	 * Returns a 2d array populated by elements from file.
	 * 
	 * @return Original Grid
	 */
	public Object[][] getGrid() {
		Object[][] bg = new Object[rows][columns];
		for(int i = 0; i < bg.length; i++) {
			for(int j = 0; j < bg[i].length; j++)  
				bg[i][j] = grid[i][j];
			}
		return bg;
	}
}
