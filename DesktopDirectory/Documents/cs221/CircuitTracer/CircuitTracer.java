import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail and Eddie Quebrado
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		System.out.println("java CircuitTracer <-s or -q> <-c or -g> <filename>");
		System.err.println("-s: storage type set to stack");
		System.err.println("-q: storage type set to queue");
		System.err.println("-c: set program to run in console");
		System.err.println("-g: set program to run in GUI(unsupported)");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		//parsing command line arguments
		String storage = args[0];
		String display = args[1];
		String file = args[2];
		boolean displayConsole = false;
		boolean displayGUI = false;
		try {
			board = new CircuitBoard(file);
			bestPaths = new ArrayList<>();
			if(storage.equals("-s")) {
				stateStore = Storage.getStackInstance();
			} else if (storage.equals("-q")) {
				stateStore = Storage.getQueueInstance();
			} else {
				System.out.println("Incorrect command line argument for storage");
				printUsage();
				System.exit(1);
			}
			if(display.equals("-c")) {
				displayConsole = true;
			}else if(display.equals("-g")) {
				displayGUI = true;
			}else {
				System.out.println("Incorrect command line argument for display option");
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found: " + file);
		}
		
		// search algorithm for finding the best path to the endingpoint
		ArrayList<Point> openPoint = board.adjacentPoints(board.getStartingPoint().x, board.getStartingPoint().y);
		for(Point p: openPoint) {
			stateStore.store(new TraceState(board, p.x, p.y));
		}
		while(!stateStore.isEmpty()) {
			TraceState state = stateStore.retrieve();
			if(state.isComplete()) {
				// finding the shortest path in bestPaths
				int shortestPath = Integer.MAX_VALUE;
				for (TraceState ts : bestPaths) {
					if (ts.pathLength() < shortestPath) {
						shortestPath = ts.pathLength();
					}
				}
				// comparing current trace state to shortest path
				if(state.pathLength() == shortestPath) {
					bestPaths.add(state);
				} else if(state.pathLength() < shortestPath) {
					bestPaths.clear();
					bestPaths.add(state);
				}
			} else {
				ArrayList<Point> nextPoint = state.getBoard().adjacentPoints(state.getRow(), state.getCol());
				for(Point p: nextPoint) {
					stateStore.store(new TraceState(state, p.x, p.y));
				}
			}
		}
		// choosing to either display graphs in console or GUI(unsupported)
		if(displayConsole) {
			for(TraceState c: bestPaths) {
				System.out.println(c);
			}
		} else if(displayGUI) {
			System.out.println("**************************\n\nError: GUI is unsupported\n\n**************************");
			System.exit(1);
		}
	}
}
