/**
 * @author Eddie Quebrado
 * @param <T>
 */

public class HashObject<T> {
	private T HashObject;
	private long key;
	private int duplicate;
	
	/**
	 * Creates a HashObject from the specified object
	 * @param object the object to turn to a HashObject
	 */
	public HashObject(T object) {
		duplicate = 0;
		key = getHash(object);
		HashObject = object;
		System.out.println(object);
	}
	
	/**
	 * Calculates a hash key for object.
	 * @param object the object to be calculated.
	 * @return the calculated hash value.
	 */
	private long getHash(T object) {
		long value = 0;
		if(object instanceof String) {
			value = Math.abs(object.hashCode());
		}
		if(object instanceof Integer) {
			value = Math.abs(object.hashCode());
		}
		if(object instanceof Long) {
			value = Math.abs(object.hashCode());
		}
		return value;
	}
	
	
	/**
	 * Return the hash key of the the object
	 * @return hash key
	 */
	public Long getKey() {
		return key;
	}
	
	/**
	 * Get the frequency of duplicates
	 * @return frequency of duplicates
	 */
	public int getDuplicates() {
		return this.duplicate;
	}
	
	/**
	 * Updates the frequency of duplicates
	 */
	public void incDuplicates() {
		this.duplicate++;
	}
	
	/**
	 * Get the HashObject
	 * @return HashObject
	 */
	public T getHashObject() {
		return HashObject;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += HashObject.toString() + " " + duplicate;
		return str;
	}
}
