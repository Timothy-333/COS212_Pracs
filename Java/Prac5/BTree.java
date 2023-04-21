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
		throw new UnsupportedOperationException();
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
			currentNode.setKey(currentNode.getSmallerKeyPos(data)+1, data);
		}
		else if(currentNode.childrenLength() == 0 && currentNode.keysLength() == m-1)
		{
			currentNode.setKey(currentNode.getSmallerKeyPos(data)+1, data);
			currentNode = split(currentNode);
		}
		throw new UnsupportedOperationException();
	}
	private Node<T> split(Node<T> node)
	{
		Node<T> newNode = new Node<T>(m);
		Node<T> parentNode = node.parent;
		if(parentNode == null)
		{
			parentNode = new Node<T>(m);
			root = parentNode;
		}
		int pos = parentNode.getSmallerKeyPos(node.getKey(0));
		parentNode.setKey(pos, node.getKey(0));
		parentNode.setChildNode(pos, node);
		parentNode.setChildNode(pos+1, newNode);
		for(int i = 0; i < m-1; i++)
		{
			if(i < (m-1)/2)
			{
				newNode.setKey(i, node.getKey((m-1)/2+i+1));
				node.setKey((m-1)/2+i+1, null);
			}
			else
			{
				newNode.setKey(i, null);
			}
		}
		return parentNode;
	}
	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) {
		// TODO - implement BTree.find
		throw new UnsupportedOperationException();
	}

	public Node<T>[] nodes() {
		// TODO - implement BTree.leaves
		throw new UnsupportedOperationException();
	}

	public int numKeys() {
		// TODO - implement BTree.numKeys
		throw new UnsupportedOperationException();
	}

	public int countNumNodes() {
		// TODO - implement BTree.countNumLeaves
		throw new UnsupportedOperationException();
	}

}