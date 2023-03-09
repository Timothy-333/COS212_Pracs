public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T>{
    public IterativeTraverse()
    {
        list = null;
    }
    
    public IterativeTraverse(SelfOrderingList<T> list)
    {
        this.list = list.getBlankList();
        Node<T> nodePtr = list.head;
        while(nodePtr != null)
        {
            this.list.insert(nodePtr.data);
            nodePtr = nodePtr.next;
        }

    }

    @Override
    public SelfOrderingList<T> reverseList()
    {
        SelfOrderingList<T> newList = list.getBlankList();
        if(list.head != null)
        {
            Node<T> nodePtr = list.head;
            while(nodePtr.next != null)
            {
                nodePtr = nodePtr.next;
            }
            while(nodePtr != null)
            {
                newList.insert(nodePtr.data);
                nodePtr = nodePtr.prev;
            }
        }
        return newList;
    }

    @Override
    public boolean contains(T data) 
    {
        Node<T> nodePtr = list.head;
        while(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                return true;
            }
            nodePtr = nodePtr.next;
        }
        return false;
    }

    @Override
    public String toString() 
    {
        String str = "";
        Node<T> nodePtr = list.head;
        while(nodePtr != null)
        {
            str += "->" + nodePtr.toString();
            nodePtr = nodePtr.next;
        }
        return str;
    }

    @Override
    public Node<T> get(int pos) 
    {
        Node<T> nodePtr = list.head;
        int counter = 0;
        while(nodePtr != null)
        {
            if(counter == pos)
            {
                return nodePtr;
            }
            counter++;
            nodePtr = nodePtr.next;
        }
        return null;
    }

    @Override
    public Node<T> find(T data) 
    {
        Node<T> nodePtr = list.head;
        while(nodePtr != null)
        {
            if(nodePtr.data.compareTo(data) == 0)
            {
                return nodePtr;
            }
            nodePtr = nodePtr.next;
        }
        return null;
    }

    @Override
    public int size() 
    {
        Node<T> nodePtr = list.head;
        int counter = 0;
        while(nodePtr != null)
        {
            nodePtr = nodePtr.next;
            counter++;
        }
        return counter;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList)
    {
        SelfOrderingList<T> newList = otherList.getBlankList();
        Node<T> nodePtr = otherList.head;
        while(nodePtr != null)
        {
            newList.insert(nodePtr.data);
            nodePtr = nodePtr.next;
        }
        return newList;
    }
}
