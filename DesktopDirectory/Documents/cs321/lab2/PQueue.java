
public class PQueue {
	
	private MaxHeap queue;
	
	public PQueue() {
		queue = new MaxHeap();
	}
	
	public void update(int timeToIncrementLevel, int maxLevel) {
		for(int i = 1; i <= queue.getHeapSize(); i++) {
			Process p = queue.getFromHeap(i);
			p.increaseTimeNotProcessed();
			if(p.getTimeNotProcessed() >= timeToIncrementLevel) {
				p.resetTimeNotProcessed();
				if(p.getPriority() < maxLevel) {
					p.increasePriority();
				}
				p.resetTimeNotProcessed();
			}
		}
		queue.BuildMaxHeap();
	}
	
	public int size() {
		return queue.getHeapSize();
	}
	
	public Process dePQueue() {
		return queue.heapExtractMax();
	}
	
	public void enPQueue(Process p) {
		queue.insertToHeap(p);
	}
	
	public boolean isEmpty() {
		return (queue.getHeapSize() < 1);
	}
	
	public void clearQueue() {
		queue.clearHeap();
	}
	
	public String toString() {
		return queue.toString();
	}
}
