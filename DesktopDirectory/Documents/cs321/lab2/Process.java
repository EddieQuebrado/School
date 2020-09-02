
public class Process implements Comparable<Process> {
	private int priorityLevel;
	private int timeRemaining;
	private int arrivalTime;
	private int timeNotProcessed;
	
	public Process(int currentTime, int maxProcessTime, int maxLevel) {
		this.priorityLevel = maxLevel;
		this.arrivalTime = currentTime;
		this.timeRemaining = maxProcessTime;
		this.timeNotProcessed = 0;
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
	
	public void reduceTimeRemaining() {
		this.timeRemaining--;
		resetTimeNotProcessed();
	}
	
	public boolean finish() {
		return (getTimeRemaining() == 0);
	}
	
	public void resetTimeNotProcessed() {
		this.timeNotProcessed = 0;
	}
	
	public void increaseTimeNotProcessed() {
		this.timeNotProcessed++;
	}
	
	public int getTimeNotProcessed() {
		return this.timeNotProcessed;
	}

	public int compareTo(Process process) {
		if(this.priorityLevel > process.priorityLevel) {
			return 1;
		} else if (this.priorityLevel < process.priorityLevel) {
			return -1;
		} else if (this.arrivalTime < process.arrivalTime){
			return 1;
		}
		return 0;
	}
}
