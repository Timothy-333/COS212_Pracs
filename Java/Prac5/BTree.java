public class BTree<T extends Comparable<T>> 
{

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
			currentNode.setKey(data);
		}
		else if(currentNode.childrenLength() == 0 && currentNode.keysLength() == m-1)
		{
			split(currentNode, data);
			currentNode = find(data);
		}
		return currentNode;
		// throw new UnsupportedOperationException();
	}
	@SuppressWarnings("unchecked")
	private Node<T> split(Node<T> node, T data) 
	{
		Node<T> leftNode = new Node<T>(m);
		Node<T> rightNode = new Node<T>(m);
		Node<T> parentNode = node.parent;
		if(parentNode == null)
		{
			parentNode = new Node<T>(m);
		}
		else
		{
			parentNode.removeChildNode(node);
		}
		int mid = (m-1)/2;
		T tempData[] = (T[])new Comparable[m];
		for(int i = 0; i < m-1; i++)
		{
			tempData[i] = node.getKey(i);
		}
		tempData[m-1] = data;
		for(int i = 0; i < m-1; i++)
		{
			for(int j = i+1; j < m; j++)
			{
				if(tempData[i].compareTo(tempData[j]) > 0)
				{
					T temp = tempData[i];
					tempData[i] = tempData[j];
					tempData[j] = temp;
				}
			}
		}
		for(int i = 0; i < mid; i++)
		{
			leftNode.setKey(tempData[i]);
		}
		for(int i = mid+1; i < m; i++)
		{
			rightNode.setKey(tempData[i]);
		}
		for(int i = 0; i < m; i++)
		{
			if(node.getChildNode(i) != null)
			{
				if(i <= mid)
				{
					leftNode.setChildNode(node.getChildNode(i));
					node.getChildNode(i).parent = leftNode;
				}
				else
				{
					rightNode.setChildNode(node.getChildNode(i));
					node.getChildNode(i).parent = rightNode;
				}
			}
		}
		if(parentNode.keysLength() >= m-1)
		{
			parentNode = split(parentNode, tempData[mid]);
		}
		else
		{
			parentNode.setKey(tempData[mid]);
		}
		parentNode.setChildNode(leftNode);
		leftNode.parent = parentNode;
		parentNode.setChildNode(rightNode);
		rightNode.parent = parentNode;
		if(node == root)
		{
			root = parentNode;
		}
		node.setKey(0, null);
		return parentNode;
	}
	/**
	 * 
	 * @param data
	 */
	public Node<T> find(T data) 
	{
		if(root == null)
			return null;
		if(root.keysLength() == 0)
			return null;
		Node<T> currentNode = root;
		while(currentNode.childrenLength() > 0)
		{
			int pos = currentNode.getSmallerKeyPos(data);
			if(pos == -1)
				return currentNode;
			currentNode = currentNode.getChildNode(pos);
		}
		int pos = currentNode.getSmallerKeyPos(data);
		if(pos == -1)
			return currentNode;
		return null;
	}
	@SuppressWarnings("unchecked")
	public Node<T>[] nodes() 
	{
		if (root == null) return null;
		if (root.keysLength() == 0) return null;
		Node<T>[] nodeList = (Node<T>[])new Node[countNumNodes()];
		nodes(root, nodeList, 0);
		return nodeList;
	}
	
	private int nodes(Node<T> node, Node<T>[] nodeList, int index) 
	{
		nodeList[index] = node;
		int newIndex = index + 1;
		for(int i = 0; i < node.childrenLength(); i++)
		{
			if(node.getChildNode(i) != null)
				newIndex = nodes(node.getChildNode(i), nodeList, newIndex);
		}
		return newIndex;
	}	
	public int numKeys() 
	{
		int numKeys = 0;
		if(root == null)
			return 0;
		if(root.keysLength() == 0)
			return 0;
		Node<T>[] nodes = nodes();
		for(int i = 0; i < nodes.length; i++)
		{
			numKeys += nodes[i].keysLength();
		}
		return numKeys;
	}

	public int countNumNodes() 
	{
		if(root == null)
			return 0;
		if(root.keysLength() == 0)
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