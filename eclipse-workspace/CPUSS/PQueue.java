/**
 * Priority Queue:
 * @author Eddie
 *
 */
public class PQueue {
	
	private MaxHeap queue;
	
	/**
	 * Constructor for PQueue, 
	 * initializes a new Priority Queue using a MaxHeap
	 */
	public PQueue() {
		queue = new MaxHeap();
	}
	
	/**
	 * Sifts through Queue, updating Process priority levels if
	 * priority level can increment. Then builds new MaxHeap to
	 * reorganize the Processes.
	 * @param timeToIncrementLevel
	 * @param maxLevel
	 */
	public void update(int timeToIncrementLevel, int maxLevel) {
		for(int i = 1; i <= queue.getHeapSize(); i++) {
			Process p = queue.getFromHeap(i);
			p.increaseTimeNotProcessed();
			if(p.getTimeNotProcessed() >= timeToIncrementLevel) {
				p.resetTimeNotProcessed();
				if(p.getPriority() < maxLevel) {
					p.increasePriority();
				}
			}
		}
		queue.BuildMaxHeap();
	}
	
	/**
	 * Returns current size of the Queue.
	 * @return
	 */
	public int size() {
		return queue.getHeapSize();
	}
	
	/**
	 * Removes a Process from the front of the Queue.
	 * @return
	 */
	public Process dePQueue() {
		return queue.heapExtractMax();
	}
	
	/**
	 * Adds a new Process to the end of the Queue.
	 * @param p
	 */
	public void enPQueue(Process p) {
		queue.insertToHeap(p);
	}
	
	/**=
	 * Returns whether the Queue is empty or contains Processes.
	 * @return
	 */
	public boolean isEmpty() {
		return (queue.getHeapSize() < 1);
	}
	
	/**
	 * Clears the queue of all objects.
	 */
	public void clearQueue() {
		queue.clearHeap();
	}
	
	/**
	 * Outputs Priority Queue to String
	 */
	public String toString() {
		return queue.toString();
	}
}
