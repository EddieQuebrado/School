package Default;
public class BTree {
	
	private int degree;
	private int substringLength;
	private int counter;
	public BTree(int degree, String fileName) {
		this.filename = fileName;
		this.degree = degree;
		BTreeNode node = new BTreeNode(Offset);
		root = node;
	}
	
	/**
	 * Private Getter Method : 
	 *  
	 * @return
	 */
	private BTreeNode getRoot() {
		return root;
	}
	
	/**
	 * BTree_Insert(T, k)
	 * r = root[T]
	 * if(number of things in r = 2t-1){
	 * 	s = allocate-node() // construct new node
	 * 	root[T] = s; //set root of BTreeNode(T) to s
	 * 	leaf[s] = false; // set s as not a leaf
	 * 	n[s] = 0; // set number of things in s to 0
	 * 	c_1[s] = r // set first child pointer of s to r
	 * 	B-Tree-Split-Child(s, 1, r)
	 * 	B-Tree-Insert-Nonfull(s,k)
	 * } else {
	 * 	B-Tree-Insert-Nonfull(r,k)
	 * }
	 */
	public void BTree_Insert(TreeObject k) {
		BTreeNode r = root;
		if(r.getKeyCount() == 2 * degree - 1) {
			BTreeNode node = new BTreeNode(Offset);
			//T.setParent(to);
		} else {
			BTree_Insert_Nonfull(k);
		}
	}
	
	/**
	 * BTree-Split-Child(x, i , y)
	 * z = allocate-node() // construct new node 
	 * leaf[z] = leaf[y] // set x and y to a leaf
	 * n[z] = t - 1 // set number of things in node z to t-1
	 * for(int j = 1; j < t-1; j++){
	 * 	key_j[z] = key_j+1[y] // reindexing keys in node z to node y
	 * }
	 * if(y is not a leaf){
	 * 	for(int j = 1; j < t; j++){
	 * 		c_j[z] = c_j+1[y] // reindexing child pointers
	 * 	}
	 * }
	 * n[y] = t - 1 // set the number of things in node y to t-1
	 * for(int j = n[x]+1; j > i + 1; j--){
	 * 	c_j+1[x] = c_j[x] // reindexing child pointers
	 * }
	 * c_j+1 = t // setting child pointer to t
	 * for(int j = n[x]; j > i; j--){
	 * 	key_j+1[x] = key_j[x] // reindexing keys in node x
	 * }
	 * key_i[x] = key_t[y] // set key_i in node x to key_t in node y
	 * n[x] = n[x] + 1 // increasing the amount of things by 1
	 * Disk-Write(x)
	 * Disk-Write(y)
	 * Disk-Write(z)
	 */
	public static void BTree_Split_Child(int i, BTreeNode y){
		
	}
	
	/**
	 * BTree_Insert_Nonfull(x , k)
	 * i = n[x] // set i to the number of things in x
	 * if(x is a leaf){
	 *	while(i >= 1 && k < key_i[x]){
	 * 		key_i+1[x] = key_i[x] // reindexing keys in node
	 * 		i--
	 * 	}
	 * 	key_j+1[x] = k //setting the key(k) at index j+1 in the node x
	 * 	n[x]++; // increasing the amount of things in the node x
	 * 	Disk-Write(x)
	 * } else {
	 * 	while(i >= 1 && k < key_i[x]){
	 * 		i-- // decreasing down to 0
	 * 	}/home/EDDIEQUEBRADO/eclipse-workspace
	 * 	i++ // increasing up to 1
	 * 	Disk-Read(C_i[x]) // grabbing the child pointer of x in the disk file
	 * 	if(n[c_i[x]] = 2t-1) // if number of child pointers in the node x equals 2t-1
	 * 		BTree-Split-Child(x, i, C_i[x])
	 * 		if(k > key_i[x]) // if Key(k) value is greater than key at index i in node x
	 * 			i++ // if this was increased then we will be inserting at the newly created node
	 * 		}
	 * 	}
	 * 	BTree-Insert-Nonfull(C_i[x], k)
	 * }
	 */
	public static void BTree_Insert_Nonfull(TreeObject k) {
		
	}
}
