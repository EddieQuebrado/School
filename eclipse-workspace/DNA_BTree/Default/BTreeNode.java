package Default;
import java.util.ArrayList;

public class BTreeNode {
		
		private int parent;
		private ArrayList<TreeObject> keys;
		private ArrayList<Integer> children;
		private int numKeys;
		private boolean isLeaf;
		private int offset;
		private int counter;
		
		/**
		 * 
		 * @param offset
		 */
		public BTreeNode(int nodeOffset, int parentOffset) {
			keys = new ArrayList<TreeObject>();
			children = new ArrayList<Integer>();
			numKeys = 0;
			offset = nodeOffset;
			parent = parentOffset;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getOffset() {
			return offset;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getParent() {
			return parent;
		}
		
		/**
		 * 
		 * @param Parent
		 */
		public void setParent(int Parent) {
			this.parent = Parent;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getNumKeys() {
			return numKeys;
		}
		
		/**
		 * 
		 * @param numKeys
		 */
		public void setNumKeys(int numKeys) {
			this.numKeys = numKeys;
		}
		
		/**
		 * 
		 * @param i
		 * @return
		 */
		public TreeObject getKey(int i) {
			TreeObject key = keys.get(i);
			return key;
		}
		
		/**
		 * 
		 * @param object
		 */
		public void addKey(TreeObject object) {
			keys.add(object);
		}
		
		/**
		 * 
		 * @param i
		 * @param object
		 */
		public void addKey(int i, TreeObject object) {
			keys.add(i, object);
		}
		
		/**
		 * 
		 * @param i
		 * @return
		 */
		public TreeObject removeKey(int i) {
			return keys.remove(i);
		}
		
		/**
		 * 
		 * @return
		 */
		public ArrayList<TreeObject> getKeys(){
			return keys;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getKeyCount() {
			int i = 0;
			for(TreeObject o : keys) {
				i++;
			}
			return i;
		}
		
		/**
		 * 
		 * @return
		 */
		public ArrayList<Integer> getChildren(){
			return children;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getChildCount() {
			int i = 0;
			for(Integer o : children) {
				i++;
			}
			return i;
		}
		
		/**
		 * 
		 * @param i
		 * @return
		 */
		public int getChild(int i) {
			return children.get(i).intValue();
		}
		
		/**
		 * 
		 * @param i
		 */
		public void addChild(int i) {
			children.add(i);
		}
		
		/**
		 * 
		 * @param c
		 * @param i
		 */
		public void addChild(Integer c, int i) {
			children.add(c, i);
		}
		
		/**
		 * 
		 * @param i
		 */
		public void removeChild(int i) {
			children.remove(i);
		}
		
		/**
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return isLeaf;
		}
		
		/**
		 * 
		 * @param b
		 */
		public void setLeaf(boolean b) {
			this.isLeaf = b;
		}
}