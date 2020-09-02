import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
//		String string1 = "aaaga";
//		String string2 = "aaaga";
//		String string3 = "aaaat";
//		String string4 = "aaagt";
//		String string5 = "aaaag";
//
//		Long longValue1 = Long.parseLong(toBinaryString(string1), 2);
//		Long longValue2 = Long.parseLong(toBinaryString(string2), 2);
//		Long longValue3 = Long.parseLong(toBinaryString(string3), 2);
//		Long longValue4 = Long.parseLong(toBinaryString(string4), 2);
//		Long longValue5 = Long.parseLong(toBinaryString(string5), 2);
//
//		TreeObject treeObj1 = new TreeObject(longValue1, 5);
//		TreeObject treeObj2 = new TreeObject(longValue2, 5);
//		TreeObject treeObj3 = new TreeObject(longValue3, 5);
//		TreeObject treeObj4 = new TreeObject(longValue4, 5);
//		TreeObject treeObj5 = new TreeObject(longValue5, 5);
//
//		BTreeNode btn = new BTreeNode(12, 4, -1);
//
//		btn.addKey(treeObj1);
//		btn.addKey(treeObj2);
//		btn.addKey(treeObj3);
//		btn.addKey(treeObj4);
//		btn.addKey(treeObj5);
//
//		btn.setIsLeaf(1);
//
//		int child1 = 40;
//		int child2 = 50;
//		int child3 = 60;
//		int child4 = 70;
//		int child5 = 80;
//		int child6 = 90;
//
//		btn.setChild(0, child1);
//		btn.setChild(1, child2);
//		btn.setChild(2, child3);
//		btn.setChild(3, child4);
//		btn.setChild(4, child5);
//		btn.setChild(5, child6);
//
//		int i = 0;
//		for (TreeObject t : btn.getListOfKeys()) {
//			// if(t != null) {
//			System.out.print("key: " + i + " = " + t.getKey() + " ");
//			// }
//			i++;
//		}
//
//		System.out.println();
//
//		for (TreeObject t : btn.getListOfKeys()) {
//			// if(t != null) {
//			System.out.print(t.toString() + " ");
//			// }
//		}
//		System.out.println();
//
//		BTree btree = new BTree(4, 5);
//
//		System.out.println("\n======BTREE METADA READ FROM FILE=======");
//		System.out.println("Degree of the tree : " + btree.readBTreeMetadata().get(0));
//		System.out.println("Substring length : " + btree.readBTreeMetadata().get(1));
//		System.out.println("Location of root : " + btree.readBTreeMetadata().get(2));
//		System.out.println("========================================\n");
//
//		btree.writeBTreeNode(btn, 12);
//
//		BTreeNode retBtn = btree.readBTreeNode(12);
//
//		System.out.println("==========NODE READ FROM FILE===========");
//		System.out.println("retBtn offset : " + retBtn.getOffset());
//		System.out.println("retBtn degree : " + retBtn.getDegree());
//		System.out.println("retBtn isLeaf : " + retBtn.getIsLeaf());
//		System.out.println("retBtn parent : " + retBtn.getParent());
//		System.out.println("retBtn numKeys : " + retBtn.getNumOfKeys());
//		System.out.println("retBtn listOfKeys : " + retBtn.getListOfKeys().toString());
//		System.out.println("retBtn listOfChildren : " + retBtn.getListOfChildren().toString());
//		System.out.println("========================================\n");

//		BTree btree = new BTree(2,5);
//		ArrayList<String> fromGbk = FileParser.getArrayListOfKeys("myTest.gbk", 3);
//		
//		for(String str : fromGbk) {
//			String binaryString = toBinaryString(str);
//			Long longValue = Long.parseLong(binaryString, 2);
//			TreeObject treeObject = new TreeObject(longValue,3);
//			btree.BTree_Insert(treeObject);
//		}
//		
//		BTreeNode temp = btree.readBTreeNode(12);
//		System.out.println(temp.getListOfKeys().toString());
		// btree.printOutAllNodes();

		String s1 = "aac";
		String s2 = "aat";
		String s3 = "aag";

		String s4 = "cca";
		String s5 = "ccg";
		String s6 = "cct";

		String s7 = "gga";
		String s8 = "ggc";
		String s9 = "ggt";
		String s10 = "aac";

		Long l1 = Long.parseLong(toBinaryString(s1), 2);
		Long l2 = Long.parseLong(toBinaryString(s2), 2);
		Long l3 = Long.parseLong(toBinaryString(s3), 2);

		Long l4 = Long.parseLong(toBinaryString(s4), 2);
		Long l5 = Long.parseLong(toBinaryString(s5), 2);
		Long l6 = Long.parseLong(toBinaryString(s6), 2);

		Long l7 = Long.parseLong(toBinaryString(s7), 2);
		Long l8 = Long.parseLong(toBinaryString(s8), 2);
		Long l9 = Long.parseLong(toBinaryString(s9), 2);
		Long l10 = Long.parseLong(toBinaryString(s10), 2);

		TreeObject to1 = new TreeObject(l1, 3);
		TreeObject to2 = new TreeObject(l2, 3);
		TreeObject to3 = new TreeObject(l3, 3);

		TreeObject to4 = new TreeObject(l4, 3);
		TreeObject to5 = new TreeObject(l5, 3);
		TreeObject to6 = new TreeObject(l6, 3);

		TreeObject to7 = new TreeObject(l7, 3);
		TreeObject to8 = new TreeObject(l8, 3);
		TreeObject to9 = new TreeObject(l9, 3);
		TreeObject t10 = new TreeObject(l10, 3);

		BTreeNode tempNode = new BTreeNode(12,3,-1);
		
		tempNode.insertKey(to2);
		tempNode.insertKey(to1);
		tempNode.insertKey(to3);
		tempNode.insertKey(to3);
		tempNode.insertKey(to3);
	
		BTreeNode node3	= new BTreeNode(12, 3, -1);
		node3.insertKey(to1);
		node3.insertKey(to1);
		node3.insertKey(to1);
		node3.insertKey(to7);
		node3.insertKey(to8);
		node3.insertKey(to5);
		
		Cache cache = new Cache(2);
		
		cache.addObject(tempNode);
		cache.addObject(node3);
		
//		for(int i = 0; i <= cache.getSize(); i++) {
//			TreeObject to10 = ((BTreeNode) cache.getObject(tempNode)).getKey(i);
//			System.out.println("Object " + i + " : " + to10.toString());
//		}
		
		for(int i = 0; i < cache.getSize(); i++) {
			BTreeNode tempNode2 = (BTreeNode) cache.getObject(i);
			ArrayList<TreeObject> tempArray = tempNode2.getListOfKeys();
			for(TreeObject to: tempArray) {
				if(to.getKey() == t10.getKey()) {
					to.increaseFrequency();
				} else {
					System.out.println("Here");
				}
			}
		}
		
		System.out.println(to1.toString());
		
		
//		tempNode.removeKey(1);
//		
//		for(TreeObject to : tempNode.getListOfKeys()) {
//			System.out.println(to.toString());
//		}
//
//		BTree btree = new BTree(5, 6, false, 0, false);
//
//		btree.bTreeInsert(to1);
//		btree.bTreeInsert(to2);
//		btree.bTreeInsert(to3);
//		
//		btree.bTreeInsert(to4);
//		btree.bTreeInsert(to5);
//		btree.bTreeInsert(to6);
//		
//		btree.bTreeInsert(to7);
//		btree.bTreeInsert(to8);
//		btree.bTreeInsert(to9);
//
//		ArrayList<String> dnaSequences = FileParser.getArrayListOfKeys("test3.gbk", 6);
//
//		for (String str : dnaSequences) {
//			// System.out.println(str);
//			Long longstr = Long.parseLong(toBinaryString(str), 2);
//			TreeObject treeObject = new TreeObject(longstr, 6);
//			btree.bTreeInsert(treeObject);
//		}
//		
		/*int i = 0;
		for (BTreeNode btn : btree.getListOfNodes()) {
			System.out.println("Location: " + i + " ->" + btn.getListOfKeys().toString()
					+ "         ###########          Parent -> " + btn.getParent());

			i++;
		}*/
	}

	public static String toBinaryString(String string) {
		String binaryString = "";
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 'a') {
				binaryString += "00";
			} else if (string.charAt(i) == 't') {
				binaryString += "11";
			} else if (string.charAt(i) == 'c') {
				binaryString += "01";
			} else if (string.charAt(i) == 'g') {
				binaryString += "10";
			}
		}
		return binaryString;
	}

}
