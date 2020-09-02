import java.util.Random;

public class ProcessGenerator {
	
	private Random rand;
	private double probability;
	
	public ProcessGenerator(double probability) {
		this.probability = probability;		
		rand = new Random();
	}
	
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		return new Process(currentTime, rand.nextInt(maxProcessTime) + 1, rand.nextInt(maxLevel) + 1);
		
	}
	
	public boolean query() {
		return rand.nextDouble() <= probability;
	}
	
}
