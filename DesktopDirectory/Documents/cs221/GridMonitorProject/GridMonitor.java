import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;	

public class GridMonitor implements GridMonitorInterface{
	
	private int width, height;
	private Scanner scanner;
	private double baseGrid[][];
	private double surroundingSumGrid[][];
	private double surroundingAvgGrid[][];
	private double deltaGrid[][];
	private boolean dangerGrid[][];
	
	/**
	 * Initializes instance variables then proceeds to open up file scanning
	 * every integer storing values such as width and height into their respective
	 * variables. Also creates a 2d grid stored with base values of the file.
	 * @param String filename
	 */
	public GridMonitor(String filename) throws FileNotFoundException{
		File file = new File(filename);
		scanner = new Scanner(file);
		width = scanner.nextInt();
		height = scanner.nextInt();
		baseGrid = new double[width][height];
		surroundingSumGrid = new double[width][height];
		surroundingAvgGrid = new double[width][height];
		deltaGrid = new double[width][height];
		dangerGrid = new boolean[width][height];
				
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				baseGrid[i][j] = scanner.nextDouble();
			}
		}
	}

	@Override
	/**
	 * Returns original values taken from the file.
	 * 
	 * @return base grid
	 */
	public double[][] getBaseGrid() {
		
		double[][] bg = new double[width][height];

		for(int i = 0; i < bg.length; i++) {
			for(int j = 0; j < bg[i].length; j++) {
				bg[i][j] = baseGrid[i][j];
			}
		}
		return bg;
	}

	@Override
	/**
	 * Returns a grid in which each element in the grid is a sum of its 4 surrounding
	 * elements. For elements which are on the border, the original base element's 
	 * value is used when looking outside of the grid border. Size of the grid is the
	 * same as the base grid. Returned grid has matching dimensions as the base grid.
	 * 
	 * @return grid containing sums of adjacent elements.
	 */
	public double[][] getSurroundingSumGrid() {
		double sum = 0.0;
		for(int i = 0; i < surroundingSumGrid.length; i++) {
			for(int j = 0; j < surroundingSumGrid[i].length; j++) {
				//Left Sum
				if(j - 1 < 0) {
					sum += baseGrid[i][j];
				}
				else {
					sum += baseGrid[i][j-1];
				}
				//Right Sum
				if(j + 1 > height - 1) {
					sum += baseGrid[i][j];
				}
				else {
					sum += baseGrid[i][j+1];
				}
				//Top Sum
				if(i - 1 < 0) {
					sum += baseGrid[i][j];
				}
				else {
					sum += baseGrid[i-1][j];
				}
				//Bottom Sum
				if(i + 1 > width - 1) {
					sum += baseGrid[i][j];
				}
				else {
					sum += baseGrid[i+1][j];
				}
				surroundingSumGrid[i][j] = sum;
				sum = 0.0;
			}
		}
		
		return surroundingSumGrid;
	}

	@Override
	/**
	 * Returns a grid in which each element is the average of the 4 surrounding 
	 * elements summed up. Returned grid has matching dimensions as the base grid.
	 * 
	 * @return grid containing average of adjacent elements.
	 */
	public double[][] getSurroundingAvgGrid() {
		double[][] sag = getSurroundingSumGrid();
		
		for(int i = 0; i < surroundingAvgGrid.length; i++) {
			for(int j = 0; j < surroundingAvgGrid[i].length; j++) {
				surroundingAvgGrid[i][j] = sag[i][j] / 4.0;
			}
		}
		return surroundingAvgGrid;
	}

	@Override
	/**
	 * Returns a grid in which each element is the maximum delta of the average.
	 * Returned grid has matching dimensions as the base grid.
	 * 
	 * @return grid containing maximum delta from average of surrounding elements.
	 */
	public double[][] getDeltaGrid() {
		double[][] dg = getSurroundingAvgGrid();
		
		for(int i = 0; i < deltaGrid.length; i++) {
			for(int j = 0; j < deltaGrid[i].length; j++) {
				deltaGrid[i][j] = Math.abs(dg[i][j] / 2.0);
			}
		}
		return deltaGrid;
	}

	@Override
	/**
	 * Returns a grid in which each element is a boolean value indicating whether
	 * a cell is at risk of exploding. For example, if the value of the current coordinate
	 * is less than the surrounding average minus the maximum delta or greater than the 
	 * surrounding average plus the maximum delta, the current coordinate will return true. 
	 * If the value of the cell is within this calculated range the current coordinate
	 * will return false.
	 * 
	 * @return grid containing boolean values indicating whether a cell is at risk
	 * of exploding.
	 */
	public boolean[][] getDangerGrid() {
		double[][] bg = getBaseGrid();
		double[][] dg = getDeltaGrid();
		double[][] sag = getSurroundingAvgGrid();
		
		for(int i = 0; i < dangerGrid.length; i++ ) {
			for(int j = 0; j < dangerGrid[i].length; j++) {
				if(bg[i][j] > sag[i][j] + dg[i][j] || bg[i][j] < sag[i][j] - dg[i][j]) {
					dangerGrid[i][j] = true;
				}
				else {
					dangerGrid[i][j] = false;
				}
			}
		}
		return dangerGrid;
	}
	
	public String toString() {
		String s = "";
		double[][] bg = getBaseGrid();
		double[][] ssg = getSurroundingSumGrid();
		double[][] sag = getSurroundingAvgGrid();
		double[][] dg = getDeltaGrid();
		boolean[][] dg2 = getDangerGrid();
		
		s += "====| Base Solar Cell Array |====\n";
		s += setGrid(bg);
		
		s += "\n====| Surrounding Solar Array Sums |====\n";
		s += setGrid(ssg);
		
		s += "\n====| Surrounding Solar Array Averages |====\n";
		s += setGrid(sag);
		
		s += "\n====| Solar Array Safe Range |====\n";
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				s += " [" + (sag[i][j] - dg[i][j]) + " - " + (sag[i][j] + dg[i][j]) + "] "; 
			}
			s += "\n";
		}
		s += "\n====| Solar Array Danger Grid |====\n";
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				s += " [" + dg2[i][j] + "] ";
			}
			s += "\n";
		}
		
		s += "\n====| Solar Array Condition |====\n";
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				if(dg2[i][j] == true) {
					s += "Cell " + "(" + i + ", " + j + ")" + " in danger of exploading!!";
				}
				else
				{
					s += "Cell " + "(" + i + ", " + j + ")" + " in a safe condition.";
				}
				
				s += "\n";
			}
		}
		
		return s;
	}
	
	private String setGrid(double[][] array) {
		
		String s = "";
		
		for(int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				s += " [" + array[i][j] + "] "; 
			}
			s += "\n";
		}
		return s;
	}
}
