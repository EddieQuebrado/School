
public class Node<T> {
	private T element;
	private Node<T> next;
	private Node<T> prev;

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	public Node(T element) {
		this.element = element;
		this.next = null;
	}
	
	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}
	
	/**

	 * @param element
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	/**
	 * @return next Node
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * @param next is equal to 
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null) + " Has prev: " + (prev != null);
	}

}
