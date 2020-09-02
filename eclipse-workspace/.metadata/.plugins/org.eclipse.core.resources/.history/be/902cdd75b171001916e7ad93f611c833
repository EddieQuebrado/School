import java.util.ArrayList;
import java.util.Collections;

public class BTreeNode {
	// Instance Variables
	private int offset; // Metadata
	private int isLeaf; // Metadata
	private int parent; // Metadata
	private int numKeys; // Metadata
	private int degree;

	private TreeObject[] keys;
	private int[] children;

	public BTreeNode(int offset, int degree, int parent) {
		this.offset = offset;
		this.degree = degree;
		this.parent = parent;

		isLeaf = 1; // true, 0 = false

		keys = new TreeObject[2 * degree - 1];
		children = new int[2 * degree];
		numKeys = 0;

		for (int i = 0; i < 2 * degree; i++) {
			children[i] = -1;
		}
	}

	public int getDegree() {
		return degree;
	}

	public int getIsLeaf() {
		return isLeaf;
	}

	public int getNumKeys() {
		return numKeys;
	}

	public int getParent() {
		return parent;
	}

	public void setIsLeaf(int value) {
		isLeaf = value;
	}

	public int getOffset() {
		return offset;
	}

	public void setParent(int offsetValue) {
		parent = offsetValue;
	}

	public boolean isFull() {
		return (numKeys == ((2 * degree) - 1));
	}

	public int insertKey(TreeObject k) {
		boolean found = false;
		for (int i = 0; i < numKeys; i++) {
			if (keys[i].getKey() == k.getKey()) {
				found = true;
				keys[i].increaseFrequency();
				return -1;
			}
		}
		if (!found) {
			keys[numKeys] = k;
			numKeys++;
		}

		ArrayList<TreeObject> tempList = new ArrayList<>();

		for (int i = 0; i < numKeys; i++) {
			tempList.add(keys[i]);
		}

		Collections.sort(tempList);

		int returnIndex = 0;

		for (int i = 0; i < numKeys; i++) {
			keys[i] = tempList.get(i);
			if (keys[i].getKey() == k.getKey()) {
				returnIndex = i;
			}
		}
		return returnIndex;
	}

	public int findChildWithOffsetValue(int offset) {
		for (int i = 0; i < 2 * degree; i++) {
			if (children[i] == offset) {
				return i;
			}
		}
		return -1;
	}

	public void setChildAtIndex(int index, int value) {
		children[index] = value;
	}

	public int getChildAtIndex(int index) {
		return children[index];
	}

	public int getIndexOfKey(TreeObject k) {
		for (int i = 0; i < numKeys; i++) {
			if (keys[i].getKey() == k.getKey()) {
				return i;
			}
		}
		return -1;
	}

	public void insertChildOffset(int index, int offsetValue) {
		if (index == numKeys) {
			children[index] = offsetValue;
		} else {
			int i;
			for (i = numKeys; i > index; i--) {
				children[i] = children[i - 1];
			}
			children[i] = offsetValue;
		}
	}

	public ArrayList<TreeObject> getListOfKeys() {
		ArrayList<TreeObject> listOfKeys = new ArrayList<>();
		for (int i = 0; i < numKeys; i++) {
			listOfKeys.add(keys[i]);
		}
		return listOfKeys;
	}

	public ArrayList<Integer> getListOfChildren() {
		ArrayList<Integer> listOfChildren = new ArrayList<>();
		for (int i = 0; i < numKeys + 1; i++) {
			listOfChildren.add(children[i]);
		}
		return listOfChildren;
	}

	public void removeKey(int index) {
		for (int i = index; i < numKeys - 1; i++) {
			keys[i] = keys[i + 1];
		}
		numKeys--;
		keys[numKeys] = null;
	}

	public void setKeyToNull(int index) {
		keys[index] = null;
		numKeys--;
	}

	public TreeObject getKey(int index) {
		return keys[index];
	}
}