import java.io.File;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class HashTest {
	public static final int MIN = 95500, MAX = 96000;
	private static final int RANDOM = 1;
	private static final int SYSTIME = 2;
	private static final int FILE = 3;
	private static double userLoadFactor;
	private static int source = 0;
	private static int debugLevel = 0;
	@SuppressWarnings("unused")
	private static boolean isDone = false;
	private static String sourceType = "";
	
	
    public static void main(String args[]) {
    	if(args.length < 2 || args.length > 3) {
    		System.out.println(printUsage());
    		System.exit(1);
    	}
        source = Integer.parseInt(args[0]);
        userLoadFactor = Double.parseDouble(args[1]);
        if (args.length == 3 && args[2] != null) {
        	debugLevel = Integer.parseInt(args[2]);
        } else {
        	debugLevel = 0;
        }
        int size = (int)PrimeGenerator.primeFinder(MIN, MAX);
        int linOverflow = 0;
        int dubOverflow = 0;
        HashTable<Object> linear = new HashTable<Object>(size, 0, userLoadFactor);
        HashTable<Object> doubleHashing = new HashTable<Object>(size, 1, userLoadFactor);
        if(source == RANDOM) {
        	Random rand = new Random();
        	sourceType += "random number";
        	while(linOverflow != -1 || dubOverflow != -1) {
        		long randNum = Math.abs(rand.nextLong());
        		HashObject<Long> newObject = new HashObject<Long>(randNum);
        		long key = newObject.getKey();
        		linOverflow = linear.insert(newObject, key);
        		dubOverflow = doubleHashing.insert(newObject, key);
        		isDone = false;
        	}
        	isDone = true;
        } else if (source == SYSTIME) {
        	sourceType += "current time";
        	while(linOverflow != -1 || dubOverflow != -1) {
        		long currentTime = System.currentTimeMillis();
        		HashObject<Long> systimeObject = new HashObject<Long>(currentTime);
        		long key = systimeObject.getKey();
        		linOverflow = linear.insert(systimeObject, key);
        		dubOverflow = doubleHashing.insert(systimeObject, key);
        		isDone = false;
        	}
        	isDone = true;
        } else if (source == FILE) {
        	sourceType = "word-list";
        	try (Scanner scan = new Scanner(new File("word-list.txt"))){
                while (linOverflow != -1 && dubOverflow != -1){
                    while (scan.hasNextLine()){
                        String word = scan.nextLine();
                        HashObject<String> stringHashObject = new HashObject<>(word);
                        long key = stringHashObject.getKey();
                        linOverflow = linear.insert(stringHashObject, key);
                        dubOverflow = doubleHashing.insert(stringHashObject, key);
                        isDone = false;
                    }
                }
                isDone = true;
        	} catch (FileNotFoundException e) {
        		System.out.println(e.getMessage());
        	}
        } else {
        	System.out.println("Invalid source provided \nInput type:\n" + "\t 1 - Random numbers\n"
        			+ "\t 2 - Current System Time\n" + "\t 3 - File\n");
        	System.exit(1);
        }
        if(debugLevel > 1 || debugLevel < 0) {
        	System.out.println("Invalid debug level provided \nInput type:\n" + "\t0 - print default summary (default)\n"
        + "\t1 - print table with duplicates and number of probes");
        }
        
        if(debugLevel == 0){
        	String inputs = "A good table size is found: " + size + "\n" + 
        					"Data source type: " + sourceType + "\n";
            String LinearHashing = "Using Linear Hashing....\n" + "Input " + (linear.getNumInserted() + linear.getDuplicates())
            + " elements, of which " + linear.getDuplicates() + " duplicates\n" + "load factor = "
            		+ userLoadFactor + ", Avg. no. of probes " + linear.getAverage();
            
            String DoubleHashing = "Using Double Hashing....\n" + "Input " + (doubleHashing.getNumInserted() + doubleHashing.getDuplicates())
            + " elements, of which " + doubleHashing.getDuplicates() + " duplicates\n" + "load factor = "
            		+ userLoadFactor + ", Avg. no. of probes " + doubleHashing.getAverage();
            System.out.println(inputs);
            System.out.println(LinearHashing);
            System.out.println(DoubleHashing);
            
        }
        else  if(debugLevel == 1) {
        	System.out.println(linear.linDebugTable());
        	System.out.println(doubleHashing.dubDebugTable());
        }
    }
	
    public static String printUsage() {
        return "Usage: <input type> <load factor> [<debug level>]\n" +
                "input type:\n\t1 - random numbers \n\t2 - current system time\n\t3 - file\n" +
                "load factor: \n\t0 < A < 1" + "\ndebug level:\n\t0 - print default summary (default)\n\t1 - print table with duplicates and number of probes";
    }
    
    public static class PrimeGenerator {
    	public static boolean isPrime(long i){
            if(i%2 == 0 && i!=2) {
            	return false;
            } else {
            	if(i == 1) {
            		return false;
            	}
                for(int p=3;p<=i/2;p+=2){
                    if(i%p == 0) {
                    	return false;
                    }
                } 
                return true;
            }

    	}
        public static long primeFinder(long start, long end) {
            for (long i = start; i <= end; start++) {
                if (isPrime(start) && isPrime(start - 2)) {
                    return start;
            	}
            }
            return -1;
        }
	}
}
