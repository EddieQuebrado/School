import java.util.ArrayList;

public class GeneBankCreateBTree{
	
	public static void main(String[] args) {
		String fileName = "test1.gbk";
		int k = 62;
		ArrayList<String> listOfKeys = FileParser.getArrayListOfKeys(fileName, k);
		for(String str: listOfKeys) {
			System.out.println(str);
		}
	}
}