public class multiplesOf3and5 {
	final static int LIMIT = 1000;

	public static void main(String args[]){
		long result = solution(LIMIT);
		System.out.println("The sum of multiples under " + LIMIT + ": " + result);
	}
	
	/**
	 * Finds the sum of all multiples of 3 or 5 under a limit.
	 * @param limit
	 * @return sum of all multiples of 3 or 5
	 */
	public static long solution(int limit){
		long sum = 0;
		for(int i = 0; i < limit; ++i){
			if(i%3==0){
				sum += i;
			} else if(i%5==0){
				sum += i;
			}
		}
		return sum;
	}
}
