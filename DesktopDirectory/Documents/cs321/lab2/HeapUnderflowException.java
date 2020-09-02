/**
 * Indicates a file formatting exception.
 * @author mvail
 */
@SuppressWarnings("serial")
public class HeapUnderflowException extends Exception {
	public HeapUnderflowException(String msg) {
		super(msg);
	}
}
