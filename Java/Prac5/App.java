public class App {
	public static void main(String[] args) 
	{
		// create a BTree with order 3
		BTree<Integer> btree = new BTree<Integer>(4);
		// insert some data
		btree.insert(10);
		btree.insert(20);
		btree.insert(5);
		btree.insert(15);
		btree.insert(6);
		btree.insert(1);
		btree.insert(3);
		btree.insert(30);
	
		// find some data
		Node<Integer> node1 = btree.find(20);
		if (node1 != null) 
		{
			System.out.println(node1.toString());
		}
	
		// count number of nodes and keys
		int numNodes = btree.countNumNodes();
		int numKeys = btree.numKeys();
	
		System.out.printf("Number of nodes: %d\n", numNodes);
		System.out.printf("Number of keys: %d\n", numKeys);
	
		// print out all keys in order
		Node<Integer>[] nodes = btree.nodes();
		for (int i = 0; i < nodes.length; i++) 
		{
			System.out.println(nodes[i].toString());
		}
	}
	
}