/**
 * Indicates a heap underflow exception.
 * @author Eddie Quebrado
 */
@SuppressWarnings("serial")
public class HeapUnderflowException extends Exception {
	public HeapUnderflowException(String msg) {
		super(msg);
	}
}
