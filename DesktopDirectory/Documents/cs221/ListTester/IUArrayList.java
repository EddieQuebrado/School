import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private T[] array;
	private int rear;
	private int modCount;
	private static int DEFAULT_SIZE = 10;
	
	public IUArrayList() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public IUArrayList(int intialCapacity) {
		array = (T[])(new Object[intialCapacity]);
		rear = 0;
		modCount = 0;
	}
	
	@Override
	public void addToFront(T element) {
		add(0, element);
	}

	@Override
	public void addToRear(T element) {
		expandArrayIfNecessary();
		array[rear] = element;
		rear++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		int targetElement = indexOf(target);
		if(targetElement < 0) {
			throw new NoSuchElementException();
		}
		add(targetElement+1, element);
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		expandArrayIfNecessary();
		for(int i = rear; i > index; i--) {
			array[i] = array[i-1];
		}
		array[index] = element;
		rear++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return remove(0);
	}

	@Override
	public T removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return remove(rear-1);
	}

	@Override
	public T remove(T element) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int targetElement = indexOf(element);
		if(targetElement < 0) {
			throw new NoSuchElementException();
		}
		return remove(targetElement);
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		T retVal = array[index];
		for(int i = index; i < rear-1; i++) {
			array[i] = array[i+1];
		}
		array[rear-1] = null;
		rear--;
		modCount++;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		outOfBoundsCheck(index);
		array[index] = element;
		modCount++;
	}

	@Override
	public T get(int index) {
		outOfBoundsCheck(index);
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		for (int i = 0; i < rear && index < 0; i++) {
			if (element.equals(array[i])) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public T first() {
		if(isEmpty() == true) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		if(isEmpty() == true) {
			throw new NoSuchElementException();
		}
		return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) >= 0);
	}

	@Override
	public boolean isEmpty() {
		return (rear == 0);
	}

	@Override
	public int size() {
		return rear;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(T element: this) {
			str.append(element.toString());
			str.append(", ");
		}
		if(!isEmpty()) {
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new UIIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
	
	private class UIIterator implements Iterator<T> {
		
		private int currentIndex;
		private int iterModCount;
		private boolean canRemove;
		
		public UIIterator() {
			currentIndex = 0;
			iterModCount = modCount;
			canRemove = false;
		}

		@Override
		public boolean hasNext() {
			if(modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			return(currentIndex < rear);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			currentIndex++;
			return array[currentIndex-1];
		}
		
		public void remove() {
			if(modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			if(!canRemove) {
				throw new IllegalStateException();
			}
			for(int i = currentIndex - 1; i < rear-1; i++) {
				array[i] = array[i+1];
			}
			array[rear-1] = null;
			currentIndex--;
			rear--;
			modCount++;
			iterModCount++;
			canRemove = false;
		}
	}
	
	private void expandArrayIfNecessary() {
		if(array.length == rear) {
			array = Arrays.copyOf(array, array.length*2);
		}
	}
	
	private void outOfBoundsCheck(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
	}

}
