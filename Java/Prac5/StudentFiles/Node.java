public class Node<T extends Comparable<T>> {

	private Comparable<T>[] keys;
	private Node<T>[] children;

	/**
	 * 
	 * @param m
	 */
	@SuppressWarnings("unchecked")
	public Node(int m) {
		// TODO - implement Node.Node
		throw new UnsupportedOperationException();
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