import java.util.ArrayList;

public class PQueue {
	
	private ArrayList<Process> pq;
	
	public PQueue() {
		pq = new ArrayList<Process>();
	}
	
	public boolean isEmpty() {
		return (pq.isEmpty());
	}
	
	public void update(int timeToIncrementLevel, int maxLevel) {
		for(int i = 0; i < size(); i++) {
			if(pq.get(i).getPriority() != maxLevel) {
				pq.get(i).increasePriority();
			}
		}
	}
	
	public int size() {
		return pq.size();
	}
	
	public Process dePQueue() {
		Process p = pq.remove(0);
		return p;
	}
	
	public void enPQueue(Process p) {
		pq.add(p);
	}
}
