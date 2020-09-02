/**
 * Represents an Object in the BTree.
 */
public class TreeObject implements Comparable<TreeObject> {
	// Instance Variables
	private long dna; // long representation of the dna string
	private int frequency; // counter to keep track of the frequency of the object
	private int sequenceLength; // length of the dna string

	/**
	 * Constructor : Initializes the instance variables
	 * 
	 * @param dna            : The key value
	 * @param sequenceLength : Length of the dna sequence
	 */
	public TreeObject(long dna, int sequenceLength) {
		this.dna = dna;
		this.sequenceLength = sequenceLength;
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
	 * Setter Method : Sets the frequency of this object to the specified value.
	 * 
	 * @param value : The integer value that's to be set as the frequency.
	 */
	public void setFrequency(int value) {
		this.frequency = value;
	}


	/**
	 * Getter Method : Returns the actual string of the long object.
	 * 
	 * @return : Returns the actual string
	 */
	public String getActualString() {
		String binaryString = Long.toBinaryString(dna);
		
		while(binaryString.length() != sequenceLength*2) {
			binaryString = "0"+binaryString;
		}
		
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

		// return the actualString if its length is equal to the sequenceLength, else,
		// add sequenceLength-actualString.length() # of a's in the front if
		// actualString's length is not equal to the sequenceLength
		if (actualString.length() == sequenceLength) {
			return actualString;
		} else {
			String temp = "";
			int missingNumChars = sequenceLength - actualString.length();
			for (int i = 0; i < missingNumChars; i++) {
				temp += "a";
			}
			temp += actualString;
			return temp;
		}
	}

	@Override
	public int compareTo(TreeObject o) {
		if (this.getKey() > o.getKey()) {
			return 1;
		}
		return -1;
	}

	/**
	 * Returns the String representation of the the actual string combined with the
	 * frequency.
	 */
	public String toString() {
		return getActualString() + ": " + frequency + "\n";
	}

}