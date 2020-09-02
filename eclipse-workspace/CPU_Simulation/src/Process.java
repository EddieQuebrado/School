import java.util.Random;

public class Process implements Comparable<Process> {
	private int priorityLevel;
	private int timeRemaining;
	private int arrivalTime;
	@SuppressWarnings("unused")
	private int timeNotProcessed;
	private int requiredProcessTime;
	private Random rand = new Random();
	
	public Process(int currentTime, int maxProcessTime, int maxLevel) {
		this.priorityLevel = rand.nextInt(maxLevel) + 1;
		this.requiredProcessTime = rand.nextInt(maxProcessTime) + 1;
		this.timeNotProcessed = 0;
		this.timeRemaining = requiredProcessTime;
		this.arrivalTime = currentTime;
	}
	
	public int getPriority() {
		return priorityLevel;
	}
	
	public int getTimeRemaining() {
		return timeRemaining;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void increasePriority() {
		this.priorityLevel++;
	}

	public int compareTo(Process process) {
		int retVal = 0;
		if(this.priorityLevel > process.priorityLevel) {
			retVal = 1;
		} else if (this.priorityLevel < process.priorityLevel) {
			retVal = -1;
		} else if (this.priorityLevel == process.priorityLevel){
			retVal = 0;
		}
		return retVal;
	}
	
	public void reduceTimeRemaining() {
		this.timeRemaining--;
	}
	
	public boolean finish() {
		return (timeRemaining == 0);
	}
	
	public void resetTimeNotProcessed() {
		this.timeNotProcessed = 0;
	}
}
