
public class ProcessGenerator {
	
	public ProcessGenerator(double d) {
		
	}
	
	public boolean query() {
		return true;
	}
	
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxLevel) {
		Process newProc = new Process(currentTime, maxProcessTime, maxLevel);
		return newProc;
	}
}
