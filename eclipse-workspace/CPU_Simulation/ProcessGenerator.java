import java.util.Random;

/**
 * Generates Process objects. Frequency of generated processes is determined
 * by the query with a given probability.
 * For Example) If probability is assigned the value 1.0 the query() will 
 * 				always be true.
 * @author Eddie Quebrado
 *
 */
public class ProcessGenerator {
	
	private Random rand;
	private double probability;
	
	/**
	 * ProcessGenerator constructor initializes probability
	 * and random generator.
	 * @param probability
	 */
	public ProcessGenerator(double probability) {
		this.probability = probability;		
		rand = new Random();
	}
	
	/**
	 * Generates a new Process, a Process includes current time created, a random time ranging
	 * from [1-maxProcessTime], and priority level ranging from [1-maxLevel].
	 * @param currentTime
	 * @param maxProcessTime
	 * @param maxLevel
	 * @return Process
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		return new Process(currentTime, rand.nextInt(maxProcessTime) + 1, rand.nextInt(maxLevel) + 1);
	}
	
	/**
	 * Randomly generates a double between 0.0 and 1.0 inclusive
	 * then the generated double is compared to the probability.
	 * @return True :If generated double is less than or equal to probability.
	 * @return False :Otherwise
	 */
	public boolean query() {
		return rand.nextDouble() <= probability;
	}
	
}
