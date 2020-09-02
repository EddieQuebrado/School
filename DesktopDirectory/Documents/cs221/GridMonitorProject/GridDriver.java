import java.io.FileNotFoundException;

/**
 * Creates a grid which monitors the potential risk of a solar array
 * exploding. 
 * @author Eddie Quebrado
 *
 */
public class GridDriver {
	public static void main(String[] args) {
		if(args.length < 1)
		{
			System.err.println("Usage: java GridDriver file1 [file2 ...]");
			System.exit(1);
		}
		
		for(String arg: args)
		{
			String filename = arg;
			try {
				GridMonitor gm = new GridMonitor(filename);
				System.out.println(gm.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
