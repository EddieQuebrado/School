import java.io.IOException;

public class evenFibNums {
	
	final static long limit = 4000000;	
	
	public static void main(String args[]) {
		long i = 0, result = 0, sum = 0;
		while(result < limit){
			result = solution(i);
			i++;
			if(result%2==0){
				sum += result;
			}
		}
		System.out.println(sum);
	}

	/**
 	* Fibonacci sequence up to a limit 
 	*
 	*/
	public static long solution(long limit){
		if(limit < 0) throw new IllegalArgumentException("Limit is less than 0");
		if(limit == 1 || limit == 0) {
			return limit;
		} else {
			return solution(limit-1) + solution(limit-2);
		}
	}
}
