import java.util.ArrayList;
import java.util.Collections;

public class MaxHeap {
	private int heapSize;
	ArrayList<Process> heap;
	private int ROOT = 1;
	
	/**
	 * Builds a MaxHeap storage implemented with an
	 * ArrayList .
	 */
	public MaxHeap() {
		heap = new ArrayList<Process>();
		heap.add(null);
	}
	
	/**
	 * Sorts the max heap to make sure processes are in order.
	 * @param array
	 */
	private void HeapSort(ArrayList<Process> array) {
		BuildMaxHeap();
		int size = heapSize;
		for(int i = size; i > ROOT; i--) {
			Collections.swap(heap, ROOT, i);
			heapSize--;
			maxHeapifyDown(ROOT);
		}
	}
	
	/**
	 * Builds a max heap.
	 */
	public void BuildMaxHeap() {
		heapSize = heap.size() - 1;
		for(int i = (heap.size()/2); i > 0; i--) {
			maxHeapifyDown(i);
		}
	}
	
	/**
	 * A recursive heapifiction downward to maintain the heap property
	 * of the tree.
	 * @param int startingIndex
	 */
	public void maxHeapifyDown(int startingIndex) {
		if(startingIndex <= heapSize) {
			int left = left(startingIndex);
			int right = right(startingIndex);
			int largest;
			if(left <= getHeapSize() && (heap.get(left).compareTo(heap.get(startingIndex)) == 1)) {
				largest = left;
			} else {
				largest = startingIndex;
			}
			if(right <= getHeapSize() && (heap.get(right).compareTo(heap.get(largest)) == 1)) {
				largest = right;
			}
			if(largest != startingIndex) {
				Collections.swap(heap, startingIndex, largest);
				maxHeapifyDown(largest);
			}
		}
	}

	public Process heapExtractMax() {
		if(heapSize < ROOT) {
			new HeapUnderflowException("Heap Underflow error");
		}
		Process max = heap.remove(ROOT);
		heapSize--;
		BuildMaxHeap();
		return max;
	}
	
	private void heapIncreaseKey(int index, Process key) {
		if(key.compareTo(heap.get(index)) == -1) {
			System.out.println("New value is smaller than the old value");
		}
		heap.set(index, key);
		while(index > ROOT && (heap.get((index / 2 ) + 1).compareTo(heap.get(index)) == -1)) {
			Collections.swap(heap, index, (index / 2 ) + 1);
			index = (index / 2 ) + 1;
		}
		maxHeapifyDown(ROOT);
	}
	
	private static int left(int i) {
		return (2*i);
	}
	
	private static int right(int i) {
		return ((2*i)+1);
	}

    public Process getFromHeap(int index){
        if(index < ROOT){
            new HeapUnderflowException("Index must < ROOT (1)");
        }
        return heap.get(index);
    }
    
    public void insertToHeap(Process key){
        heap.add(ROOT,key);
        heapSize = heap.size();
        BuildMaxHeap();
    }
    
    public Process retMax() {
    	return heap.get(ROOT);
    }
	
	public int getHeapSize() {
		return heapSize;
	}
	
	public boolean isEmpty() {
		return (heap.isEmpty());
	}
	
	public ArrayList<Process> getHeap(){
		return heap;
	}
	
	public void clearHeap() {
		heap.clear();
		heap.add(null);
	}
	
	public String toString() {
		return heap.toString();
	}
}
