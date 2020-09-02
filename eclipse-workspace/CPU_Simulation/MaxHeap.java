import java.util.ArrayList;
import java.util.Collections;
/**
 * MaxHeap:
 * @author Eddie
 *
 */
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
	
	/**
	 * Sorts the max heap to make sure processes are in order.
	 * @param array
	 */
	@SuppressWarnings("unused")
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
	 * 
	 * @param index
	 * @param key
	 */
	@SuppressWarnings("unused")
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
	
	/**
	 * Extracts the max process (root) of the Heap.
	 * @return Process max (root)
	 */
	public Process heapExtractMax() {
		if(heapSize < ROOT) {
			new HeapUnderflowException("Heap Underflow error");
		}
		Process max = heap.remove(ROOT);
		heapSize--;
		BuildMaxHeap();
		return max;
	}
	
	/**
	 * Returns Process from Heap.
	 * @param index
	 * @return Process
	 */
    public Process getFromHeap(int index){
        if(index < ROOT){
            new HeapUnderflowException("Index must < ROOT (1)");
        }
        return heap.get(index);
    }
    
    /**
     * Inserts a process into the Heap, after new process is added
     * the Heap is then reorganized.
     * @param key
     */
    public void insertToHeap(Process key){
        heap.add(ROOT,key);
        heapSize = heap.size();
        BuildMaxHeap();
    }
    
    /**
     * Returns the max process from the Heap.
     * @return Max (root)
     */
    public Process getMax() {
    	return heap.get(ROOT);
    }
	
    /**
     * Returns current size of the Heap.
     * @return Heap Size
     */
	public int getHeapSize() {
		return heapSize;
	}
	
	/**
	 * Returns true if Heap contains no elements.
	 * @return
	 */
	public boolean isEmpty() {
		return (heap.isEmpty());
	}
	
	/**
	 * Returns Heap by returning the address of the Heap.
	 * @return Heap
	 */
	public ArrayList<Process> getHeap(){
		return heap;
	}
	
	/**
	 * Clears the Heap.
	 */
	public void clearHeap() {
		heap.clear();
		heap.add(null);
	}
	
	/**
	 * Function for calculation the left node location
	 * based on the startingIndex from maxHeapifyDown() parameter.
	 * @param i
	 * @return left node location
	 */
	private static int left(int i) {
		return (2*i);
	}
	
	/**
	 * Function for calculation the right node location
	 * based on the startingIndex from maxHeapifyDown() parameter.
	 * @param i
	 * @return right node location
	 */
	private static int right(int i) {
		return ((2*i)+1);
	}
	
	/**
	 * Outputs toString()
	 */
	public String toString() {
		return heap.toString();
	}
}
