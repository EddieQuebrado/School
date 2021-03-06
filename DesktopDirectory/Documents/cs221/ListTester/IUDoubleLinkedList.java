import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Creates a double linked list that consists of a set of
 * sequentially linked nodes. Each node contains references
 * to the previous and to the next node in the sequence of
 * node.
 * @author eddiequebrado
 *
 * @param <T>
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T>{
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private int modCount;
	
	/**
	 * Construct a new empty double linked list
	 */
	public IUDoubleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(head);
		if(head != null) {
			head.setPrev(newNode);
		} else {
			tail = newNode;
		}
		head = newNode;
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setPrev(tail);
		if(tail != null ) {
			tail.setNext(newNode);
		} else {
			head = newNode;
		}
		tail = newNode;
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		ListIterator<T> lit = listIterator();
		boolean foundIt = false;
		while(!foundIt && lit.hasNext()) {
			T value = lit.next();
			if(value.equals(target)) {
				foundIt = true;
			}
		}
		if(!foundIt) {
			throw new NoSuchElementException();
		}
		lit.add(element);
	}

	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(element);
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		newNode.setNext(current);
		if(current != null) {
			newNode.setPrev(current.getPrev());
			current.setPrev(newNode);
		} else {
			newNode.setPrev(tail);
			tail = newNode;
		}
		if(current != head) {
			newNode.getPrev().setNext(newNode);
		} else {
			head = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		if(size > 1) {
			head.getNext().setPrev(null);
		} else {
			tail = null;
		}
		head = head.getNext();
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T retVal = tail.getElement();
		if(size > 1) {
			tail.getPrev().setNext(null);
		} else {
			head = null;
		}
		tail = tail.getPrev();
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		ListIterator<T> lit = listIterator();
		boolean foundIt = false;
		while(lit.hasNext() && !foundIt) {
			if(lit.next().equals(element)) {
				foundIt = true;
			}
		}
		if(!foundIt) {
			throw new NoSuchElementException();
		}
		T retVal = lit.previous();
		lit.remove();
		return retVal;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListIterator<T> lit = listIterator(index);
		T retVal = lit.next();
		lit.remove();
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			head.setElement(element);
		} else {
			Node<T> currentNode = head;
			for(int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			if(currentNode.getNext() == null) {
				tail.setElement(element);
			}
			currentNode.setElement(element);
		}
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}	
		ListIterator<T> lit = listIterator(index);
		T retVal = lit.next();
		return retVal;
	}

	@Override
	public int indexOf(T element) {
		int index = 0;
		Node<T> currentNode = head;
		while(currentNode != null && !element.equals(currentNode.getElement())) {
			currentNode = currentNode.getNext();
			index++;
		}
		if(currentNode == null) {
			index = -1;
		}
		return index;
	}

	@Override
	public T first() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		Node<T> currentNode = head;
		while(currentNode != null && !target.equals(currentNode.getElement())) {
			currentNode = currentNode.getNext();
		}
		if(currentNode == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return(size == 0);
	}

	@Override
	public int size() {
		return size;
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
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}
	
	/**
	 * ListIterator for IUDoubleLinkedList
	 * @author eddiequebrado
	 *
	 */
	private class DLLIterator implements ListIterator<T> {
		
		private Node<T> nextNode;
		private int iterModCount;
		private int nextIndex;
		private Node<T> lastNode;
		
		public DLLIterator(){
			this(0);
		}
		
		public DLLIterator(int startingIndex) {
			if(startingIndex < 0 || startingIndex > size)
			{
				throw new IndexOutOfBoundsException();
			}
			nextNode = head;
			for(int i = 0; i < startingIndex; i++) {
				nextNode = nextNode.getNext();
			}
			nextIndex = startingIndex;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return(nextNode != null);
		}

		@Override
		public T next() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			lastNode = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			return lastNode.getElement();
		}

		@Override
		public boolean hasPrevious() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return(nextNode != head);
		}

		@Override
		public T previous() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if(nextNode != null) {
				nextNode = nextNode.getPrev();
			} else {
				nextNode = tail;
			}
			nextIndex--;
			lastNode = nextNode;
			return nextNode.getElement();
		}

		@Override
		public int nextIndex() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex-1;
		}

		@Override
		public void remove() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(lastNode == null) {
				throw new IllegalStateException();
			}
			if(lastNode != head) {
				lastNode.getPrev().setNext(lastNode.getNext());
			} else {
				head = lastNode.getNext();
				if(head != null) {
					head.setPrev(null);
				}
			}
			if(lastNode != tail) { // wasn't tail
				lastNode.getNext().setPrev(lastNode.getPrev());
			} else {
				tail = lastNode.getPrev();
				if(tail != null) {
					tail.setNext(null);
				}
			}
			if(lastNode == nextNode) {
				nextNode = nextNode.getNext();
				
			} else {
				nextIndex--;
			}
			modCount++;
			iterModCount++;
			size--;
			lastNode = null;
		}

		@Override
		public void set(T e) {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(lastNode == null) {
				throw new IllegalStateException();
			}
			lastNode.setElement(e);
			modCount++;
			iterModCount++;
		}

		@Override
		public void add(T e) {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			Node<T> newNode = new Node<T>(e);
			if(isEmpty()) {
				head = tail = newNode;
			} else {
				if(nextNode == head) {
					newNode.setNext(nextNode);
					nextNode.setPrev(newNode);
					head = newNode;
				} else if(nextNode == null) {
					tail.setNext(newNode);
					newNode.setPrev(tail);
					tail = newNode;
				} else {
					nextNode.getPrev().setNext(newNode);
					newNode.setNext(nextNode);
					newNode.setPrev(nextNode.getPrev());
					nextNode.setPrev(newNode);
				}
			}
			size++;
			modCount++;
			iterModCount++;
		}
		
	}

}
