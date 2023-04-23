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
		this.m = m;
        this.keys = (T[])new Comparable[m-1];
        this.children = new Node[m];
        this.parent = null;
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
		int count = 0;
		for(int i = 0; i < m-1; i++)
		{
			if(keys[i] != null)
				count++;
		}
		return count;
	}
	public int childrenLength()
	{
		int count = 0;
		for(int i = 0; i < m; i++)
		{
			if(children[i] != null)
				count++;
		}
		return count;
	}
	public void setKey(Comparable<T> data)
	{
		int i = getSmallerKeyPos((T)data);
		for(int j = m-2; j > i; j--)
		{
			keys[j] = keys[j-1];
		}
		keys[i] = data;
	}
	public void setParent(Node<T> parent)
	{
		this.parent = parent;
	}
	public void setChildNode(Node<T> node)
	{
		int i = getSmallerKeyPos((T)node.getKey(0));
		for(int j = m-1; j > i; j--)
		{
			children[j] = children[j-1];
		}
		node.setParent(this);
	}
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