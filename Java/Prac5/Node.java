import javax.swing.UIDefaults.ProxyLazyValue;

public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;
	private int m;
	public Node<T> parent;

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) 
	{
		keys = new Comparable[m-1];
		children = new Node[m];
		this.m = m;
		throw new UnsupportedOperationException();
	}
	public Comparable<T> getKey(int i)
	{
		return keys[i];
	}
	public Node<T> getChildNode(int i)
	{
		return children[i];
	}
	public int getSmallerKeyPos(T data)
	{
		for(int i = 0; i < m-1; i++)
		{
			if(keys[i] == null)
				return i;
			else if(keys[i].compareTo(data) > 0)
				return i;
			else if(keys[i].compareTo(data) == 0)
				return -1;
		}
		return m-1;
	}
	public int keysLength()
	{
		return keys.length;
	}
	public int childrenLength()
	{
		return children.length;
	}
	public void setKey(int i, Comparable<T> data)
	{
		keys[i] = data;
	}
	public void setParent(Node<T> parent)
	{
		this.parent = parent;
	}
	// public void setNode()
	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				res += keys[i];
			else
				res += "null";
			res += ",";
		}
		if (res.length() > 1) {
			res = res.substring(0, res.length() - 1);
		}
		return res + "]";
	}

}