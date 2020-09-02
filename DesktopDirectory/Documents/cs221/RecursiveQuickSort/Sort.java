import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>();
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		quicksort(list, c);
	}
	
	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list)
	{
		if(list.size() < 2) {
			return;
		}
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		int middle = list.size()/2;
		for(int i = 0; i < middle; i++) {
			left.add(list.removeFirst());
		}
		while(!list.isEmpty()) {
			right.add(list.removeFirst());
		}
		//recursively quicksort left and right
		quicksort(left);
		quicksort(right);
		// merge left and right
		while(!left.isEmpty() && !right.isEmpty()) {
			if(left.first().compareTo(right.first()) < 0) {
				list.add(left.removeFirst());
			} else {
				list.add(right.removeFirst());
			}
		}
		while(!left.isEmpty()) {
			list.add(left.removeFirst());
		}
		while(!right.isEmpty()) {
			list.add(right.removeFirst());
		}
	}
		
	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		if(list.size() < 2) {
			return;
		}
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		int middle = list.size()/2;
		for(int i = 0; i < middle; i++) {
			left.add(list.removeFirst());
		}
		while(!list.isEmpty()) {
			right.add(list.removeFirst());
		}
		//recursively quicksort left and right
		quicksort(left, c);
		quicksort(right, c);
		// merge left and right
		while(!left.isEmpty() && !right.isEmpty()) {
			if(c.compare(left.first(), right.first()) < 0) {
				list.add(left.removeFirst());
			} else {
				list.add(right.removeFirst());
			}
		}
		while(!left.isEmpty()) {
			list.add(left.removeFirst());
		}
		while(!right.isEmpty()) {
			list.add(right.removeFirst());
		}
	}
	
}
