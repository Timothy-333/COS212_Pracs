public class BTree<T extends Comparable<T>> {

	private int m;
	private Node<T> root;

	/**
	 * 
	 * @param m
	 */
	public BTree(int m) 
	{
		this.m = m;
		root = new Node<T>(m);
		// throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 */
	public Node<T> insert(T data) 
	{
		Node<T> currentNode = root;
		while(currentNode.childrenLength() > 0)
		{
			int pos = currentNode.getSmallerKeyPos(data);
			if(pos == -1)
				return currentNode;
			currentNode = currentNode.getChildNode(pos);
		}
		if(currentNode.childrenLength() == 0 && currentNode.keysLength() < m-1)
		{
			currentNode.setKey(currentNode.getSmallerKeyPos(data), data);
		}
		else if(currentNode.childrenLength() == 0 && currentNode.keysLength() == m-1)
		{
			currentNode.setKey(currentNode.getSmallerKeyPos(data), data);
			currentNode = split(currentNode);
		}
		return currentNode;
		// throw new UnsupportedOperationException();
	}
	private Node<T> split(Node<T> node) 
	{
		Node<T> newNode = new Node<T>(m);
		Node<T> parent = node.parent;
		if(parent == null)
		{
			parent = new Node<T>(m);
			root = parent;
		}
		int pos = parent.getSmallerKeyPos((T)node.getKey(m/2));
		if(pos == -1)
			return node;
		parent.setKey(pos, node.getKey(m/2));
		parent.setChildNode(pos, node);
		parent.setChildNode(pos+1, newNode);
		for(int i = m/2+1; i < m-1; i++)
		{
			newNode.setKey(i-m/2-1, node.getKey(i));
			node.setKey(i, null);
		}
		for(int i = m/2+1; i < m; i++)
		{
			if(node.getChildNode(i) != null)
			{
				newNode.setChildNode(i-m/2-1, node.getChildNode(i));
				node.getChildNode(i).setParent(newNode);
				node.setChildNode(i, null);
			}
		}
		return parent;
	}
	
	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) 
	{
		Node<T> currentNode = root;
		while(currentNode.childrenLength() > 0)
		{
			int pos = currentNode.getSmallerKeyPos(data);
			if(pos == -1)
				return currentNode;
			currentNode = currentNode.getChildNode(pos);
		}
		return null;
		// throw new UnsupportedOperationException();
	}

	public Node<T>[] nodes() 
	{
		if (root == null) return null;
		Node<T>[] nodes = new Node[countNumNodes()];
		nodes[0] = root;
		int i = 1;
		while (i < nodes.length) {
			for (int j = 0; j < i; j++) { // Only consider nodes that have been added so far
				if (nodes[j] != null) {
					for (int k = 0; k < nodes[j].childrenLength(); k++) {
						if (nodes[j].getChildNode(k) != null) {
							nodes[i] = nodes[j].getChildNode(k);
							i++;
							if (i >= nodes.length) break; // Stop adding nodes when array is full
						}
					}
				}
			}
		}
		return nodes;
		// throw new UnsupportedOperationException();
	}

	public int numKeys() 
	{
		int numKeys = 0;
		Node<T>[] nodes = nodes();
		for(int i = 0; i < nodes.length; i++)
		{
			numKeys += nodes[i].keysLength();
		}
		return numKeys;
		// throw new UnsupportedOperationException();
	}

	public int countNumNodes() 
	{
		if(root == null)
			return 0;
		return countNumNodes(root);
		// throw new UnsupportedOperationException();
	}
	private int countNumNodes(Node<T> node) 
	{
		int numNodes = 1;
		for(int i = 0; i < node.childrenLength(); i++)
		{
			if(node.getChildNode(i) != null)
				numNodes += countNumNodes(node.getChildNode(i));
		}
		return numNodes;
	}
}