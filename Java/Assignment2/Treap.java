public class Treap<T extends Comparable<T>> {
    public Node<T> root = null;

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        return root.toString() + "\n" + toString(root, "");
    }

    private String toString(Node<T> curr, String pre) {
        if (curr == null)
            return "";
        String res = "";

        if (curr.left != null) {
            if (curr.right != null) {
                res += pre + "├(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "|    ");
            } else {
                res += pre + "└(L)─ " + curr.left.toString() + "\n" + toString(curr.left, pre + "     ");
            }
        }

        if (curr.right != null) {
            res += pre + "└(R)─ " + curr.right.toString() + "\n" + toString(curr.right, pre + "   ");
        }
        return res;
    }

    /*
     * Don't change anything above this line
     */

    public void insert(T data) throws DatabaseException 
    {
        root = insert(root, new Node<T>(data));
    }
    private Node<T> insert(Node<T> curr, Node<T> newNode) throws DatabaseException 
    {
        if (curr == null) 
        {
            return newNode;
        }
        if (curr.data.compareTo(newNode.data) > 0) 
        {
            curr.left = insert(curr.left, newNode);
            if(curr.left.priority >= curr.priority)
            {
                curr = rotateRight(curr);
            }
        } 
        else if (curr.data.compareTo(newNode.data) < 0) 
        {
            curr.right = insert(curr.right, newNode);
            if(curr.right.priority >= curr.priority)
            {
                curr = rotateLeft(curr);
            }
        }
        else 
        {
            throw DatabaseException.duplicateInsert(newNode.data);
        }
        return curr;
    }
    private Node<T> rotateLeft(Node<T> curr) 
    {
        Node<T> temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        if(curr == root)
        {
            root = temp;
        }
        return temp;
    }

    private Node<T> rotateRight(Node<T> curr) 
    {
        Node<T> temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        if(curr == root)
        {
            root = temp;
        }
        return temp;
    }
    private Node<T> removedNode = null;
    public Node<T> remove(T data) 
    {
        if (data == null) 
        {
            return null;
        }
        removedNode = null;
        root = remove(root, data);
        return removedNode;
    }
    private Node<T> remove(Node<T> curr, T data) 
    {
        if (curr == null) 
        {
            return null;
        }
        if (curr.data.compareTo(data) > 0) 
        {
            curr.left = remove(curr.left, data);
            return curr;
        } 
        else if (curr.data.compareTo(data) < 0) 
        {
            curr.right = remove(curr.right, data);
            return curr;
        } 
        else 
        {
            if (curr.left == null) 
            {
                removedNode = curr;
                return curr.right;
            } 
            else if (curr.right == null) 
            {
                removedNode = curr;
                return curr.left;
            } 
            else 
            {
                if (curr.left.priority > curr.right.priority) 
                {
                    curr = rotateRight(curr);
                    curr.right = remove(curr.right, data);
                } 
                else 
                {
                    curr = rotateLeft(curr);
                    curr.left = remove(curr.left, data);
                }
                return curr;
            }
        }
    }
    private Node<T> foundNode = null;
    public Node<T> access(T data) 
    {
        foundNode = null;
        access(root, data);
        return foundNode;
    }
    private Node<T> access(Node<T> curr, T data)
    {
        if (curr == null)
        {
            return null;
        }
        else if (curr.data.compareTo(data) > 0)
        {
            curr.left = access(curr.left, data);
            if (curr.left != null && curr.left.priority >= curr.priority)
            {
                curr = rotateRight(curr);
            }
        } 
        else if (curr.data.compareTo(data) < 0) 
        {
            curr.right = access(curr.right, data);
            if (curr.right != null && curr.right.priority >= curr.priority)
            {
                curr = rotateLeft(curr);
            }
        } 
        else 
        {
            curr.priority++;
            foundNode = curr;
        }
        return curr;
    }
}
