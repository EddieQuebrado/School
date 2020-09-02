import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 * Takes in file and reads the contents within the given file to ensure that
 * the file is correctly formatted.
 * @author Eddie Quebrado
 */
public class FormatCheckerDriver {
	public static void main(String[] args) {
		if(args.length < 1)
		{
			System.err.println("Usage: java GridDriver file1 [file2 ...]");
			System.exit(1);
		}
		
		for(String arg: args)
		{
			String filename = arg;
			System.out.println("File: " + arg);
			
			try {
				FormatChecker fm = new FormatChecker(filename);
				Object[][] dg = fm.getGrid();
				fm.checkValidFormat(dg);
				System.out.println("[VALID]\n");
			} 
			catch (FileNotFoundException | NumberFormatException | ArrayIndexOutOfBoundsException | InputMismatchException e) {
				System.out.println(e.toString());
				System.out.println("[INVALID]\n");
			}
			
			
			
		}
	}
}