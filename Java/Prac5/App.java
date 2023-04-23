public class App {
	public static void main(String[] args) 
	{
		// create a BTree with order 3
		BTree<Integer> btree = new BTree<Integer>(5);
		// insert some data
		// btree.insert(10);
		// btree.insert(20);
		btree.insert(5);
		btree.insert(15);
		btree.insert(30);
		btree.insert(1);
		btree.insert(3);
		btree.insert(30);
		btree.insert(12);
		btree.insert(100);
		// btree.insert(200);
		// btree.insert(300);
		// btree.insert(400);
		// btree.insert(2);
	
		// find some data
		Node<Integer> node1 = btree.find(1);
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
		if (nodes == null) 
		{
			System.out.println("No nodes");
			return;
		}
		for (int i = 0; i < nodes.length; i++) 
		{
			System.out.println(nodes[i].toString());
		}
	}
	
}