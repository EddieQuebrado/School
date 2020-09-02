import java.io.FileWriter;
import java.io.IOException;

public class HashTable<T> {
	private int probingType;
	private int numProbes, numInserted;
	public int duplicates;
	private int load, size;
	private int probingCount[];
	private HashObject[] table;
	
	
	HashTable(int size, int probe, double loadFactor) {
		probingType = probe;
		duplicates = 0;
		numProbes = 0;
		numInserted = 0;
		this.size = size;
		probingCount = new int[size];
		table = new HashObject[size];
		load = (int)(loadFactor * size);
	}
	
	/**
	 * Insert a HashObject into the hash table
	 * @param object the HashObject to be inserted to table
	 * @param key the hash value of the object
	 * @return index at which the HashObject was inserted, -1 if overflow.
	 */
	public int insert(HashObject object, long key) {
		
		//System.out.println(numInserted + " : " + load);
        while (numInserted <= load) {
            for (int i = 0; i < size; i++) {
                int K = hashIndex(key, i);
                if (table[K] == null) {
                    table[K] = object;
                    numInserted++;
                    probingCount[K] = i + 1;
                    numProbes += (i + 1);
                    return K;
                } else if (equals(table[K].getKey(), object.getKey())) {
                    table[K].incDuplicates();
                    duplicates++;
                    return K;
                }
            }
        }
        //System.out.println("Stuck here??????????????????????????????????????????????????????");
        return -1;
	}
	
	private boolean equals(Long key1, Long key2) {
		if(key1.equals(key2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the average number of probes in the hash table.
	 * @return av.erage number of probes.
	 */
	public double getAverage() {
		double avg = (double) numProbes / (double) numInserted;
		return avg;
	}
	
	/**
	 * Gets number of probes
	 * @return an array containing number of probes.
	 */
	public int[] getProbeCount() {
		return probingCount;
	}
	
	/**
	 * Gets number of objects inserted into HashTable.
	 * @return number of objects
	 */
	public int getNumInserted() {
		return numInserted;
	}
	
	/**
	 * Return total amount of duplicate objects in HashTable
	 * @return total duplicate objects
	 */
	public int getDuplicates() {
		return duplicates;
	}
	
	/**
	 * Returns hash index.
	 * @param key the key-value of an object
	 * @param i the number to offset the key
	 * @return hash index.
	 */
	private int hashIndex(long key, int i) {
		int index;
		if(probingType == 0) {
			index = ((int)((key % size) + i) % size);
		} else {
			index = ((int) (((key % size) + i * (1 + (key % (size - 2)))) % size));
		}
		return index;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("\n");
		for(int i = 0; i < size; i++) {
			if(table[i] != null) {
				str.append("table[").append(i).append("]: ");
				str.append(table[i].toString());
				str.append("\n");
			}
		}
		return str.toString();
	}
	
	/**
	 * Dumps all linear hash table data to file 
	 * @param str linear hashing data
	 * @throws IOException
	 */
    public static void linDumpToFile(String str) throws IOException {
    	String fileContent = str;
    	FileWriter fileWriter = new FileWriter("linear-dump");
    	fileWriter.write(fileContent);
    	fileWriter.close();
	}
    
    /**
     * Dumps all double hash table data to file
     * @param str double hashing data
     * @throws IOException
     */
    public static void dubDumpToFile(String str) throws IOException {
        String fileContent = str;
        FileWriter fileWriter = new FileWriter("double-dump");
        fileWriter.write(fileContent);
        fileWriter.close();
    }
	
	/**
	 * Prints the hash table with the total number of probes for linear hashtable
	 * @return the hash table as string with total number of probes.
	 */
	public String linDebugTable() {
		StringBuilder str = new StringBuilder("\n");
		for(int i = 0; i < size; i++) {
			if(table[i] != null) {
				str.append("Table[").append(i).append("]: ");
				str.append(table[i].toString());
				str.append(" ").append(probingCount[i]).append(":");
				str.append("\n");
			}
		}
		try {
			linDumpToFile(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	/**
	 * Prints the hash table with the total number of probes for double hashtable
	 * @return the hash table as string with total number of probes.
	 */
	public String dubDebugTable() {
		StringBuilder str = new StringBuilder("\n");
		for(int i = 0; i < size; i++) {
			if(table[i] != null) {
				str.append("Table[").append(i).append("]: ");
				str.append(table[i].toString());
				str.append(" ").append(probingCount[i]).append(":");
				str.append("\n");
			}
		}
		try {
			dubDumpToFile(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	
	
	
}
