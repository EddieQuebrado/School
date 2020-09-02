import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private int modCount;
	
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}
	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(head);
		head = newNode;
		if(tail == null) {
			tail = newNode;
		}
		size++;
		modCount++;
	}
	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T>(element);
		if(isEmpty()) {
			head = newNode;
		} else {
			tail.setNext(newNode);
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
		Node<T> targetNode = head;
		while(targetNode != null && !targetNode.getElement().equals(target)) {
			targetNode = targetNode.getNext();
		}
		if(targetNode == null) {
			throw new NoSuchElementException();
		}
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(targetNode.getNext());
		targetNode.setNext(newNode);
		if(targetNode == tail) {
			tail = newNode;
		}
		size++;
		modCount++;
	}
	@Override
	public void add(int index, T element) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(element);
		if(index == 0) { // new head
			newNode.setNext(head);
			head = newNode;
		} else { // somewhere after head
			Node<T> previousNode = head;
			for(int i = 0; i < index-1; i++) {
				previousNode = previousNode.getNext();
			}
			newNode.setNext(previousNode.getNext());
			previousNode.setNext(newNode);
		}
		if(newNode.getNext() == null) { // new tail
			tail = newNode;
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
		head = head.getNext();
		if(size == 1) {
			tail = null;
		}
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
			Node<T> currentNode = head;
			while(currentNode.getNext() != tail) {
				currentNode = currentNode.getNext();
			}
			tail = currentNode;
			tail.setNext(null);
		} else {
			head = tail = null;
		}
		size--;
		modCount++;
		return retVal;
	}
	@Override
	public T remove(T element) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int index = indexOf(element);
		if(index < 0 || index >= size()) {
			throw new NoSuchElementException();
		}
		return remove(index);
	}
	@Override
	public T remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		T retVal;
		if(index == 0) {
			retVal = head.getElement();
			head = head.getNext();
			if(head == null) {
				tail = null;
			}
		} else {
			Node<T> prevNode = head;
			for(int i = 0; i < index - 1; i++) {
				prevNode = prevNode.getNext();
			}
			if(prevNode == tail) {
				throw new NoSuchElementException();
			}
			retVal = prevNode.getNext().getElement();
			prevNode.setNext(prevNode.getNext().getNext());
			if(prevNode.getNext() == null) {
				tail = prevNode;
			}
		}
		size--;
		modCount++;
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
		T retVal;
		if(index == 0) {
			retVal = head.getElement();
		} else {
			Node<T> currentNode = head;
			for(int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			if(currentNode.getNext() == null) {
				retVal = tail.getElement();
			}
			retVal = currentNode.getElement();
		}
		return retVal;
	}
	@Override
	public int indexOf(T element) {
		// navigate through the list and to find the element you are looking for
		// by a loop.
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
		boolean b = true;
		Node<T> currentNode = head;
		while(currentNode != null && !target.equals(currentNode.getElement())) {
			currentNode = currentNode.getNext();
		}
		if(currentNode == null) {
			b = false;
		}
		return b;
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
		
		private Node<T> nextNode;
		private int iterModCount;
		private boolean canRemove;
		
		public UIIterator() {
			nextNode = head;
			iterModCount = modCount;
			canRemove = false;
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
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T retVal = nextNode.getElement();
			nextNode = nextNode.getNext();
			canRemove = true;
			return retVal;
		}
		
		public void remove() {
			if(iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if(!canRemove) {
				throw new IllegalStateException();
			}
			if(head.getNext() == nextNode) {
				head = nextNode;
				if(size == 1) {
					tail = null;
				}
			} else {
				Node<T> prevPrev = head;
				while(prevPrev.getNext().getNext() != nextNode) {
					prevPrev = prevPrev.getNext();
				}
				prevPrev.setNext(nextNode);
				if(nextNode == null) {
					tail = prevPrev;
				}
			}
			modCount++;
			iterModCount++;
			size--;
			canRemove = false;
		}
	}

}
