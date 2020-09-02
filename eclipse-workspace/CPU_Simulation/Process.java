/**
 * A Process
 * @author Eddie
 *
 */
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
	
	/**
	 * Returns current Process priority level.
	 * @return int priorityLevel.
	 */
	public int getPriority() {
		return priorityLevel;
	}
	
	/**
	 * Returns remaining time for the process.
	 * @return int timeRemaining
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}
	
	/**
	 * Returns process arrival time.
	 * @return int arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Increments current Processes priority level.
	 */
	public void increasePriority() {
		this.priorityLevel++;
	}
	
	/**
	 * Increments a Processes time not processed.
	 */
	public void increaseTimeNotProcessed() {
		this.timeNotProcessed++;
	}
	
	/**
	 * Reduces Process remaining time and resets
	 * time not processed.
	 */
	public void reduceTimeRemaining() {
		this.timeRemaining--;
		resetTimeNotProcessed();
	}
	
	/**
	 * Resets the time not processed for a Process.
	 */
	public void resetTimeNotProcessed() {
		this.timeNotProcessed = 0;
	}
	
	/**
	 * Returns the time not processed for a Process.
	 * @return int timeNotProcessed
	 */
	public int getTimeNotProcessed() {
		return this.timeNotProcessed;
	}
	
	/**
	 * If Process's time remaining reaches zero then
	 * process is finished, returning true.
	 * @return boolean 
	 */
	public boolean finish() {
		return (getTimeRemaining() == 0);
	}
	
	/**
	 * Compares Processes priority level and returns int ranging from [-1,1] 
	 * @return 1 :If Process priority level is greater than the Process being compared.
	 * @return -1 :If Process priority level is less than the Process being compared.
	 * @return 1 :If both Process priority levels are equal then give priority to the Process with the earliest arrival time.
	 * @return 0 :If Process priority level is equal to the Process being compared.
	 */
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
