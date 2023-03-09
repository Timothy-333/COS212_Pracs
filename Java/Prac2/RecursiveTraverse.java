public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T>
{
    public RecursiveTraverse()
    {
        list = null;
    }
    
    public RecursiveTraverse(SelfOrderingList<T> list)
    {
        this.list = list.getBlankList();
        CopyNode(list.head, this.list, true);
    }

    private void CopyNode(Node<T> nodePtr, SelfOrderingList<T> newList, boolean copyAccessCount)
    {
        if(nodePtr != null)
        {
            if(copyAccessCount)
            {
                newList.insert(nodePtr.data, nodePtr.accessCount);
            }
            else
            {
                newList.insert(nodePtr.data);
            }
            CopyNode(nodePtr.next, newList , copyAccessCount);
        }
    }

    @Override
    public SelfOrderingList<T> reverseList() 
    {
        if(list.head == null)
            return null;
        SelfOrderingList<T> newList = list.getBlankList();
        CopyNodeReverse(list.head, newList);
        return newList;
    }
    private void CopyNodeReverse(Node<T> nodePtr, SelfOrderingList<T> newList)
    {
        if(nodePtr != null)
        {
            CopyNodeReverse(nodePtr.next, newList);
            newList.insert(nodePtr.data,nodePtr.accessCount);
        }
    }

    @Override
    public boolean contains(T data) 
    {
        return ContainsNode(list.head, data);
    }
    private boolean ContainsNode(Node<T> nodePtr, T data)
    {
        if(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                return true;
            }
            return ContainsNode(nodePtr.next, data);
        }
        return false;
    }

    @Override
    public String toString() 
    {
        return toStringNode(list.head);
    }
    private String toStringNode(Node<T> nodePtr)
    {
        if(nodePtr != null)
        {
            return "->" + nodePtr.toString() + toStringNode(nodePtr.next);
        }
        return "";
    }

    @Override
    public Node<T> get(int pos) 
    {
        return getNode(list.head, pos);
    }
    private Node<T> getNode(Node<T> nodePtr, int pos)
    {
        if(nodePtr != null)
        {
            if(pos == 0)
            {
                return nodePtr;
            }
            return getNode(nodePtr.next, pos - 1);
        }
        return null;
    }

    @Override
    public Node<T> find(T data) 
    {
        return findNode(list.head, data);
    }
    private Node<T> findNode(Node<T> nodePtr, T data)
    {
        if(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                return nodePtr;
            }
            return findNode(nodePtr.next, data);
        }
        return null;
    }

    @Override
    public int size() 
    {
        return countNode(list.head, 0);
    }
    private int countNode(Node<T> nodePtr, int size)
    {
        if(nodePtr != null)
        {
            return countNode(nodePtr.next, ++size);
        }
        return size;
    }
    
    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) 
    {
        if(otherList == null)
        {
            return null;
        }
        SelfOrderingList<T> newList = otherList.getBlankList();
        CopyNode(otherList.head, newList, false);
        return newList;
    }
}
