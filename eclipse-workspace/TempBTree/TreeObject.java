/**
 * Represents an Object in the BTree.
 */
public class TreeObject {
	// Instance Variables
	private long dna; // long representation of the dna string
	private int frequency; // counter to keep track of the frequency of the object

	/**
	 * Constructor : Initializes the instance variables
	 * 
	 * @param dna : The key value
	 */
	public TreeObject(long dna) {
		this.dna = dna;
		frequency = 1;
	}

	/**
	 * Getter Method : Returns the long value of DNA.
	 * 
	 * @return : Returns the long object
	 */
	public long getKey() {
		return dna;
	}

	/**
	 * Method : Increases the frequency count by 1.
	 */
	public void increaseFrequency() {
		frequency++;
	}

	/**
	 * Getter Method : Returns the frequency count.
	 * 
	 * @return : Returns frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Private Getter Method : Returns the actual string of the long object.
	 * 
	 * @return : Returns the actual string
	 */
	private String getActualString() {
		String binaryString = Long.toBinaryString(dna);
		String actualString = "";
		for (int i = 0; i < binaryString.length(); i += 2) {
			String temp = binaryString.substring(i, i + 2);
			if (temp.equals("00")) {
				actualString += "a";
			} else if (temp.equals("11")) {
				actualString += "t";
			} else if (temp.equals("01")) {
				actualString += "c";
			} else if (temp.equals("10")) {
				actualString += "g";
			}
		}
		return actualString;
	}

	/**
	 * Returns the String representation of the the actual string combined with the
	 * frequency.
	 */
	public String toString() {
		return getActualString() + ": " + frequency;
	}

}